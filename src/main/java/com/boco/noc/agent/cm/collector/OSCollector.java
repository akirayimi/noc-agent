package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.cm.information.OperatingSystem;

public class OSCollector extends AbstractCollector<OperatingSystem>{
	@Override
	protected void _start() {
		info = new OperatingSystem();
		info.put(OperatingSystem.RELEASE_TYPE, System.getProperty("os.name"));
		info.put(OperatingSystem.HOST_NAME, System.getProperty("user.name"));
		info.put(OperatingSystem.VERSION, System.getProperty("os.version"));
	}
	
	public static void main(String[] args) {
		System.getProperties().list(System.out);
	}
}
