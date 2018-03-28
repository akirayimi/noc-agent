package com.boco.noc.agent.cm.collector.command;

import com.boco.noc.agent.cm.info.CfgInfo;

public abstract class DiskCommand extends AbstractCommand{
	protected abstract void putTotalSize(CfgInfo info);
}
