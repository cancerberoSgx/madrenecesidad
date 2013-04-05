package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;

import org.sgx.jsutil.client.JsObject;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * The Channel class is the main entry point to sets of tsgs, places, users, etc.
 * @author sg
 *
 */
@Entity
//@Cache
public class Channel implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	String name;
	
	String description; 
	
//	/**
//	 * owned tags by this channel
//	 */
//	List<Key<Tag>> tags = new ArrayList<Key<Tag>>();
	
//	/**
//	 * owned places by this channel
//	 */
//	List<Key<Place>> places = new ArrayList<Key<Place>>();

	String indexId; 
	
//	public void setTags(List<Tag> tags) {
//		this.tags = new LinkedList<Key<Tag>>(); 
//		for(Tag t : tags) {
//			Key.create(Tag.class, t.get)
//		}
//	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<Key<Tag>> getTags() {
//		return tags;
//	}
//
//	public void setTags(List<Key<Tag>> tags) {
//		this.tags = tags;
//	}
//
//	public List<Key<Place>> getPlaces() {
//		return places;
//	}
//
//	public void setPlaces(List<Key<Place>> places) {
//		this.places = places;
//	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	
	@Override
	public String toString() {
		return "Channel "+getName(); 
	}

	public JavaScriptObject toJSO() {
		return JsObject.one("name", getName())._("description", getDescription()); 
	}
}
