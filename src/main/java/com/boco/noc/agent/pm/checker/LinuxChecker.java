package com.boco.noc.agent.pm.checker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.boco.noc.agent.ResultData;
import com.boco.noc.agent.pm.checker.handler.DoNothingHandler;
import com.boco.noc.agent.pm.checker.handler.ReplyHandler;
import com.boco.noc.agent.pm.data.Performance;
import com.boco.noc.agent.util.RuntimeExecutor;

public class LinuxChecker implements Checker{
	private final static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	@Override
	public void check(Performance p, ResultData data) {
		check(p, data, new DoNothingHandler());
	}
	
	
	@Override
	public void check(Performance p, ResultData data, ReplyHandler handler) {
		String file = p.getFilepath();
		String shellcmd = ".//" + file;
		String echo = RuntimeExecutor.run(shellcmd);
		data.setValue(handler.handle(echo));
		data.setTimestamp(sdf.format(new Date()));
		data.setMetric(p.getName());
		data.setStep(String.valueOf(p.getFrequency()));
		
	}
}
