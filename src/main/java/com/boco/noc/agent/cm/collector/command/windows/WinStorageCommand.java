package com.boco.noc.agent.cm.collector.command.windows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.collector.command.DiskCommand;
import com.boco.noc.agent.cm.info.CfgInfo;

public abstract class WinStorageCommand extends DiskCommand {
	
	
	protected void putTotalSize(CfgInfo info, String identifier, String modelCmd, String modelSizeCmd) {
		String modelEcho = exec(modelCmd);
		String[] results = modelEcho.split(Global.LINE_SEPERATOR);
		JSONObject jobj = new JSONObject();
		List<SingleStorageDetail> list = new ArrayList<SingleStorageDetail>();
		int totalGB = 0;
		
		if (results != null && results.length > 1) {
			for (int i = 1; i < results.length; i++) {
				SingleStorageDetail ssd = new SingleStorageDetail();
				ssd.setModel(results[i].trim());
				list.add(ssd);
			}
		}
		
		String modelSizeEcho = exec(modelSizeCmd);
		results = modelSizeEcho.split(Global.LINE_SEPERATOR);
		if (results != null && results.length > 1) {
			for (int i = 1; i < results.length; i++) {
				totalGB += putSizeInMap(list, results[i]);
			}
		}
		
		jobj.put("detail", list);
		jobj.put("total", totalGB + "GB");
		info.put(identifier, jobj.toJSONString());
	}
	
	private int putSizeInMap(List<SingleStorageDetail> list, String echoLine){
		int total = 0;
		for (SingleStorageDetail ssd : list){
			String model = ssd.getModel();
			if (echoLine.contains(model)){
				String sizeStr = echoLine.replace(model, Global.BLANK);
				long size = Long.valueOf(sizeStr.trim());
				ssd.setSize(size);
				total = (int) (size >> 30);
			}
		}
		return total;
	}

	@Override
	protected abstract void putTotalSize(CfgInfo info);

	class SingleStorageDetail {
		String model;
		long size; // byte

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getSize() {
			return (size >> 30) + "GB";
		}

		public void setSize(long size) {
			this.size = size;
		}

		public int toGB() {
			return (int) (size >> 30);
		}
	}
}
