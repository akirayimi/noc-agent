package com.boco.noc.agent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.boco.noc.agent.Global;
import com.google.common.base.Strings;

public class RuntimeExecutor {
	private static Logger logger = Logger.getLogger(RuntimeExecutor.class);
	
	public static String run(String cmd){
		return run(cmd, "utf8");
	}

	public static String run(String cmd, String charset){
		StringBuilder ret = new StringBuilder();
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), charset));
			String s;
			while ((s = br.readLine()) != null){
				if (!Strings.isNullOrEmpty(s))
					ret.append(s).append(Global.LINE_SEPERATOR);
			}
			
			BufferedReader errorBr = new BufferedReader(new InputStreamReader(p.getErrorStream(), charset));
			while ((s = errorBr.readLine()) != null){
				LogUtils.logError(logger, "execute command [" + cmd + "] error: " + s);
				throw new RuntimeException(s);
			}
		} catch (IOException e) {
			LogUtils.logError(logger, "execute command [" + cmd + "] error!", e);
			throw new RuntimeException(e);
		}
		return ret.toString().trim();
	}
}
