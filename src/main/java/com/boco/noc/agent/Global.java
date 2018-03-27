package com.boco.noc.agent;

import java.io.File;

public class Global {
	public enum OSType{WINDOWS, LINUX};
	public static OSType CURRENT_OS = getCurrentOS();
	public static String LINE_SEPERATOR = System.getProperty("line.separator");
	
	private static OSType getCurrentOS() {
		String os = System.getProperty("os.name");
		
		if (os.toUpperCase().startsWith("WIN")){
			return OSType.WINDOWS;
		} else {
			return OSType.LINUX;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(CURRENT_OS);
	}
}
