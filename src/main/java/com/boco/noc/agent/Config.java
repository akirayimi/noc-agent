package com.boco.noc.agent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static String REMOTE_HOST = null; 
	public static Integer REMOTE_PORT = null;
	
	public static int CM_FREQUENCY = 24; //default
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private final static Properties pro = new Properties();
	private final static String PRO_PATH = "cfg.properties";
	static {
		try {
			InputStream in = Config.class.getClassLoader().getResourceAsStream(PRO_PATH);
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		REMOTE_HOST = getRemoteHost();
		REMOTE_PORT = getRemotePort();
		CM_FREQUENCY = getFrequenry();
	}
	
	private static String getRemoteHost() {
		String host = pro.getProperty("remote_host");
		if (host == null)
			throw new RuntimeException("please define the remote host in [cfg.properties] file!");
		return host;
	}

	private static Integer getRemotePort() {
		String port = pro.getProperty("remote_port");
		if (port == null)
			throw new RuntimeException("please define the remote port in [cfg.properties] file!");
		Integer intport = null;
		try {
			intport = Integer.valueOf(port);
		} catch (NumberFormatException e ){
			throw new NumberFormatException("the value of [cfg.properties#remote_port] should be a number!");
		}
		return intport;
	}
	
	private static int getFrequenry() {
		String val = pro.getProperty("frequency");
		return val == null ? CM_FREQUENCY : Integer.valueOf(val.trim());
	}
	
	public static void main(String[] args) {
		System.out.println(REMOTE_HOST + ":" + REMOTE_PORT);
	}
}
