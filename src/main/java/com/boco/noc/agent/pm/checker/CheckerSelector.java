package com.boco.noc.agent.pm.checker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.Global.OSType;

public class CheckerSelector {
	private static Map<OSType, Checker> checkerPool = new ConcurrentHashMap<OSType, Checker>();
	
	static {
		//checkerPool.put(OSType.WINDOWS, new WindowsChecker())
		checkerPool.put(OSType.LINUX, new LinuxChecker());
	}
	
	public static Checker select(){
		return checkerPool.get(Global.CURRENT_OS);
	}
}
