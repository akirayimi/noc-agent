package com.boco.noc.agent.cm.collector.command;

import com.boco.noc.agent.cm.info.CfgInfo;

public abstract class MemoryCommand extends AbstractCommand{
	protected abstract void putTotal(CfgInfo info);
}
