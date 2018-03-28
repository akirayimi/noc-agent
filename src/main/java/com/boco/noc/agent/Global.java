package com.boco.noc.agent;

public class Global {
	public enum OSType{WINDOWS, LINUX};
	public static OSType CURRENT_OS = getCurrentOS();
	public static String LINE_SEPERATOR = System.getProperty("line.separator");
	
	public static int KB = 1024;
	public static int MB = 1024 << 10;
	public static int GB = 1024 << 20;
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
