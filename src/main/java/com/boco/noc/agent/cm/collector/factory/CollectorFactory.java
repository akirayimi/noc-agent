package com.boco.noc.agent.cm.collector.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.boco.noc.agent.cm.collector.Collector;
import com.boco.noc.agent.cm.collector.CpuCollector;
import com.boco.noc.agent.cm.collector.DiskCollector;
import com.boco.noc.agent.cm.collector.MemoryCollector;
import com.boco.noc.agent.cm.collector.OSCollector;
import com.boco.noc.agent.cm.info.CfgInfo;
import com.boco.noc.agent.util.LogUtils;

public class CollectorFactory {
	private final static Logger logger = Logger.getLogger(CollectorFactory.class);
	private final static Map<String, Collector<? extends CfgInfo>> collectorMap = new HashMap<String, Collector<? extends CfgInfo>>();
	
	private final static ReentrantLock lock = new ReentrantLock();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		List<Class<? extends Collector>> colList = new ArrayList<Class<? extends Collector>>();
		colList.add(DiskCollector.class);
		colList.add(CpuCollector.class);
		colList.add(OSCollector.class);
		colList.add(MemoryCollector.class);
		
		for (Class<? extends Collector> clazz : colList){
			for (Entry<String, String> entry :getCollector(clazz).start().get()){
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
		}
	}
}
