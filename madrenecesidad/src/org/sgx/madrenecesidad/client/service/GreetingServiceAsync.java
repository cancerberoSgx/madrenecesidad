package org.sgx.madrenecesidad.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void saveEntry(String content, String guestbookName, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
