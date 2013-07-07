package org.sgx.madrenecesidad.server;

import static org.sgx.madrenecesidad.server.OfyService.ofy;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.MNServiceException;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Owned;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.service.MapViewService;
import org.sgx.madrenecesidad.client.util.MNUtil;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.PutResponse;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

public class MapViewServiceImpl extends AbstractService implements MapViewService {

	private static final String INDEX_NAME = "mapview_index";
	private static final Logger LOG = Logger.getLogger(MapViewServiceImpl.class.getName());
	private static final String FIELD_NAME = "name";
	private static final String FIELD_DESCRIPTION = "description";
	/**
	 * The index used by this application. Since we only have one index we create one instance only. We build an index with the default consistency, which is
	 * Consistency.PER_DOCUMENT. These types of indexes are most suitable for streams and feeds, and can cope with a high rate of updates.
	 */
	private static final Index INDEX = SearchServiceFactory.getSearchService().getIndex(
			IndexSpec.newBuilder().setName(INDEX_NAME));

	private static final int SEARCH_LIMIT = 200;
	@Override
	public MapView get(Long id)  throws MNServiceException {
	     MapView model = ofy().load().type(MapView.class).id(id).safeGet();	     
	     if(model.isPrivate()) {
	    	 UserService userService = UserServiceFactory.getUserService();
		     User user = userService.getCurrentUser();
		     if(user.getUserId().equals(model.getOwnerId()))
		    	 return model;
		     else
		    	 throw new MNServiceException("don't have permission to view this private mapview");		    	 
	     }
	     else	    	 
	    	 return model; 
	}
	
	@Override
	public Long add(MapView mv) throws MNServiceException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		checkPrecondition(MNUtil.notNull(mv.getName()) && mv.getCenter() != null, 
			"Invalid name or location coordinates");
		
		checkUserLogged(user);
		
		mv.setOwnerId(user.getUserId());

		// create a new index indexId will be updated !
		
		if (MNUtil.isNull(mv.getDescription()))
			mv.setDescription("");
		Document.Builder docBuilder = Document.newBuilder()
				.addField(Field.newBuilder().setName(FIELD_NAME).setText(mv.getName()))
				.addField(Field.newBuilder().setName(FIELD_DESCRIPTION).setText(mv.getDescription()));

		if (mv.getIndexId() != null) {
			docBuilder.setId(mv.getIndexId()); 
		}
		Document doc = docBuilder.build();
		String indexId = null;

		try {
			PutResponse addresp = INDEX.put(doc); 
			indexId = addresp.getIds().get(0);
			// List<String> ids = ;
			LOG.info("Adding/updating mapview to search index:\n" + doc.toString() + " - doc_id: " + indexId);
		} catch (RuntimeException e) {
			String errMsg = "Failed to add/update index " + doc + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg, e);
			throw new MNServiceException(errMsg, e);
		}
		if (indexId == null) {
			String msg = "addMapView - index to the document returned a null indexId - canceling"; 
			LOG.log(Level.SEVERE, msg);
			// TODO: delete the document from index ?
			throw new MNServiceException(msg);
		}
		mv.setIndexId(indexId);

		// and now save or update the entity to the datastore.
		try {
			Objectify ofy = ofy();
			Key<MapView> key = ofy.save().entity(mv).now();

			String logMsg1 = "added/updated mapview Entity datastore " + mv.getName();
			LOG.info(logMsg1);

			return key.getId();

		} catch (Exception e) {
			String errMsg = "Failed to add/(update tag" + mv.getName() + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg);
			throw new MNServiceException(errMsg, e);
		}
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
	public List<MapView> searchMapView(String nameFragment) {
		// see http://code.google.com/p/google-app-engine-samples/source/browse/trunk/search/java/src/com/google/appengine/demos/search/TextSearchServlet.java
		com.google.appengine.api.search.Query query = com.google.appengine.api.search.Query
				.newBuilder()
				.setOptions(
						com.google.appengine.api.search.QueryOptions.newBuilder().setLimit(SEARCH_LIMIT)
								.setFieldsToReturn("name").setFieldsToSnippet("name").
								// for deployed apps, uncomment the line below to demo snippeting.
								// This will not work on the dev_appserver.
								// setFieldsToSnippet("content").
								build()).build(nameFragment);

		Results<ScoredDocument> results = INDEX.search(query);
		List<MapView> channelList = new LinkedList<MapView>();
		for (ScoredDocument scoredDoc : results) {
			MapView ch = new MapView();
			String nameFieldValue = getOnlyField(scoredDoc, FIELD_NAME, "defaultValue");
			// LOG().info("search name found: "+nameFieldValue);
			ch.setName(nameFieldValue);
			channelList.add(ch);
		}
		LOG().info(
				"searchChannel keyword: " + nameFragment + ", searchresults: " + results.getResults().size()
						+ ", returned channelList: " + channelList.size());
		return channelList;
	}
	
	
	
	@Override
	public List<MapView> searchMapView(String nameFragment, int size, int page) {
		List<MapView> l = searchMapView(nameFragment);
		if (l == null)
			return null;
		else if (l.size() > size * page)
			return l.subList(size * page, Math.min(size * (page + 1), l.size() - 1));
		else
			return null;
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
