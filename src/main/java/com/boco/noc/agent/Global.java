package com.boco.noc.agent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Global {
	public static OSType CURRENT_OS;
	public static String IP;

	public static final String LINE_SEPERATOR = System.getProperty("line.separator");
	public static final String BLANK = "";
	public static final String SEPERATOR = "#@#@#";
	
	public enum OSType {
		WINDOWS, LINUX
	};
	
	static {
		CURRENT_OS = getCurrentOS();

		IP = getCurrentIp();
	}
	
	private static OSType getCurrentOS() {
		String os = System.getProperty("os.name");
		if (os.toUpperCase().startsWith("WIN")) {
			return OSType.WINDOWS;
		} else {
			return OSType.LINUX;
		}
	}

	private static String getCurrentIp() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip = addr.getHostAddress().toString(); 
		return ip;
	}
}
