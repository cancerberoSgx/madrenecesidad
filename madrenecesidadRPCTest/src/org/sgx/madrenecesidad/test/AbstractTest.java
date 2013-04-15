package org.sgx.madrenecesidad.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.CookieManager;

import org.sgx.madrenecesidad.client.service.ChannelService;
import org.sgx.madrenecesidad.client.service.TagService;

import com.gdevelop.gwt.syncrpc.SyncProxy;

public abstract class AbstractTest {
	
	protected static final String MODULE_BASE_URL = "http://127.0.0.1:8888/appenginetest1/"; 
	
	//on appengine
//	protected static final String MODULE_BASE_URL = "http://madrenecesidad.appspot.com/appenginetest1/";	
//	http://madrenecesidad.appspot.com/appenginetest1/tagService
	
//	protected static ChannelService channelService = (ChannelService) SyncProxy
//		.newProxyInstance(ChannelService.class, MODULE_BASE_URL, "channelService");	
//
//	protected static TagService tagService = (TagService) SyncProxy
//		.newProxyInstance(TagService.class, MODULE_BASE_URL, "tagService");

//	private ChannelService channelService=null;
//	private TagService tagService=null;
	ChannelService newChannelService() {
		
		CookieManager userSession;
		try {
			userSession = LoginUtils.loginAppEngine("http://127.0.0.1:8888",
				    "http://example.appspot.com/helloApp/greet",
				    "test@example.com", "");
			if(userSession==null) {
				fail(); 
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail(); 
			return null;
		}
		
//		if(channelService==null)
//			channelService=
			return (ChannelService) SyncProxy
					.newProxyInstance(ChannelService.class, MODULE_BASE_URL, "channelService", userSession);	
//		return channelService; 
	}
	TagService newTagService() {
		CookieManager userSession;
		try {
			userSession = LoginUtils.loginAppEngine("http://127.0.0.1:8888",
				    "http://example.appspot.com/helloApp/greet",
				    "test@example.com", "");
			if(userSession==null) {
				fail(); 
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail(); 
			return null;
		}
		
//		if(tagService==null)
//			tagService=
					return (TagService) SyncProxy
					.newProxyInstance(TagService.class, MODULE_BASE_URL, "channelService", userSession);	
//		return tagService; 
	}
//	protected void deleteAllChannels() {
//		getChannelService().cleanAll(); 		
//	}
//	protected void deleteAllTags() {
//		getTagService().cleanAll(); 
//		try {
//			Thread.sleep(timeout1 );
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} 	
//	}
}
