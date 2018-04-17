package com.boco.noc.agent.pm.checker.handler;

/**
 * Normally, after run a script in a shell, the reply contains some unnecessary imformation.
 * like "[root@VM_44_171_centos ~]#", so use {@see EchoHandler#handle(String)} to handle the reply.
 * 
 * @author someone
 */
public interface ReplyHandler {
	String handle(String echo);
}
