package org.sgx.madrenecesidad.client.state.impl;

import java.util.Map;

import org.sgx.jsutil.client.appstate.AppStateParamHelper;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.state.MNAppState;
import org.sgx.madrenecesidad.client.state.MNStateManager;
import org.sgx.madrenecesidad.client.ui.action.ActionManager;
import org.sgx.madrenecesidad.client.ui.action.AddPlaceAction;
import org.sgx.madrenecesidad.client.ui.editors.MapViewEditor;
import org.sgx.madrenecesidad.client.ui.view.ViewMapView;

import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class ViewMapState extends MNAppState {
	
	public ViewMapState() { 
		super(MNStateManager.STATE_VIEWMAP);
	}
	
	@Override
	public void perform(String config) {
		ActionManager.getInstance().performAction(new AddPlaceAction(), null);
		Map<String, String> params = AppStateParamHelper.toParams(config);
		final long id = Long.parseLong(params.get(MNStateManager.PARAM_ID)); 
		MNMain.getInstance().getServiceFactory().getMapViewService().get(id, new AsyncCallback<MapView>() {			
			@Override
			public void onSuccess(MapView result) {
				LatLng c = LatLng.newInstance(result.getCenter().getLatitude(), result.getCenter().getLongitude()); 
				MNMain.getInstance().getLayout().getMapWidget().setCenter(c); 
				UIObject view = getViewFor(result); 
				MNMain.getInstance().getLayout().getStatePanel().setState(ViewMapState.this, view); 
			}			
			@Override
			public void onFailure(Throwable caught) {
				MNMain.getInstance().getLayout().setStatusError("cannot view map "+id, caught); 
			}
		});
	}

	protected UIObject getViewFor(MapView m) {
		ViewMapView v = new ViewMapView();
		v.load(m); 
		return v;
	}

}
