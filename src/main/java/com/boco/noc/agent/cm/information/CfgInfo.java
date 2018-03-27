package com.boco.noc.agent.cm.information;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * the super class of config information.
 * the subclass of CfgInfo define the identifier of each indicator. 
 * @author someone
 *
 */
public interface CfgInfo extends Iterable<Map.Entry<String, String>>, Serializable{
	/**
	 * if one indicator has not been implemented, use this field as it's value.
	 */
	static final String NOT_IMPLEMENT = "NOT_IMPLEMENT";
	
	void put(String identifer, String value);
	String get(String identifer);
	
	
}

