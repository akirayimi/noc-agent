package com.boco.noc.agent.cm.collector.command.linux;

import com.boco.noc.agent.cm.collector.command.CpuCommand;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.CpuInfo;

public class LinuxCpuCommand extends CpuCommand {

	public LinuxCpuCommand(CfgInfo info) {
		putInfo(info);
		putPhysCnt(info);
		putCoreCnt(info);
	}

	@Override
	protected void putInfo(CfgInfo info) {
	}

	@Override
	protected void putPhysCnt(CfgInfo info) {
	}

	@Override
	protected void putCoreCnt(CfgInfo info) {
	}

}
