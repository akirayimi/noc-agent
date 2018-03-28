package com.boco.noc.agent.cm.collector.command.windows;

import com.boco.noc.agent.cm.collector.command.CpuCommand;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.CpuInfo;

public class WinCpuCommand extends CpuCommand{
	public WinCpuCommand(CpuInfo info) {
		putInfo(info);putPhysCnt(info);putCoreCnt(info);
	}

	@Override
	protected void putInfo(CfgInfo info) {
		winPut(info, "wmic cpu get Name", CpuInfo.INFO);
	}

	@Override
	protected void putPhysCnt(CfgInfo info) {
		winPut(info, "wmic cpu get NumberOfCores", CpuInfo.PHYSICAL_COUNT);
	}

	@Override
	protected void putCoreCnt(CfgInfo info) {
		winPut(info, "wmic cpu get NumberOfEnabledCore", CpuInfo.CORE_COUNT);
	}
}
