package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.MemoryInfo;

public class MemoryCollector extends AbstractCollector {
	
	@Override
	protected CfgInfo _start() {
		CfgInfo info = new MemoryInfo();
		return _start0(info, "MemoryCommand");
	}
}
