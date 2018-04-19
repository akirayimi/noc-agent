package com.boco.noc.agent;

import com.alibaba.fastjson.JSONObject;

/**
 * after one 
 * @author someone
 *
 */
public class ResultData {
	private JSONObject obj;
	
	public ResultData(){
		this.obj = new JSONObject();
	}
	
	public String getMetric() {
		return obj.getString("metric");
	}
	public void setMetric(String metric) {
		obj.put("metric", metric);
	}
	public String getEndpoint() {
		return obj.getString("endpoint");
	}
	public void setEndpoint(String endpoint) {
		obj.put("endpoint", endpoint);
	}
	public String getTags() {
		return obj.getString("tags");
	}
	public void setTags(String tags) {
		obj.put("tags", tags);
	}
	public String getValue() {
		return obj.getString("value");
	}
	public void setValue(String value) {
		obj.put("value", value);
	}
	public String getTimestamp() {
		return obj.getString("timestamp");
	}
	public void setTimestamp(String timestamp) {
		obj.put("timestamp", timestamp);
	}
	public String getCounterType() {
		return obj.getString("counterType");
	}
	public void setCounterType(String counterType) {
		obj.put("counterType", counterType);
	}
	public String getStep() {
		return obj.getString("step");
	}
	public void setStep(String step) {
		obj.put("step", step);
	}
	@Override
	public String toString() {
		return obj.toString();
	}
}
