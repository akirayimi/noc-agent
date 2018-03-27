package com.boco.noc.agent.cm.collector;

import com.boco.noc.agent.cm.information.CfgInfo;

public interface Collector<T extends CfgInfo> {
	void stop();
	Collector<? extends CfgInfo> start();
	T get();
}
