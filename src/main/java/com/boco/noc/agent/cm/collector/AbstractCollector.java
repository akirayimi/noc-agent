package com.boco.noc.agent.cm.collector;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.Global.OSType;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.util.LogUtils;

public abstract class AbstractCollector implements Collector{
	protected final static Map<OSType, String> OSTYPE_FIX = new HashMap<OSType, String>();
	static {
		OSTYPE_FIX.put(OSType.WINDOWS, "com.boco.noc.agent.cm.collector.command.windows.Win");
		OSTYPE_FIX.put(OSType.LINUX, "com.boco.noc.agent.cm.collector.command.linux.Linux");
	}
	
	protected CfgInfo _start0(CfgInfo info, String clazznameFix){
		try {
			String clazz = OSTYPE_FIX.get(Global.CURRENT_OS) + clazznameFix;
			Class<?> command = Class.forName(clazz);
			Constructor<?> cons = command.getConstructor(CfgInfo.class);
			cons.newInstance(info);
		} catch (Exception e) {
			LogUtils.logError(Logger.getLogger(this.getClass()), e);
			throw new RuntimeException(e);
		}
		
		return info;
	}
	
	@Override
	public CfgInfo call() throws Exception {
		return get();
	}
	
	@Override
	public void stop() {
		
	}
	
	protected abstract CfgInfo _start();

	protected CfgInfo start(){
		Logger specifiedLogger = Logger.getLogger(this.getClass());
		CfgInfo info = null;
		try {
			LogUtils.logInfo(specifiedLogger, this.getClass().getSimpleName() + " start.");
			info = _start();
			LogUtils.logInfo(specifiedLogger, this.getClass().getSimpleName() + " successfully end.");
		} catch(Exception e){
			LogUtils.logError(specifiedLogger, this.getClass().getSimpleName() + " ends error!", e);
		}
		return info;
	}
	
	@Override
	public CfgInfo get(){
		return start();
	}

}

@SuppressWarnings("serial")
class CollectorNotStartException extends RuntimeException{
	public CollectorNotStartException(String msg){
		super(msg);
	}
	
	public CollectorNotStartException(){
		this("invoke start() method before get().");
	}
}
