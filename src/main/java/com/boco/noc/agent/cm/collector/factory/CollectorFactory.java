package com.boco.noc.agent.cm.collector.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.boco.noc.agent.cm.collector.Collector;
import com.boco.noc.agent.cm.collector.CpuCollector;
import com.boco.noc.agent.cm.collector.DiskCollector;
import com.boco.noc.agent.cm.collector.MemoryCollector;
import com.boco.noc.agent.cm.collector.OSCollector;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.util.LogUtils;

public class CollectorFactory {
	private final static Logger logger = Logger.getLogger(CollectorFactory.class);
	private final static Map<String, Collector> collectorMap = new HashMap<String, Collector>();
	
	private final static ReentrantLock lock = new ReentrantLock();
	
	public static Collector getCollector(Class<? extends Collector> clazz) {
		Collector c = collectorMap.get(clazz.getCanonicalName());
		if (c == null){
			lock.lock();
			try {
				c = collectorMap.get(clazz.getCanonicalName());
				if (c == null) {
					c = clazz.newInstance();
					collectorMap.put(clazz.getCanonicalName(), c);
				}
			} catch (Throwable e) {
				LogUtils.logError(logger, "init " + clazz.getName() + " collector failed!", e);
			} finally {
				lock.unlock();
			}
		}
		return c;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<Future<CfgInfo>> results = new ArrayList<Future<CfgInfo>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		results.add(exec.submit(getCollector(OSCollector.class)));
		results.add(exec.submit(getCollector(CpuCollector.class)));
		results.add(exec.submit(getCollector(DiskCollector.class)));
		results.add(exec.submit(getCollector(MemoryCollector.class)));
		exec.shutdown();           
		for (Future<CfgInfo> f : results){
			System.out.println(f.get());
		}
	}
	
}
