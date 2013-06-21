package org.sgx.madrenecesidad.server;

import static org.sgx.madrenecesidad.server.OfyService.ofy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.MNConstants;
import org.sgx.madrenecesidad.client.model.Tag;
import org.sgx.madrenecesidad.client.service.TagService;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.PutResponse;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

/**
 * The server side implementation of the RPC service for tags
 * @author sg
 */
@SuppressWarnings("serial")
public class TagServiceImpl extends AbstractService implements TagService {
//	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TagServiceImpl.class.getName());

	private static final String INDEX_NAME = "tag_index";
	private static final String FIELD_NAME = "name", FIELD_DESCRIPTION="description";

	/**
	 * The index used by this application. Since we only have one index we
	 * create one instance only. We build an index with the default consistency,
	 * which is Consistency.PER_DOCUMENT. These types of indexes are most
	 * suitable for streams and feeds, and can cope with a high rate of updates.
	 */
	private static final Index INDEX = SearchServiceFactory.getSearchService().getIndex(IndexSpec.newBuilder().setName(INDEX_NAME));

	private static final int SEARCH_LIMIT = 200;

	@Override
	public Long addTag(Tag tag) {
		//first add it to the tag index to get the indexId

		Document.Builder docBuilder = Document.newBuilder()
			.addField(Field.newBuilder().setName(FIELD_NAME).setText(tag.getName()))
			.addField(Field.newBuilder().setName(FIELD_DESCRIPTION).setText(tag.getDescription())); 
			
		if(tag.getIndexId()!=null) {
			docBuilder.setId(tag.getIndexId()); //.addField(Field.newBuilder().setName("doc_id").setText(ch.getName())); 
		}
		String indexId=null; 
		
		Document doc = docBuilder.build();
		LOG.info("Adding/updating document:\n" + doc.toString());
		try {
			PutResponse addresp = INDEX.put(doc);
			indexId = addresp.getIds().get(0); 
			// return "Document added";
		} catch (RuntimeException e) {
			String errMsg = "Failed to add/update index " + doc + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg, e);
			return null;
		}
		tag.setIndexId(indexId);
		
		//now save it to the datastore
		
		try {
			
			Objectify ofy = ofy(); 
			ofy.save().entity(tag).now(); 
//			ofy.getTxn().commit(); 
			
			String logMsg1 = "add/updating tag " + tag.getName()+" - "+tag.getId();
			LOG.info(logMsg1);
		} catch (Exception e) {
			String errMsg = "Failed to add/(update tag" + tag.getName() + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg);
			return null;
		}
//		tag.setId(tag.getId()); 
		return tag.getId();
	}

	@Override
	public Tag getTagById(Long id) {
		try {
			Ref<Tag> ref = ofy().load().key(Key.create(Tag.class, id));
			Tag fetched1 = ref.get();
			return fetched1;
		} catch (Exception e) {
			String errMsg = "Failed to getTagById " + id + ". Error: " + e;
			LOG.log(Level.SEVERE, errMsg, e);
			return null;
		}

	}
//	@Override
//	public Tag getTagsOf(Channel ch) {
//		try {
//			Ref<Tag> ref = ofy().load().key(Key.create(Tag.class, tagName));
//			Tag fetched1 = ref.get();
//			return fetched1;
//		} catch (Exception e) {
//			String errMsg = "Failed to getTagByName " + tagName + ". Error: " + e;
//			LOG.log(Level.SEVERE, errMsg, e);
//			return null;
//		}
//
//	}
	@Override
	public List<Tag> getTags() {
		List<Tag> alltags = ofy().load().type(Tag.class).list();
		List<Tag> ret = new LinkedList<Tag>();
		for (Tag t : alltags) {
			ret.add(t);
		}
		LOG.info("getTags count: "+ret.size());
		return ret;
	}

	@Override
	public void deleteTag(Tag tag) {
		if(tag==null)
			return;
		Objectify ofy = ofy(); 
		ofy.delete().entity(tag).now(); 
//		ofy.getTxn().commit(); 
		LOG.info("deleteTag from datastore: "+tag.getName()+" - "+tag.getId());
		
		try {
			//and delete from indexes to
			if(tag.getIndexId()!=null && !tag.getIndexId().equals("")) {
				INDEX.delete(tag.getIndexId());
				LOG().info("deleted tag from INDEX: "+tag+" - indexId: "+tag.getIndexId());
			}		 
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "failed to delete tag "+tag+" from INDEX", e);
		}
	}
	
	@Override
	public void cleanAll() {//TODO: commented while updating appengine sdk
//		if(!MNConstants.develmode)
//			return;
//		
//		for(Tag t : getTags()) {
//			deleteTag(t); 
//		}
//		
//		//clean the search index - from https://developers.google.com/appengine/docs/java/search/overview#Removing_Documents
//		try {
//		    while (true) {
//		        List<String> docIds = new ArrayList<String>();
//		        // Return a set of document IDs.
//		        ListRequest request = ListRequest.newBuilder().setKeysOnly(true).build();
//		        ListResponse<Document> response = INDEX.listDocuments(request);
//		        if (response.getResults().isEmpty()) {
//		            break;
//		        }
//		        for (Document doc : response) {
//		            docIds.add(doc.getId());
//		        }
//		        INDEX.remove(docIds);
//		    }
//		} catch (RuntimeException e) {
//		    LOG.log(Level.SEVERE, "Failed to remove documents", e);
//		}
	}

	@Override
	public List<Tag> searchTag(String nameFragment) {
		// see
		// http://code.google.com/p/google-app-engine-samples/source/browse/trunk/search/java/src/com/google/appengine/demos/search/TextSearchServlet.java
		com.google.appengine.api.search.Query query = com.google.appengine.api.search.Query.newBuilder()
				.setOptions(com.google.appengine.api.search.QueryOptions.newBuilder().setLimit(SEARCH_LIMIT).
				build()).build(nameFragment);

		Results<ScoredDocument> results = INDEX.search(query);
		List<Tag> dtoResults = new LinkedList<Tag>();
		for (ScoredDocument scoredDoc : results) {
			Tag dto = new Tag();
			dto.setName(getOnlyField(scoredDoc, FIELD_NAME, "defaultValue"));
			dtoResults.add(dto);
		}
		return dtoResults;
	}

	@Override
	public Logger LOG() {
		return LOG; 
	}

	
}
