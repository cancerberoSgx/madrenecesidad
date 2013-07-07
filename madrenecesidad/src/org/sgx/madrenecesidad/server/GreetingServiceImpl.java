package org.sgx.madrenecesidad.server;

import org.sgx.madrenecesidad.client.service.GreetingService;
//import org.sgx.madrenecesidad.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	
	public String saveEntry(String content, String guestbookName) {
		  UserService userService = UserServiceFactory.getUserService();
	        User user = userService.getCurrentUser();

//	        String guestbookName = req.getParameter("guestbookName");
	        Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
//	        String content = req.getParameter("content");
	        Date date = new Date();
	        Entity greeting = new Entity("Greeting", guestbookKey);
	        greeting.setProperty("user", user);
	        greeting.setProperty("date", date);
	        greeting.setProperty("content", content);

	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	        datastore.put(greeting);
	        return "OK"; 
	}
	
	
	
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
//		if (!FieldVerifier.isValidName(input)) {
//			// If the input is not valid, throw an IllegalArgumentException back to
//			// the client.
//			throw new IllegalArgumentException("Name must be at least 4 characters long");
//		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
