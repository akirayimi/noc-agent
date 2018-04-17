package com.boco.noc.agent.cm.collector.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.boco.noc.agent.cm.collector.Collector;
import com.boco.noc.agent.util.LogUtils;

public class CollectorFactory {
	private final static Logger logger = Logger.getLogger(CollectorFactory.class);
	private final static Map<String, Collector> collectorMap = new HashMap<String, Collector>();
	
	private final static ReentrantLock lock = new ReentrantLock();
	
	public static Collector getCollector(Class<? extends Collector> clazz) {
		Collector c = collectorMap.get(clazz.getCanonicalName());
		if (c == null){
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
}
