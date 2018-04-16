package com.boco.noc.agent.cm.collector.command.linux;

import com.boco.noc.agent.cm.collector.command.CpuCommand;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.CpuInfo;
import com.boco.noc.agent.util.RuntimeExecutor;

public class LinuxCpuCommand extends CpuCommand {

	public LinuxCpuCommand(CfgInfo info) {
		putInfo(info);
		putPhysCnt(info);
		putCoreCnt(info);
	}

	@Override
	protected void putInfo(CfgInfo info) {
		info.put(CpuInfo.INFO, RuntimeExecutor.run("cat /proc/cpuinfo |grep name | cut -f2 -d:"));
	}

	@Override
	protected void putPhysCnt(CfgInfo info) {
		info.put(CpuInfo.INFO, RuntimeExecutor.run("cat /proc/cpuinfo | grep \"physical id\" | sort | uniq | wc -l"));
	}

	@Override
	protected void putCoreCnt(CfgInfo info) {
		info.put(CpuInfo.INFO, RuntimeExecutor.run("cat /proc/cpuinfo | grep \"cpu cores\" | uniq  |cut -f2 -d :"));
	}

}
