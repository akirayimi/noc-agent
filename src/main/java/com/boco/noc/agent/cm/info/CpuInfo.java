package com.boco.noc.agent.cm.info;

/**
 * the information of cpu.
 * @author someone
 *
 */
@SuppressWarnings("serial")
public class CpuInfo extends AbstractCfgInfo{
	@Identifier
	public final static String INFO = "cm.cpu.cpuInfo";
	@Identifier
	public final static String PHYSICAL_COUNT = "cm.cpu.physicalId";
	@Identifier
	public final static String CORE_COUNT = "cm.cpu.cores";
}
