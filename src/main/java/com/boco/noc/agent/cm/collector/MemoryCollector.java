package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.collector.command.linux.LinuxMemoryCommand;
import com.boco.noc.agent.cm.collector.command.windows.WinMemoryCommand;
import com.boco.noc.agent.cm.info.MemoryInfo;

public class MemoryCollector extends AbstractCollector<MemoryInfo> {
	
	@Override
	protected void _start() {
		info = new MemoryInfo();
		if (Global.CURRENT_OS == Global.OSType.WINDOWS){
			new WinMemoryCommand(info);
		} else if (Global.CURRENT_OS == Global.OSType.LINUX){
			new LinuxMemoryCommand(info);
		}
	}
}
