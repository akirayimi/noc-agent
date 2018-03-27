package com.boco.noc.agent.cm.information;

import java.util.Iterator;

/**
 * the information of cpu.
 * @author someone
 *
 */
public class Cpu extends AbstractCfgInfo{
	@Identifier
	public final static String INFO = "cm.cpu.cpuInfo";
	@Identifier
	public final static String PHYSICAL_COUNT = "cm.cpu.physicalId";
	@Identifier
	public final static String CORE_COUNT = "cm.cpu.cores";
}
