package com.boco.noc.agent.cm.convert;

import java.util.List;

import com.boco.noc.agent.ResultData;
import com.boco.noc.agent.cm.info.CfgInfo;

public interface Converter {
	List<ResultData> convert(CfgInfo info);
}
