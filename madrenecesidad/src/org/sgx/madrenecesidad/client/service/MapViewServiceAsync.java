package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Owned;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MapViewServiceAsync {

	void add(MapView mv, AsyncCallback<Long> callback);

	void remove(MapView mv, AsyncCallback<Boolean> callback);

	void list(AsyncCallback<List<MapView>> callback);

	void get(Long id, AsyncCallback<MapView> callback);

	void searchMapView(String keywords, AsyncCallback<List<MapView>> asyncCallback);

	void searchMapView(String nameFragment, int size, int page, AsyncCallback<List<MapView>> callback);

}
