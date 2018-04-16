package com.boco.noc.agent.pm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.Global.OSType;
import com.boco.noc.agent.pm.linux.LinuxChecker;

public class SelectChecker {
	private static Map<OSType, Checker> checkerPool = new ConcurrentHashMap<OSType, Checker>();
	
	static {
		//checkerPool.put(OSType.WINDOWS, new WindowsChecker())
		checkerPool.put(OSType.LINUX, new LinuxChecker());
	}
	
	public static Checker select(){
		return checkerPool.get(Global.CURRENT_OS);
	}
}
