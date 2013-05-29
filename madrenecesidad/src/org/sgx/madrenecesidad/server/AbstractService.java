package org.sgx.madrenecesidad.server;

import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.model.MNServiceException;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.users.User;
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
	
	protected void checkUserLogged(User user) throws MNServiceException {
		if (user == null || user.getUserId() == null)
			throw new MNServiceException(MNServiceException.MESSAGE_PERMISSION); 
	}
	
	protected void checkPrecondition(boolean b, String s) throws MNServiceException {
		if(!b)
			throw new MNServiceException("Invalid data: "+s); 
	}

	public abstract Logger LOG(); 
	
	
}
