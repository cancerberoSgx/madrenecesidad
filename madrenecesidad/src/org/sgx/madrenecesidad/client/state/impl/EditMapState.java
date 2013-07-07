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
import org.sgx.madrenecesidad.client.ui.util.MNAsyncCallback;

import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

/**
 * In this state the user can add a new state, edit an exiting one, or just view. 
 * @author sg
 *
 */
public class EditMapState extends MNAppState {
	
	public EditMapState() { 
		super(MNStateManager.STATE_VIEWMAP);
	}
	
	@Override
	public void perform(String config) {
		
		ActionManager.getInstance().performAction(new AddPlaceAction(), null);
		Map<String, String> params = AppStateParamHelper.toParams(config);
		String cmd = params.get(MNStateManager.PARAM_CMD);
		
		if(cmd==null)
			cmd = MNStateManager.CMD_EDIT;
		
		if(cmd.equals(MNStateManager.CMD_EDIT)) {
			Long id = AppStateParamHelper.getLong(params, MNStateManager.PARAM_ID, -1l);
			doEdit(id, false); 
		}
		else if(cmd.equals(MNStateManager.CMD_ADD)){
			doEdit(0l, true); 
		}
		else if(cmd.equals(MNStateManager.CMD_VIEW)){
			Long id = AppStateParamHelper.getLong(params, MNStateManager.PARAM_ID, -1l);
			doView(id); 
		}
	}

	private void doEdit(Long id, boolean add) {
//		MapView model = MapView.create();
		if(!add && id>0) {
			MNMain.services().getMapViewService().get(id, new MNAsyncCallback<MapView>("get mapview") {				
				@Override
				public void onSuccess(MapView m) {
					doEdit2(m); 
				}				
//				@Override
//				public void onFailure(Throwable caught) {	
//					MNMain.layout().setStatusError(text)
//				}
			}); 
		}
	}

	protected void doEdit2(MapView m) {
		LatLng c = LatLng.newInstance(m.getCenter().getLatitude(), m.getCenter().getLongitude()); 
		MNMain.getInstance().getLayout().getMapWidget().setCenter(c); 
		UIObject view = getViewFor(m); 
		MNMain.getInstance().getLayout().getStatePanel().setState(this, view); 
	}
	protected UIObject getViewFor(MapView m) {
		MapViewEditor v = new MapViewEditor();
		v.load(m); 
		return v;
	}
	private void doView(Long id) {
		
	}

}
