package com.boco.noc.agent.cm.collector;

import java.util.concurrent.Callable;

import com.boco.noc.agent.cm.info.CfgInfo;

public interface Collector extends Callable<CfgInfo>{
	void stop();
	CfgInfo get();
}
