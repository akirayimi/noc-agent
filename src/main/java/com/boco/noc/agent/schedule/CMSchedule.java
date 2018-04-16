package com.boco.noc.agent.schedule;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class CMSchedule {
	public static void main(String[] args) throws SchedulerException {
		new CMSchedule().task();
	}
	public void task() throws SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();


		JobDetail jobDetail = JobBuilder.newJob(CMJob.class).build();
		CronTrigger trigger = new CronTriggerImpl("cronTrigger", "triggerGroup2");
		
		try {
			CronExpression cexp = new CronExpression("*/10 * * * *");
			((CronTriggerImpl) trigger).setCronExpression(cexp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		scheduler.scheduleJob(jobDetail, trigger);

		scheduler.start();
	}
}
