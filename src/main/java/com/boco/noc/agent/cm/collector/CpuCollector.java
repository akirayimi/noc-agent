package com.boco.noc.agent.cm.collector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.collector.command.linux.LinuxCpuCommand;
import com.boco.noc.agent.cm.collector.command.windows.WinCpuCommand;
import com.boco.noc.agent.cm.information.Cpu;

public class CpuCollector extends AbstractCollector<Cpu>{
	@Override
	protected void _start() {
		info = new Cpu();
		if (Global.CURRENT_OS == Global.OSType.WINDOWS){
			new WinCpuCommand(info);
		} else if (Global.CURRENT_OS == Global.OSType.LINUX){
			new LinuxCpuCommand(info);
		}
	}
}
