package com.boco.noc.agent.cm.collector.command.windows;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.DiskInfo;

public class WinDiskCommand extends WinStorageCommand{

	public WinDiskCommand(CfgInfo info) {
		putTotalSize(info);
	}

	@Override
	protected void putTotalSize(CfgInfo info) {
		super.putTotalSize(info, DiskInfo.TOTAL_SIZE, "wmic diskdrive get Model", "wmic diskdrive get Model,Size");
	}
	

}
