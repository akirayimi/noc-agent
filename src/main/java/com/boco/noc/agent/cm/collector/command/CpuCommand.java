package com.boco.noc.agent.cm.collector.command;

import com.boco.noc.agent.cm.collector.command.windows.WinCpuCommand;
import com.boco.noc.agent.cm.information.Cpu;

public abstract class CpuCommand extends AbstractCommand{
	private final static String INFO = "cm.cpu.cpuInfo";
	private final static String PHYSICAL_COUNT = "cm.cpu.physicalId";
	private final static String CORE_COUNT = "cm.cpu.cores";
	

	/**
	 * get the INFO, and put into the cpu incofmation map.
	 * @param info
	 * @see Cpu#INFO
	 * @return
	 */
	protected abstract void putInfo(Cpu info);
	
	/**
	 * get the PHYSICAL_COUNTs, and put into the cpu incofmation map.
	 * @param info
	 * @see Cpu#PHYSICAL_COUNT
	 * @return
	 */
	protected abstract void putPhysCnt(Cpu info);
	
	/**
	 * get the CORE_COUNT, and put into the cpu incofmation map.
	 * @see Cpu#CORE_COUNT
	 * @param info
	 * @return
	 */
	protected abstract void putCoreCnt(Cpu info);
}
