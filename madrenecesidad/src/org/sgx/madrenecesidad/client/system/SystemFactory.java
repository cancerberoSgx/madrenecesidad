package org.sgx.madrenecesidad.client.system;

public class SystemFactory {
/**
 * The shared instance.
 */
private static SystemFactory instance = new SystemFactory();
private ChannelSystem channelSystem = null;

/**
 * Private constructor.
 */
private SystemFactory() {
	super();
}

/**
 * Returns this shared instance.
 *
 * @returns The shared instance
 */
public static SystemFactory getInstance() {
	return instance;
}

public ChannelSystem getChannelSystem() {
	if(this.channelSystem==null)
		this.channelSystem = new ChannelSystem(); 
	return this.channelSystem; 
}
}
