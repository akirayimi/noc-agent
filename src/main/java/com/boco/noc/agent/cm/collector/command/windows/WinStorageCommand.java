package com.boco.noc.agent.cm.collector.command.windows;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boco.noc.agent.Global;
import com.boco.noc.agent.cm.collector.command.DiskCommand;
import com.boco.noc.agent.cm.info.CfgInfo;

public abstract class WinStorageCommand extends DiskCommand {
	enum SizeOrder{SIZE_FIRST, SIZE_LAST}
	
	protected void putTotalSize(CfgInfo info, String identifier, String cmd){
		putTotalSize(info, identifier, cmd, SizeOrder.SIZE_LAST);
	}
	
	protected void putTotalSize(CfgInfo info, String identifier, String cmd, SizeOrder order) {
		String echo = exec(cmd);
		String[] results = echo.split(Global.LINE_SEPERATOR);
		JSONObject jobj = new JSONObject();
		List<SingleStorageDetail> list = new ArrayList<SingleStorageDetail>();
		int totalGB = 0;
		if (results != null && results.length > 1) {
			for (int i = 1; i < results.length; i++) {
				SingleStorageDetail sdd = new SingleStorageDetail();
				String[] details = results[i].split("\\s+");
				
				if (order == SizeOrder.SIZE_FIRST){
					sdd.setSize(Long.valueOf(details[0].trim()));
					sdd.setModel(details[1].trim());
				} else {
					sdd.setModel(details[0].trim());
					sdd.setSize(Long.valueOf(details[1].trim()));
				}
				totalGB += sdd.toGB();
				list.add(sdd);
			}
		}
		jobj.put("detail", list);
		jobj.put("total", totalGB + "GB");
		info.put(identifier, jobj.toJSONString());
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
