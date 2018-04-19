package com.boco.noc.agent.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.boco.noc.agent.ResultData;
import com.boco.noc.agent.cm.collector.CpuCollector;
import com.boco.noc.agent.cm.collector.DiskCollector;
import com.boco.noc.agent.cm.collector.MemoryCollector;
import com.boco.noc.agent.cm.collector.OSCollector;
import com.boco.noc.agent.cm.collector.factory.CollectorFactory;
import com.boco.noc.agent.cm.convert.Converter;
import com.boco.noc.agent.cm.convert.StdConverter;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.communicate.NettyClient;
import com.boco.noc.agent.util.LogUtils;

public class CMJob implements Job {
	private static Logger logger = Logger.getLogger(CMJob.class);
	private final static Converter con = new StdConverter();

	@Override
	public void execute(JobExecutionContext jec) {
		List<Future<CfgInfo>> results = new ArrayList<Future<CfgInfo>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		results.add(exec.submit(CollectorFactory.getCollector(OSCollector.class)));
		results.add(exec.submit(CollectorFactory.getCollector(CpuCollector.class)));
		results.add(exec.submit(CollectorFactory.getCollector(DiskCollector.class)));
		results.add(exec.submit(CollectorFactory.getCollector(MemoryCollector.class)));
		exec.shutdown();

		List<ResultData> list = new ArrayList<ResultData>();
		for (Future<CfgInfo> f : results) {
			try {
				list.addAll(con.convert(f.get()));
			} catch (InterruptedException | ExecutionException e) {
				LogUtils.logError(logger, e);
				e.printStackTrace();
			}
		}
		for (ResultData data : list){
			System.out.println(data.toString());
			NettyClient.send(data.toString());
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
