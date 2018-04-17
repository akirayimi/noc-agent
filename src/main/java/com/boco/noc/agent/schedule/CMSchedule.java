package com.boco.noc.agent.schedule;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import com.boco.noc.agent.Config;

public class CMSchedule {
	public static void main(String[] args) throws SchedulerException {
		new CMSchedule().start();
	}
	
	public void start() throws SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(CMJob.class).build();
		@SuppressWarnings("deprecation")
		Trigger trigger = new SimpleTriggerImpl("trigger",
                SimpleTrigger.REPEAT_INDEFINITELY, Config.CM_FREQUENCY * 3600 * 1000L);
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}
}
