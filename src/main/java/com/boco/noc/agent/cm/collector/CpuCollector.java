package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.CpuInfo;

public class CpuCollector extends AbstractCollector{
	@Override
	protected CfgInfo _start() {
		CfgInfo info = new CpuInfo();
		return _start0(info, "CpuCommand");
	}

	
}
