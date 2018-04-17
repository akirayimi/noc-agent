package com.boco.noc.agent.cm.convert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.boco.noc.agent.Global;
import com.boco.noc.agent.ResultData;
import com.boco.noc.agent.cm.info.CfgInfo;

public class StdConverter implements Converter{
	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	@Override
	public List<ResultData> convert(CfgInfo info) {
		List<ResultData> ret = new ArrayList<ResultData>();
		for (Entry<String, String> entry : info){
			ResultData data = new ResultData();
			data.setMetric(entry.getKey());
			data.setValue(entry.getValue());
			data.setCounterType("ConterType");
			data.setStep("24h");
			data.setTags("");
			data.setTimestamp(df.format(new Date()));
			data.setEndpoint(Global.IP);
			ret.add(data);
		}
		return ret;
	}
}
