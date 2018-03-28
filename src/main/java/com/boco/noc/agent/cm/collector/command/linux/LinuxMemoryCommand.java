package com.boco.noc.agent.cm.collector.command.linux;

import com.boco.noc.agent.cm.collector.command.MemoryCommand;
import com.boco.noc.agent.cm.info.CfgInfo;

public class LinuxMemoryCommand extends MemoryCommand {

	public LinuxMemoryCommand(CfgInfo info) {
		putTotal(info);
		
	}

	@Override
	protected void putTotal(CfgInfo info) {
		
	}
}
