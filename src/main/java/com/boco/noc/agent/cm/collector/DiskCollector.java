package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.DiskInfo;

public class DiskCollector extends AbstractCollector {

	@Override
	protected CfgInfo _start() {
		CfgInfo info = new DiskInfo();
		return _start0(info, "DiskCommand");
	}
}
