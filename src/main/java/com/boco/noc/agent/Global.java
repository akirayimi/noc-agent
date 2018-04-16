package com.boco.noc.agent;

public class Global {
	public enum OSType{WINDOWS, LINUX};
	public static OSType CURRENT_OS;
	static {
		CURRENT_OS = getCurrentOS();
	}
	public static final String LINE_SEPERATOR = System.getProperty("line.separator");
	public static final String BLANK = "";
	private static OSType getCurrentOS() {
		String os = System.getProperty("os.name");
		if (os.toUpperCase().startsWith("WIN")){
			return OSType.WINDOWS;
		} else {
			return OSType.LINUX;
		}
	}
}
