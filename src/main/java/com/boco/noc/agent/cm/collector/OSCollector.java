package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.cm.info.OsInfo;

public class OSCollector extends AbstractCollector<OsInfo>{
	@Override
	protected void _start() {
		info = new OsInfo();
		info.put(OsInfo.RELEASE_TYPE, System.getProperty("os.name"));
		info.put(OsInfo.HOST_NAME, System.getProperty("user.name"));
		info.put(OsInfo.VERSION, System.getProperty("os.version"));
	}
	
	public static void main(String[] args) {
		System.getProperties().list(System.out);
	}
}
