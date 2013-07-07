package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;

import org.sgx.gwtpersistor.client.Model;
import org.sgx.madrenecesidad.client.model.jso.MapViewJSO;
import org.sgx.madrenecesidad.client.ui.editors.search.Searchable;

import com.google.appengine.api.datastore.GeoPt;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * 
 * @author sg
 * 
 */
@Entity
// @Cache
public class MapView implements Serializable, IsSerializable, Owned, Protectable, JSOable/*, Model*/, Searchable{
	@Id
	Long id;
//	double longitude, latitude;

String indexId; 
GeoPt center; 
	int zoom;
	String name, description;
	String mapTypeId; //HIBRYD, TERRAIN, SATELLITE, ETC
	String layers; //comma separated layer constants, ses MNConstants.LAYER_
	String ownerId;
	boolean private_; 
	
	// @Parent Key<User> user;

	// public Key<User> getUserKey() {
	// return user;
	// }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public double getLongitude() {
//		return longitude;
//	}
//
//	public void setLongitude(double longitude) {
//		this.longitude = longitude;
//	}
//
//	public double getLatitude() {
//		return latitude;
//	}
//
//	public void setLatitude(double latitude) {
//		this.latitude = latitude;
//	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

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

	/* (non-Javadoc)
	 * @see org.sgx.madrenecesidad.client.model.Owned#getOwnerId()
	 */
	@Override
	public String getOwnerId() {
		return ownerId;
	}

	/* (non-Javadoc)
	 * @see org.sgx.madrenecesidad.client.model.Owned#setOwnerId(java.lang.String)
	 */
	@Override
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public boolean isPrivate() {
		return private_;
	}

	public void setPrivate(boolean _private) {
		this.private_ = _private;
	}

	
	public String getMapTypeId() {
		return mapTypeId;
	}

	public void setMapTypeId(String mapTypeId) {
		this.mapTypeId = mapTypeId;
	}

	public String getLayers() {
		return layers;
	}

	public void setLayers(String layers) {
		this.layers = layers;
	}

	@Override
	public JavaScriptObject toJSO() {
		return MapViewJSO.create().name(getName()).id(getId()+""); 
	}

	public GeoPt getCenter() {
		return center;
	}

	public void setCenter(GeoPt center) {
		this.center = center;
	}

	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public static MapView create() {
		MapView m = new MapView();
		m.setZoom(3); 
		m.setCenter(new GeoPt(0,0)); 
		
		return m;
	}

//	@Override
//	public String getPersistenceId() {
//		return getId()>0 ? getId()+"" : null; 
//	}
}
