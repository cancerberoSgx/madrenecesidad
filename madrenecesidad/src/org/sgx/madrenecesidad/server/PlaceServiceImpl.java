package org.sgx.madrenecesidad.server;

import static org.sgx.madrenecesidad.server.OfyService.ofy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.MNConstants;
import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.MNServiceException;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.model.Tag;
import org.sgx.madrenecesidad.client.service.ChannelService;
import org.sgx.madrenecesidad.client.service.PlaceService;
import org.sgx.madrenecesidad.client.util.MNUtil;

//import com.google.appengine.api.search.AddResponse;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.PutResponse;
//import com.google.appengine.api.search.ListRequest;
//import com.google.appengine.api.search.ListResponse;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.cmd.Query;

/**
 * The server side implementation of the RPC service for channels
 * 
 * @author sg
 * 
 */
@SuppressWarnings("serial")
public class PlaceServiceImpl extends AbstractService implements PlaceService {

	private static final Logger LOG = Logger.getLogger(PlaceServiceImpl.class.getName());

	private static final String INDEX_NAME = "place_index";
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
	public Long addPlace(Place p) throws MNServiceException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		checkPrecondition(MNUtil.notNull(p.getName()) && p.getCenter() != null, 
			"Invalid name or location coordinates");
		
		checkUserLogged(user);
		
		p.setOwnerId(user.getUserId());

		// create a new index indexId will be updated !

		if (MNUtil.isNull(p.getDescription()))
			p.setDescription("");
		Document.Builder docBuilder = Document.newBuilder()
				.addField(Field.newBuilder().setName(FIELD_NAME).setText(p.getName()))
				.addField(Field.newBuilder().setName(FIELD_DESCRIPTION).setText(p.getDescription()));

		if (p.getIndexId() != null) {
			docBuilder.setId(p.getIndexId()); // .addField(Field.newBuilder().setName("doc_id").setText(ch.getName()));
		}

		Document doc = docBuilder.build();
		String indexId = null;

		try {
			PutResponse addresp = INDEX.put(doc); 
			indexId = addresp.getIds().get(0);
			// List<String> ids = ;
			LOG.info("Adding/updating channel to search index:\n" + doc.toString() + " - doc_id: " + indexId);
		} catch (RuntimeException e) {
			String errMsg = "Failed to add/update index " + doc + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg, e);
			throw new MNServiceException(errMsg, e);
		}
		if (indexId == null) {
			String msg = "addPlace - index to the document returned a null indexId - canceling"; 
			LOG.log(Level.SEVERE, msg);
			// TODO: delete the document from index ?
			throw new MNServiceException(msg);
//			return -1l;
		}
		p.setIndexId(indexId);

		// and now save or update the entity to the datastore.
		try {
			Objectify ofy = ofy();
			Key<Place> key = ofy.save().entity(p).now();

			String logMsg1 = "added/updated channel Entity datastore " + p.getName();
			LOG.info(logMsg1);

			return key.getId();
			// ofy.getTxn().commit();

		} catch (Exception e) {
			String errMsg = "Failed to add/(update tag" + p.getName() + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg);
//			return -1l;
			throw new MNServiceException(errMsg, e);
		}
	}

	@Override
	public Place getPlaceById(Long id) {
		try {
			Ref<Place> ref = ofy().load().key(Key.create(Place.class, id));
			Place fetched1 = ref.get();
			return fetched1;
		} catch (Exception e) {
			String errMsg = "Failed to getPlaceById " + id + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg, e);
			return null;
		}
	}

	@Override
	public List<Place> getAllPlaces() {

		List<Place> ret = new LinkedList<Place>();
		try {

			List<Place> alltags = ofy().load().type(Place.class).list();
			for (Place t : alltags) {
				ret.add(t);
			}
			LOG().info("getPlaces: " + ret.size());
			return ret;

		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to getChannels()", e);
			return ret;
		}

	}

	@Override
	public List<Place> getOwnerPlaces() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null || user.getUserId() == null)
			return null; // TODO: throw something?
		// List<Place> ret = new LinkedList<Place>();
		String userId = user.getUserId();
		try {

			List<Place> alltags = ofy().load().type(Place.class).filter("ownerId =", userId).list();
			LOG().info("getOwnerPlaces: " + alltags.size());
			return alltags;
			// for(Place t : alltags) {
			// ret.add(t);
			// }
			// LOG().info("getPlaces: "+ret.size());
			// return ret;

		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to getChannels()", e);
			return null;
		}

	}

	@Override
	public void deletePlace(Place ch) {

		if (ch == null)
			return;
		try {
			Objectify ofy = ofy();
			ofy.delete().entity(ch).now();
			// ofy.getTxn().commit();
			LOG().info("deleted channel from DATASTORE: " + ch);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to delete channel " + ch + " from DATASTORE", e);
		}

		try {
			// and delete from indexes to
			if (ch.getIndexId() != null && !ch.getIndexId().equals("")) {
				INDEX.delete(ch.getIndexId());
				LOG().info("deleted channel from INDEX: " + ch + " - indexId: " + ch.getIndexId());
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to delete channel " + ch + " from INDEX", e);
		}
	}

	// @Override
	// public List<Tag> getTags(Place c) {
	// // List<Tag> l = ofy().load().type(Tag.class).filter("channel", c.getName()).list();
	// // return l;
	// Iterable<Tag> it = ofy().load().type(Tag.class).filter("channel", c.getName());
	// List<Tag> tags = new LinkedList<Tag>();
	// for (Tag tag : it) {
	// tags.add(tag);
	// }
	// return tags;
	// }
	// @Override
	// public void cleanAll() {
	//
	// // List<Channel> alltags = ofy().load().type(Channel.class).list();
	// // List<Channel> ret = new LinkedList<Channel>();
	// // for (Channel t : ofy().load().type(Channel.class).list()) {
	// // ret.add(t);
	// // ofy().delete().entity(t).now();
	// // }
	// // for(Channel t : ret) {
	// // ofy().delete().entity(t).now();
	// // }
	//
	// List<Channel> allch = getChannels();
	// for (Channel c : allch) {
	// deleteChannel(c);
	// }
	//
	// //clean the search index - from https://developers.google.com/appengine/docs/java/search/overview#Removing_Documents
	// try {
	// while (true) {
	// List<String> docIds = new ArrayList<String>();
	// // Return a set of document IDs.
	// ListRequest request = ListRequest.newBuilder().setKeysOnly(true).build();
	// ListResponse<Document> response = INDEX.listDocuments(request);
	// if (response.getResults().isEmpty()) {
	// break;
	// }
	// for (Document doc : response) {
	// docIds.add(doc.getId());
	// }
	// INDEX.remove(docIds);
	// }
	// } catch (RuntimeException e) {
	// LOG.log(Level.SEVERE, "Failed to remove documents", e);
	// }
	// }

	// @Override
	// public Channel getFullChannelByName(String name) {
	// try {
	// Ref<Channel> ref = ofy().load().key(Key.create(Channel.class, name));
	// Channel fetched1 = ref.get();
	// return fetched1;
	// } catch (Exception e) {
	// String errMsg = "Failed to getChannelByName " + name + ". Error: " + e;
	// LOG.log(Level.SEVERE, errMsg, e);
	// return null;
	// }
	// }

	// @Override
	// public List<Tag> getTagsOf(Channel ch) {
	// try {
	// Ref<Tag> ref = ofy().load().key(Key.create(Tag.class, tagName));
	// Tag fetched1 = ref.get();
	// return fetched1;
	// } catch (Exception e) {
	// String errMsg = "Failed to getTagByName " + tagName + ". Error: " + e;
	// LOG.log(Level.SEVERE, errMsg, e);
	// return null;
	// }
	//
	// }

	@Override
	public List<Place> searchPlace(String nameFragment) {
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
		List<Place> channelList = new LinkedList<Place>();
		for (ScoredDocument scoredDoc : results) {
			Place ch = new Place();
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
	public List<Place> searchPlace(String nameFragment, int size, int page) {
		List<Place> l = searchPlace(nameFragment);
		if (l == null)
			return null;
		else if (l.size() > size * page)
			return l.subList(size * page, Math.min(size * (page + 1), l.size() - 1));
		else
			return null;
	}

	@Override
	public void cleanAll() { //TODO: commented while updating appengine sdk
//		if (!MNConstants.develmode)
//			return;
//
//		for (Place p : getAllPlaces()) {
//			deletePlace(p);
//		}
//
//		// clean the search index - from https://developers.google.com/appengine/docs/java/search/overview#Removing_Documents
//		try {
//			while (true) {
//				List<String> docIds = new ArrayList<String>();
//				// Return a set of document IDs.
//				ListRequest request = ListRequest.newBuilder().setKeysOnly(true).build();
//				ListResponse<Document> response = INDEX.listDocuments(request);
//				if (response.getResults().isEmpty()) {
//					break;
//				}
//				for (Document doc : response) {
//					docIds.add(doc.getId());
//				}
//				INDEX.remove(docIds);
//			}
//		} catch (RuntimeException e) {
//			LOG.log(Level.SEVERE, "Failed to remove documents", e);
//		}
	}

	@Override
	public Logger LOG() {
		return LOG;
	}

}
