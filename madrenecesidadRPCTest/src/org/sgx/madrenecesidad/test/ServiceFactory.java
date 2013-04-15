package org.sgx.madrenecesidad.test;

import java.net.CookieManager;

import org.sgx.madrenecesidad.client.service.ChannelService;

import com.gdevelop.gwt.syncrpc.SyncProxy;
/**
 * 
 * @author sg
 *
 */
public class ServiceFactory {
public static <T> T newService(Class<T> serviceClass, String moduleBaseUrl, String serviceName) {
	return (T)SyncProxy.newProxyInstance(ChannelService.class, moduleBaseUrl, serviceName);	
}

public static <T> T newService(Class<T> serviceClass, String moduleBaseUrl, String serviceName, CookieManager userSession) {
	return (T)SyncProxy.newProxyInstance(ChannelService.class, moduleBaseUrl, serviceName, userSession);	
}
}
