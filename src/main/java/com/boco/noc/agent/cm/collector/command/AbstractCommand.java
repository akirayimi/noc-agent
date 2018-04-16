package com.boco.noc.agent.cm.collector.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.util.LogUtils;
import com.boco.noc.agent.util.RuntimeExecutor;
import com.google.common.base.Strings;

public abstract class AbstractCommand implements Command {
	private static Logger logger = Logger.getLogger(AbstractCommand.class);
	
	protected static void winPut(CfgInfo info, String cmd, String identifer){
		String result = RuntimeExecutor.run(cmd);
		String[] results = result.split(Global.LINE_SEPERATOR);
		if (results != null && results.length > 1){
			info.put(identifer, results[1]);
		} else {
			info.put(identifer, Strings.nullToEmpty(null));
		}
	}
}
