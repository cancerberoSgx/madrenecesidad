package org.sgx.madrenecesidad.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String saveEntry(String content, String guestbookName);
	String greetServer(String name) throws IllegalArgumentException;

}
