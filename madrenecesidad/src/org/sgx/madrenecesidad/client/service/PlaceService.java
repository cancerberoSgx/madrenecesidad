package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.model.Tag;

//import com.gdevelop.gwt.syncrpc.SyncProxy;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("placeService")
public interface PlaceService extends RemoteService {
	public Long addPlace(Place p); 
	public void deletePlace(Place ch); 
	public List<Place> getAllPlaces();
	Place getPlaceById(Long placeId);
	List<Place> getOwnerPlaces(); 
//	List<Channel> getChanels(); 
//	Channel getChannelByName(String name);
	List<Place> searchPlace(String nameFragment);
	List<Place> searchPlace(String nameFragment, int maxSize, int page);
	void cleanAll();
	
//	void cleanAll();
//	List<Tag> getTags(Channel c);
	
}
