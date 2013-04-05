package org.sgx.madrenecesidad.server;

import static org.sgx.madrenecesidad.server.OfyService.ofy;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Owned;
import org.sgx.madrenecesidad.client.service.MapViewService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Objectify;

public class MapViewServiceImpl extends AbstractService implements MapViewService {
	private static final Logger LOG = Logger.getLogger(MapViewServiceImpl.class.getName());
	
	@Override
	public boolean add(MapView mv) {
		if(mv == null)
			return false; 
		 UserService userService = UserServiceFactory.getUserService();
	     User user = userService.getCurrentUser();
	     if(user==null)
	    	 return false; //only registered users can add things
	     mv.setOwnerId(user.getUserId()); 	     
//	     String useremail = user.getEmail();
	     try {
	 		Objectify ofy = ofy(); 
	 		ofy.save().entity(mv).now();
//	 		ofy.getTxn().commit(); 
	 		
	 		String logMsg1 = "added/updated mapview Entity datastore " + mv.getName();
	 		LOG.info(logMsg1);
	 	} catch (Exception e) {
	 		String errMsg = "Failed to add/(update mapview" + mv.getName() + ". Error: " + e;
	 		LOG.log(Level.SEVERE, errMsg);
	 		return false; 
	 	}
		return true;
	}

	@Override
	public boolean remove(MapView mv) {

		if(mv==null)
			return false;  
		try {
			Objectify ofy = ofy(); 
			ofy.delete().entity(mv).now(); 
			LOG.info("deleted mapview from DATASTORE: "+mv); 
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to delete mapview "+mv+" from DATASTORE", e);
			return false; 
		}
		
		return true; 
	}

	@Override
	public List<MapView> list() {
		List<MapView> ret = new LinkedList<MapView>(); 
		try {
			
			List<MapView> alltags = ofy().load().type(MapView.class).list();	
			for(MapView t : alltags) {
				ret.add(t); 
			}
			LOG.info("MapView list: "+ret.size()); 
			return ret; 
			
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to MapView list()", e);
			return ret; 
		}
	}

	@Override
	public Logger LOG() {
		return LOG;
	}

}
