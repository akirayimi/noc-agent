package com.boco.noc.agent.cm.collector;

import org.apache.log4j.Logger;

import com.boco.noc.agent.cm.information.CfgInfo;
import com.boco.noc.agent.util.LogUtils;

public abstract class AbstractCollector<T extends CfgInfo> implements Collector<T>{
	T info;
	private static final Logger logger = Logger.getLogger(AbstractCollector.class);
	
	
	@Override
	public void stop() {
		
	}
	
	protected abstract void _start();

	@Override
	public Collector<T> start(){
		LogUtils.logInfo(logger, this.getClass().getSimpleName() + " start.");
		_start();
		LogUtils.logInfo(logger, this.getClass().getSimpleName() + " successfully end.");
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
