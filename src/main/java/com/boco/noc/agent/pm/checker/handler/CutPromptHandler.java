package com.boco.noc.agent.pm.checker.handler;

import java.util.regex.Pattern;

import com.boco.noc.agent.Global;

public class CutPromptHandler implements ReplyHandler{
	private static final String PROMPT_REGEX = "\\[.+?@.+?\\][#$]\\s*";
	private static Pattern prompt = Pattern.compile(PROMPT_REGEX);
	@Override
	public String handle(String reply) {
		String[] lines = reply.split(Global.LINE_SEPERATOR);
		StringBuilder message = new StringBuilder();
		for(String line : lines){
			if(!isPromt(line))
				message.append(line).append(Global.LINE_SEPERATOR);
		}
		return message.toString();
	}
	
	private boolean isPromt(String line) {
		return prompt.matcher(line).find();
	}
}
