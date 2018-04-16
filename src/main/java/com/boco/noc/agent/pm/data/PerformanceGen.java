package com.boco.noc.agent.pm.data;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.boco.noc.agent.util.RuntimeExecutor;

/**
 * Depending on the operating system, return the list of performance.
 * always invoke the static method {@link #all()}
 * @author someone
 *
 */
public class PerformanceGen {
	private static List<Performance> cache_performance = null;
	private static final Lock lock = new ReentrantLock();
	private static final String PERFORMANCE_PATH = "/pm/pm.xml";
	public static List<Performance> all() {
		if (cache_performance != null)
			return cache_performance;
		try {
			lock.lock();
			if (cache_performance == null)
				performancesInXML();
			return cache_performance;
		} finally {
			lock.unlock();
		}
	}

	private static void performancesInXML() {
		List<Performance> list = new ArrayList<Performance>();
		//String release = RuntimeExecutor.run("lsb_release -d");
		String release = "CentOS release 6.4 (Final)";
		parseXML(release, list);
		cache_performance = list; //Assigning a reference is an atomic operation 
	}
	
	private static void parseXML(String release, List<Performance> list) {
		Document doc = null;
		try {
			doc = Jsoup.parse(new File(PerformanceGen.class.getResource(PERFORMANCE_PATH).getFile()), "utf8");
			Elements oss = doc.getElementsByTag("os");
			for (Element os : oss){
				if (release.toLowerCase().contains(os.attr("Version").toLowerCase())){
					Elements perEs = os.children();
					convertEleToPerformance(perEs, list);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static void convertEleToPerformance(Elements perEs, List<Performance> list) {
		for (Element perE : perEs){
			Performance p = new Performance();
			assignForPerformance(p, perE.attributes());
			list.add(p);
		}
	}

	private static void assignForPerformance(Performance p, Attributes attributes) {
		for (Attribute a : attributes){
			try {
				Field f = p.getClass().getDeclaredField(a.getKey());
				f.set(p, a.getValue());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} 
		}
	}

	public static void main(String[] args) {
		System.out.println(all());;
	}
	
}
