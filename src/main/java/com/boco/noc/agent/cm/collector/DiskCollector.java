package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.collector.command.linux.LinuxDiskCommand;
import com.boco.noc.agent.cm.collector.command.windows.WinDiskCommand;
import com.boco.noc.agent.cm.info.DiskInfo;

public class DiskCollector extends AbstractCollector<DiskInfo> {


	@Override
	protected void _start() {
		info = new DiskInfo();
		if (Global.CURRENT_OS == Global.OSType.WINDOWS){
			new WinDiskCommand(info);
		} else if (Global.CURRENT_OS == Global.OSType.LINUX){
			new LinuxDiskCommand(info);
		}
	}

}
