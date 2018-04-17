package com.boco.noc.agent.pm.checker.handler;

public class DoNothingHandler implements ReplyHandler{
	@Override
	public String handle(String echo) {
		return echo;
	}
}
