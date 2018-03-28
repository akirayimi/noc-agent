package com.boco.noc.agent.cm.collector.command;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.cm.info.CpuInfo;

public abstract class CpuCommand extends AbstractCommand{
	/**
	 * get the INFO, and put it into the CPU information map.
	 * @param info
	 * @see CpuInfo#INFO
	 * @return
	 */
	protected abstract void putInfo(CfgInfo info);
	
	/**
	 * get the PHYSICAL_COUNTs, and put it into the CPU information map.
	 * @param info
	 * @see CpuInfo#PHYSICAL_COUNT
	 * @return
	 */
	protected abstract void putPhysCnt(CfgInfo info);
	
	/**
	 * get the CORE_COUNT, and put it into the CPU information map.
	 * @see CpuInfo#CORE_COUNT
	 * @param info
	 * @return
	 */
	protected abstract void putCoreCnt(CfgInfo info);
}
