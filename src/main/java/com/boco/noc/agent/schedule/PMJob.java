package com.boco.noc.agent.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.noc.agent.ResultData;
import com.boco.noc.agent.communicate.NettyClient;
import com.boco.noc.agent.pm.checker.SelectChecker;
import com.boco.noc.agent.pm.data.Performance;

public class PMJob implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Performance p = (Performance)(context.getJobDetail().getJobDataMap().get("performance"));
		ResultData r = (ResultData)(context.getJobDetail().getJobDataMap().get("resultData"));
		//SelectChecker.select().check(p, r);
		String val = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]" + p.getName();
		NettyClient.send(val);
		System.out.println(val);
	}
}	
