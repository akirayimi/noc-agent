package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.OsInfo;

public class OSCollector extends AbstractCollector {
	@Override
	protected CfgInfo _start() {
		CfgInfo info = new OsInfo();
		info.put(OsInfo.RELEASE_TYPE, System.getProperty("os.name"));
		info.put(OsInfo.HOST_NAME, System.getProperty("user.name"));
		info.put(OsInfo.VERSION, System.getProperty("os.version"));
		return info;
	}
}
