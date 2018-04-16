package com.boco.noc.agent.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.noc.agent.pm.Performance;
import com.boco.noc.agent.pm.ResultData;
import com.boco.noc.agent.pm.SelectChecker;

public class PMJob implements Job{
	Date start = new Date();
	Date end = new Date();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Performance p = (Performance)(context.getJobDetail().getJobDataMap().get("performance"));
		ResultData r = (ResultData)(context.getJobDetail().getJobDataMap().get("resultData"));
		SelectChecker.select().check(p, r);
		System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]" + p.getName());
	}
}	
