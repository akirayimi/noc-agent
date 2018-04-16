package com.boco.noc.agent.schedule;

import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.noc.agent.cm.collector.factory.CollectorFactory;
import com.boco.noc.agent.util.LogUtils;

public class CMJob implements Job{
	private static Logger logger = Logger.getLogger(CMJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			CollectorFactory.main(null);
		} catch (InterruptedException e) {
			LogUtils.logError(logger, "", e);
			e.printStackTrace();
		} catch (ExecutionException e) {
			LogUtils.logError(logger, "", e);
			e.printStackTrace();
		}
	}
}
