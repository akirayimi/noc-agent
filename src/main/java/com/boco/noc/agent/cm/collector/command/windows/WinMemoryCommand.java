package com.boco.noc.agent.cm.collector.command.windows;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.MemoryInfo;

public class WinMemoryCommand extends WinStorageCommand{
	public WinMemoryCommand(CfgInfo info){
		putTotalSize(info);
	}

	@Override
	protected void putTotalSize(CfgInfo info) {
		super.putTotalSize(info, MemoryInfo.TOTAL_SIZE, "wmic memorychip get Manufacturer", "wmic memorychip get Manufacturer, Capacity");
	}
}
