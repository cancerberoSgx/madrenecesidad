package org.sgx.madrenecesidad.server.core;

import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.model.User;

import com.google.appengine.api.search.Document;


public abstract class AbstractCoreService {

	protected String getOnlyField(Document doc, String fieldName, String defaultValue) {
		if (doc.getFieldCount(fieldName) == 1) {
			return doc.getOnlyField(fieldName).getText();
		}
		LOG().severe("Field " + fieldName + " present " + doc.getFieldCount(fieldName));
		return defaultValue;
	}
	public abstract Logger LOG(); 
	
	private User user;
	public User getUser() {
		return user; 
	}
	public void setUser(User user) {
		this.user = user; 
	}
}
