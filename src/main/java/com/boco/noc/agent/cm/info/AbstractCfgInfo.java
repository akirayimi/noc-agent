package com.boco.noc.agent.cm.info;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.boco.noc.agent.util.LogUtils;

@SuppressWarnings("serial")
public abstract class AbstractCfgInfo implements CfgInfo{
	protected Map<String, String> map = new HashMap<String, String>();
	@Override
	public void put(String identifer, String value){
		map.put(identifer, value);
	}
	@Override
	public String get(String identifer){
		return map.get(identifer);
	}
	
	@Override
	public Iterator<Entry<String, String>> iterator() {
		return map.entrySet().iterator();
	}
	
	/**
	 * get alias-name map what has been defined in the information class.
	 * <br/>eg: HOST_NAME(alias): cm.os.hostName(name);
	 * @return key set.
	 */
	public Map<String, String> aliasNameMap() {
		Logger logger = Logger.getLogger(this.getClass());
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Field field : this.getClass().getFields()){
				if (field.getAnnotation(Identifier.class) != null) {
					map.put(field.getName(), (String)field.get(field.getName()));
				}
			}
		} catch (Exception e){
			LogUtils.logError(logger, " get alias name map error! ", e);
		}
		LogUtils.logInfo(logger, " get alias name map " + map);
		return map;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(map);
	}
}
