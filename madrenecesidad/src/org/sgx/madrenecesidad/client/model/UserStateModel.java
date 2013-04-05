package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable;

public class UserStateModel implements Serializable, IsSerializable{
	List<MapView> mapViews;

	public List<MapView> getMapViews() {
		return mapViews;
	}
	public List<String> getMapViewNames() {
		List<String> l = new LinkedList<String>(); 
		for(MapView mv : mapViews) {
			l.add(mv.getName()); 
		}
		return l;
	}
	public MapView getById(String id) {
		for(MapView mv : mapViews) {
			if(mv.getId().equals(id))
				return mv; 
		}
		return null; 
	}
	

	public void setMapViews(List<MapView> mapViews) {
		this.mapViews = mapViews;
	}

}
