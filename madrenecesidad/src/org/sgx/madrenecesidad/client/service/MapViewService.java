package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Owned;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("mapViewService")
public interface MapViewService extends RemoteService {
	boolean add(MapView mv);
	boolean remove(MapView mv); 
	List<MapView> list(); 
}
