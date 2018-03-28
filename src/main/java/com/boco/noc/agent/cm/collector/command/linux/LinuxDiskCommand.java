package com.boco.noc.agent.cm.collector.command.linux;

import com.boco.noc.agent.cm.collector.command.DiskCommand;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.DiskInfo;

public class LinuxDiskCommand extends DiskCommand{

	public LinuxDiskCommand(DiskInfo info) {
		putTotalSize(info);
	}

	@Override
	protected void putTotalSize(CfgInfo info) {
		
	}

}
