//package org.sgx.madrenecesidad.client.persist;
//
//import java.util.List;
//
//import org.sgx.gwtpersistor.client.PersistorException;
//import org.sgx.madrenecesidad.client.MNMain;
//import org.sgx.madrenecesidad.client.model.MapView;
//import org.sgx.madrenecesidad.client.service.MapViewServiceAsync;
//
//import com.google.gwt.user.client.rpc.AsyncCallback;
//
//public class MapViewPersistor extends MNPersistor<MapView>{
//
//	private MapViewServiceAsync service;
//
//	public MapViewPersistor(){
//		service = MNMain.services().getMapViewService(); 
//	}
//	@Override
//	public void save(MapView model) throws PersistorException {
////		service.add
////		final MNPersistorResponse<T>
////		if(model.getPersistenceId()!=null) {
////		service.add(model, new AsyncCallback<Boolean>() {
////			
////			@Override
////			public void onSuccess(Boolean result) {
////				// TODO Auto-generated method stub
////				
////			}
////			
////			@Override
////			public void onFailure(Throwable caught) {
////				// TODO Auto-generated method stub
////				
////			}
////		}); 
////		}
////		else {
//////		service.
////		}
//	}
//
//	@Override
//	public MapView load(String id) throws PersistorException {
//		
//		final MNPersistorResponse<MapView>
//		
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MapView> search(String q) throws PersistorException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
