package org.sgx.madrenecesidad.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.CookieManager;

import org.sgx.madrenecesidad.client.service.ChannelService;
import org.sgx.madrenecesidad.client.service.PlaceService;
import org.sgx.madrenecesidad.client.service.TagService;

import com.gdevelop.gwt.syncrpc.SyncProxy;

public abstract class AbstractTest {

	protected static final String SERVERURL = "http://127.0.0.1:8888",
			MODULE_BASE_URL = SERVERURL + "/appenginetest1/", 
			USER_EMAIL="test@example.com", USER_PSSWRD="";

	
	PlaceService newPlaceService() {
		CookieManager userSession;
		try {
			userSession = LoginUtils.loginAppEngine(SERVERURL, MODULE_BASE_URL + "placeService", 
					USER_EMAIL, USER_PSSWRD);
			if (userSession == null) {
				fail();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
			return null;
		}

		return (PlaceService) SyncProxy.newProxyInstance(PlaceService.class, MODULE_BASE_URL, 
				"placeService", userSession);

	}
	
	
	ChannelService newChannelService() {
		CookieManager userSession;
		try {
			userSession = LoginUtils.loginAppEngine(SERVERURL, MODULE_BASE_URL + "channelService", 
					USER_EMAIL, USER_PSSWRD);
			if (userSession == null) {
				fail();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
			return null;
		}

		return (ChannelService) SyncProxy.newProxyInstance(ChannelService.class, MODULE_BASE_URL, 
				"channelService", userSession);

	}

	TagService newTagService() {
		CookieManager userSession;
		try {
			userSession = LoginUtils.loginAppEngine(SERVERURL, MODULE_BASE_URL + "tagService", 
					USER_EMAIL, USER_PSSWRD);
			if (userSession == null) {
				fail();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
			return null;
		}

		return (TagService) SyncProxy.newProxyInstance(TagService.class, MODULE_BASE_URL, 
				"tagService", userSession);

	}
}
