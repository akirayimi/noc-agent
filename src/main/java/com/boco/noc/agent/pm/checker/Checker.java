package com.boco.noc.agent.pm.checker;

import com.boco.noc.agent.pm.data.Performance;
import com.boco.noc.agent.pm.data.ResultData;

public interface Checker{
	/**
	 * Check against the information provided by the performance metrics
	 * and then put the result back into the data 
	 * @param p 
	 * @param data
	 */
	void check(Performance p, ResultData data);
}
