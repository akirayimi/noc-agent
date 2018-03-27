package com.boco.noc.agent.cm.collector.command.linux;

import com.boco.noc.agent.cm.collector.command.CpuCommand;
import com.boco.noc.agent.cm.information.Cpu;

public class LinuxCpuCommand extends CpuCommand {

	public LinuxCpuCommand(Cpu info) {
		putInfo(info);
		putPhysCnt(info);
		putCoreCnt(info);
	}

	@Override
	protected void putInfo(Cpu info) {
	}

	@Override
	protected void putPhysCnt(Cpu info) {
	}

	@Override
	protected void putCoreCnt(Cpu info) {
	}

}
