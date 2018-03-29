package com.boco.noc.agent.cm.collector;

import org.apache.log4j.Logger;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.util.LogUtils;

public abstract class AbstractCollector implements Collector{
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
		LogUtils.logInfo(specifiedLogger, this.getClass().getSimpleName() + " start.");
		CfgInfo info = _start();
		LogUtils.logInfo(specifiedLogger, this.getClass().getSimpleName() + " successfully end.");
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
