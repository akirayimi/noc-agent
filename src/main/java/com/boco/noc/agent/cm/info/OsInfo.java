package com.boco.noc.agent.cm.info;

/**
 * the information of operating system.
 * @author someone
 *
 */
@SuppressWarnings("serial")
public class OsInfo extends AbstractCfgInfo{
	@Identifier
	public final static String HOST_NAME = "cm.os.hostName";
	@Identifier
	public final static String VENDER_NAME = "cm.os.vendorName";
	@Identifier
	public final static String RELEASE_TYPE = "cm.os.type"; 
	@Identifier
	public final static String VERSION = "cm.os.version";
}
