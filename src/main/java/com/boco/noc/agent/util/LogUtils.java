package com.boco.noc.agent.util;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogUtils {
	private final static String ERROR_MSG = "something is wrong: ";
	
	static {
		Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("[%p] [%d{yyyy-MM-dd HH:mm:ss.SSS}] %c: %m%n")));
	}
	
	public static void logError(Logger logger, String msg, Throwable t){
		logger.error(msg + t);
		t.printStackTrace();
	}
	
	public static void logError(Logger logger, String msg){
		logger.error(msg);
		//t.printStackTrace();
	}
	
	public static void logError(Logger logger, Object obj, Throwable t){
		logError(logger, obj.toString(), t);
	}
	
	public static void logError(Logger logger, Throwable t){
		logError(logger, ERROR_MSG, t);
	}

	public static void logInfo(Logger logger, Object msg) {
		logger.info(msg);
	}
}
