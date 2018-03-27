package com.boco.noc.agent.cm.collector.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.util.LogUtils;
import com.google.common.base.Strings;

public class AbstractCommand implements Command {
	private static Logger logger = Logger.getLogger(AbstractCommand.class);
	public static String exec(String cmd){
		return exec(cmd, "utf-8");
	}
	
	public static String exec(String cmd, String charset){
		StringBuilder ret = new StringBuilder();
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), charset));
			String s;
			while ((s = br.readLine()) != null){
				if (!Strings.isNullOrEmpty(s))
					ret.append(s).append(Global.LINE_SEPERATOR);
			}
		} catch (IOException e) {
			LogUtils.logError(logger, "execute command [" + cmd + "] error!", e);
		}
		return ret.toString().trim();
	}
}
