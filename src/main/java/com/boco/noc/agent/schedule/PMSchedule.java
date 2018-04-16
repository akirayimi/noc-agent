package com.boco.noc.agent.schedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import com.boco.noc.agent.pm.DataPool;
import com.boco.noc.agent.pm.Performance;
import com.boco.noc.agent.pm.PerformanceGen;

public class PMSchedule {
	public static void main(String[] args) throws SchedulerException, ParseException, FileNotFoundException {
		System.setErr(new PrintStream(new File("d://err.log")));
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		int count = 0;
		for (Performance p : PerformanceGen.all()){
			JobDetail jobDetail = JobBuilder.newJob(PMJob.class).build();
			jobDetail.getJobDataMap().put("performance", p);
			jobDetail.getJobDataMap().put("resultData", DataPool.getResultData(p));
			@SuppressWarnings("deprecation")
			SimpleTrigger trigger = new SimpleTriggerImpl("trigger" + count++,
	                SimpleTrigger.REPEAT_INDEFINITELY, p.getFrequency() * 1000);
			scheduler.scheduleJob(jobDetail, trigger);
		}
		scheduler.start();
	}
}
