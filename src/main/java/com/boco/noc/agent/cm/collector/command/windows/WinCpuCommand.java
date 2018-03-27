package com.boco.noc.agent.cm.collector.command.windows;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.collector.command.CpuCommand;
import com.boco.noc.agent.cm.information.Cpu;
import com.google.common.base.Strings;

public class WinCpuCommand extends CpuCommand{
	
	
	public WinCpuCommand(Cpu info) {
		putInfo(info);putPhysCnt(info);putCoreCnt(info);
	}
	
	private void put(Cpu info, String cmd, String identifer){
		String result = exec(cmd);
		String[] results = result.split(Global.LINE_SEPERATOR);
		if (results != null && results.length == 2){
			info.put(identifer, results[1]);
		} else {
			info.put(identifer, Strings.nullToEmpty(null));
		}
	}

	@Override
	protected void putInfo(Cpu info) {
		put(info, "wmic cpu get Name", Cpu.INFO);
	}

	@Override
	protected void putPhysCnt(Cpu info) {
		put(info, "wmic cpu get NumberOfCores", Cpu.PHYSICAL_COUNT);
	}

	@Override
	protected void putCoreCnt(Cpu info) {
		put(info, "wmic cpu get NumberOfEnabledCore", Cpu.CORE_COUNT);
	}
}
