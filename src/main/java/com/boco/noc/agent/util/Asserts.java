package com.boco.noc.agent.util;

import org.apache.log4j.Logger;

public class Asserts {
	private static Logger logger = Logger.getLogger(Asserts.class);
	public static void notNull(Object obj){
		if (obj == null){
			LogUtils.logError(logger, "assert null failed!", new NullPointerException());
			throw new RuntimeException("assert null failed!");
		}
	}
	
	public static void main(String[] args) {
		System.out.println(1);
		Asserts.notNull(null);
		System.out.println("end");
	}
}
