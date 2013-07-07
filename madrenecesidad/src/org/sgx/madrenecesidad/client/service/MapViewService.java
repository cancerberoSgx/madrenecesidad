package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.MNServiceException;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Owned;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("mapViewService")
public interface MapViewService extends RemoteService {
	Long add(MapView mv) throws MNServiceException;
	boolean remove(MapView mv); 
	List<MapView> list();
	MapView get(Long id) throws MNServiceException;
	List<MapView> searchMapView(String keywords);
	List<MapView> searchMapView(String nameFragment, int size, int page); 
}
