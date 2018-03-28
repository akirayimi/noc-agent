package com.boco.noc.agent.cm.collector;

import org.apache.log4j.Logger;

import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.util.LogUtils;

public abstract class AbstractCollector<T extends CfgInfo> implements Collector<T>{
	T info;
	
	@Override
	public void stop() {
		
	}
	
	protected abstract void _start();

	@Override
	public Collector<T> start(){
		Logger specifiedLogger = Logger.getLogger(this.getClass());
		LogUtils.logInfo(specifiedLogger, this.getClass().getSimpleName() + " start.");
		_start();
		LogUtils.logInfo(specifiedLogger, this.getClass().getSimpleName() + " successfully end.");
		return this;
	}

	@Override
	public T get(){
		if (info == null)
			throw new CollectorNotStartException();
		return info;
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
