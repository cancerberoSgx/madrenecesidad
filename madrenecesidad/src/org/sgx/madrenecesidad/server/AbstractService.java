package org.sgx.madrenecesidad.server;

import java.util.logging.Logger;

import com.google.appengine.api.search.Document;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author sg
 * 
 */
public abstract class AbstractService extends RemoteServiceServlet {

	private static final long serialVersionUID = 1L;
	
	protected String getOnlyField(Document doc, String fieldName, String defaultValue) {
		if (doc.getFieldCount(fieldName) == 1) {
			return doc.getOnlyField(fieldName).getText();
		}
		LOG().severe("Field " + fieldName + " present " + doc.getFieldCount(fieldName));
		return defaultValue;
	}
	public abstract Logger LOG(); 
}
