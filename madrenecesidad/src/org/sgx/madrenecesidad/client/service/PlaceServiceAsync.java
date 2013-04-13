package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.Place;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PlaceServiceAsync {

	void addPlace(Place p, AsyncCallback<Void> callback);

	void deletePlace(Place ch, AsyncCallback<Void> callback);

	void getAllPlaces(AsyncCallback<List<Place>> callback);

	void searchPlace(String nameFragment, AsyncCallback<List<Place>> callback);

	void getOwnerPlaces(AsyncCallback<List<Place>> callback);

}
