package com.boco.noc.agent.cm.collector.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.boco.noc.agent.cm.collector.Collector;
import com.boco.noc.agent.cm.collector.CpuCollector;
import com.boco.noc.agent.cm.collector.OSCollector;
import com.boco.noc.agent.cm.information.CfgInfo;
import com.boco.noc.agent.util.LogUtils;

public class CollectorFactory {
	private final static Logger logger = Logger.getLogger(CollectorFactory.class);
	private final static Map<String, Collector<? extends CfgInfo>> collectorMap = new HashMap<String, Collector<? extends CfgInfo>>();
	
	private final static ReentrantLock lock = new ReentrantLock();
	
	@SuppressWarnings("unchecked")
	public static Collector<? extends CfgInfo> getCollector(Class<? extends Collector> clazz) {
		Collector<? extends CfgInfo> c = collectorMap.get(clazz.getCanonicalName());
		if (c == null) {
			lock.lock();
			try {
				c = collectorMap.get(clazz.getCanonicalName());
				if (c == null) {
					c = clazz.newInstance();
					collectorMap.put(clazz.getCanonicalName(), c);
				}
			} catch (Throwable e) {
				LogUtils.logError(logger, "init " + clazz.getName() + " collector failed!", e);
			} finally {
				lock.unlock();
			}
		}
		return c;
	}

	public static void main(String[] args) {
		for (Entry<String, String> entry :getCollector(CpuCollector.class).start().get()){
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
