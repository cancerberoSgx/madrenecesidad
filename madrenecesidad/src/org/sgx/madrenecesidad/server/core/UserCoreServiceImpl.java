package org.sgx.madrenecesidad.server.core;

import static org.sgx.madrenecesidad.server.OfyService.ofy;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.User;

import com.google.appengine.api.search.AddResponse;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.SearchServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

public class UserCoreServiceImpl extends AbstractCoreService implements
		UserCoreService {

	private static final Logger LOG = Logger
			.getLogger(TagServiceLocalImpl.class.getName());


	private static final String INDEX_NAME = "user_index";
	private static final String FIELD_NICKNAME = "nickname";
	private static final String FIELD_EMAIL = "email";
	
	/**
	 * The index used by this application. Since we only have one index we
	 * create one instance only. We build an index with the default consistency,
	 * which is Consistency.PER_DOCUMENT. These types of indexes are most
	 * suitable for streams and feeds, and can cope with a high rate of updates.
	 */
	private static final Index INDEX = SearchServiceFactory.getSearchService().getIndex(
		IndexSpec.newBuilder().setName(INDEX_NAME));

	private static final int SEARCH_LIMIT = 200;
	
	public String addUser(User s, User u) {
		//create a new index indexId will be updated !
		
		Document.Builder docBuilder = Document.newBuilder()
			.addField(Field.newBuilder().setName(FIELD_NICKNAME).setText(u.getNickname()))
			.addField(Field.newBuilder().setName(FIELD_EMAIL).setText(u.getEmail()));
		
		if(u.getIndexId()!=null) {
			docBuilder.setId(u.getIndexId()); //.addField(Field.newBuilder().setName("doc_id").setText(ch.getName())); 
		}
	
		Document doc = docBuilder.build();
		String indexId=null; 
		
		try {
			AddResponse addresp = INDEX.add(doc);
			indexId = addresp.getIds().get(0); 
//					List<String> ids = ; 
			LOG.info("Adding/updating user to search index:\n" + doc.toString()+" - doc_id: "+indexId);
		} catch (RuntimeException e) {
			String errMsg = "Failed to add/update user index " + doc + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg, e);
		}
		if(indexId==null) {
			LOG.log(Level.SEVERE, "addUser - index to the document returned a null indexId - canceling");
			//TODO: delete the document from index ? 
			return null; 
		}
		u.setIndexId(indexId); 	
			
		//and now save or update the entity to the datastore. 
		try {
			Objectify ofy = ofy(); 
			ofy.save().entity(u).now();			
			String logMsg1 = "added/updated user Entity datastore " + u.getEmail();
			LOG.info(logMsg1);
		} catch (Exception e) {
			String errMsg = "Failed to add/(update tag" + u.getEmail() + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg);
		}
		return u.getEmail(); 
	}

	public User getById(User s, String email) {
		try {
			Ref<User> ref = ofy().load().key(Key.create(User.class, email));
			User fetched1 = ref.get();
			return fetched1;
		} catch (Exception e) {
			String errMsg = "Failed to getUserById " + email + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg, e);
			return null;
		}
	}

	public void removeUser(User s, User u) {
		if(u==null)
			return ; 
		try {
			Objectify ofy = ofy(); 
			ofy.delete().entity(u).now(); 
//			ofy.getTxn().commit(); 		
			LOG().info("deleted user from DATASTORE: "+u); 
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to delete user "+u+" from DATASTORE", e);
		}
		
		try {
			//and delete from indexes to
			if(u.getIndexId()!=null && !u.getIndexId().equals("")) {
				INDEX.remove(u.getIndexId());
				LOG().info("deleted user from INDEX: "+u+" - indexId: "+u.getIndexId());
			}		 
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to delete user "+u+" from INDEX", e);
		}
	}

	@Override
	public Logger LOG() {
		return LOG; 
	}

}
