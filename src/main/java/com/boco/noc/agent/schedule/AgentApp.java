package com.boco.noc.agent.schedule;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import com.boco.noc.agent.util.LogUtils;

public class AgentApp {
	private static Logger logger = Logger.getLogger(AgentApp.class);
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			new CMSchedule().start();
			new PMSchedule().start();
		} catch (SchedulerException e) {
			LogUtils.logError(logger, "", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			LogUtils.logError(logger, "", e);
			e.printStackTrace();
		} catch (ParseException e) {
			LogUtils.logError(logger, "", e);
			e.printStackTrace();
		}
	}
}
