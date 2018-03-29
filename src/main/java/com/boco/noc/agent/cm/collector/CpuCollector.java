package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.collector.command.linux.LinuxCpuCommand;
import com.boco.noc.agent.cm.collector.command.windows.WinCpuCommand;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.CpuInfo;

public class CpuCollector extends AbstractCollector{
	@Override
	protected CfgInfo _start() {
		CfgInfo info = new CpuInfo();
		if (Global.CURRENT_OS == Global.OSType.WINDOWS){
			new WinCpuCommand(info);
		} else if (Global.CURRENT_OS == Global.OSType.LINUX){
			new LinuxCpuCommand(info);
		}
		return info;
	}

	
}
