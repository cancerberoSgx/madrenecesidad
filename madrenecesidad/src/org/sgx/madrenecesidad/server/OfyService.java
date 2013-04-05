package org.sgx.madrenecesidad.server;

//import org.datanucleus.store.appengine.DatastoreManager;
import org.sgx.madrenecesidad.client.MNConstants;
import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Tag;
import org.sgx.madrenecesidad.client.model.User;

import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.googlecode.objectify.*;

public class OfyService {
    static {
        factory().register(Tag.class);
        factory().register(Channel.class);
        factory().register(MapView.class);
//        factory().register(User.class);
//        factory().register(User.class);
        
    }

//	private long timeout1 = 2000;	
	
//    private static Objectify _devel_ofy = null;  
    public static Objectify ofy() {
//    	if(Constants.develmode) {
//    		MemcacheServiceFactory.getMemcacheService().clearAll();
//    		if(_devel_ofy==null) {
//    			_devel_ofy = ObjectifyService.ofy().cache(false);
//    		}
//			_devel_ofy.clear(); 
//    		return _devel_ofy; 
//    				
////    		Objectify ret = ObjectifyService.ofy().cache(false);    		
////    		ret.clear(); 
////    		return ret;  
//    	}
//    	else 
    		return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}