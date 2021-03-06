package com.boco.noc.agent.pm.checker;

import com.boco.noc.agent.ResultData;
import com.boco.noc.agent.pm.checker.handler.ReplyHandler;
import com.boco.noc.agent.pm.data.Performance;

public interface Checker{
	/**
	 * Check against the information provided by the performance metrics
	 * and then put the result back into the data 
	 * @param p  
	 * @param data
	 * @param handler
	 */
	void check(Performance p, ResultData data, ReplyHandler handler);
	
	void check(Performance p, ResultData data);
}
