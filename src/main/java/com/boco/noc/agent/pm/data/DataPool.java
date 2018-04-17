package com.boco.noc.agent.pm.data;

import java.util.HashMap;
import java.util.List;

import com.boco.noc.agent.ResultData;
import com.boco.noc.agent.pm.checker.LinuxChecker;

public class DataPool {
	final static HashMap<String, ResultData> dataPool = new HashMap<String, ResultData>();
	final static LinuxChecker linuxChecker = new LinuxChecker();
	final static List<Performance> all = PerformanceGen.all();

	static {
		for (Performance p : all) {
			ResultData data = dataPool.get(p.getName());
			if (data == null) {
				data = new ResultData();
				dataPool.put(p.getName(), data);
			}
		}
	}
	
	public static ResultData getResultData(Performance p){
		return dataPool.get(p.getName());
	}
}
