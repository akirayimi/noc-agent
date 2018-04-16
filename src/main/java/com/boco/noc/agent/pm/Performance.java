package com.boco.noc.agent.pm;

public class Performance {
	String name;
	String zhname;
	String filepath;
	String frequency;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZhname() {
		return zhname;
	}
	public void setZhname(String zhname) {
		this.zhname = zhname;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\",\"zhname\":\"" + zhname + "\",\"frequency\":\"" + frequency
				+ "\",\"filepath\":\"" + filepath + "\"} ";
	}
	public int getFrequency() {
		return Integer.valueOf(frequency);
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
}
