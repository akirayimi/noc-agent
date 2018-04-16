package com.boco.noc.agent.cm.collector.command.linux;

import com.boco.noc.agent.cm.collector.command.DiskCommand;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.MemoryInfo;

public class LinuxDiskCommand extends DiskCommand{

	public LinuxDiskCommand(CfgInfo info) {
		putTotalSize(info);
	}

	@Override
	protected void putTotalSize(CfgInfo info) {
		info.put(MemoryInfo.TOTAL_SIZE, "dmidecode -t memory | grep \"Maximum Capacity\" | cut -f2 -d:");
	}
}
