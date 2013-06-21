package org.sgx.madrenecesidad.client.ui.state;

import org.sgx.jsutil.client.appstate.AbstractAppStateManager;
import org.sgx.jsutil.client.appstate.AppState;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.ui.action.ActionManager;
import org.sgx.madrenecesidad.client.ui.action.AddPlaceAction;
import org.sgx.madrenecesidad.client.ui.action.HomeUi;
import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchModel;
import org.sgx.madrenecesidad.client.ui.view.SearchMapViewPanel;
import org.sgx.madrenecesidad.client.ui.view.SearchPlacePanel;

public class MNStateManager extends AbstractAppStateManager {

	public static final String STATE_HOME = "home", STATE_SEARCHPLACE = "searchPlace", 
			STATE_SEARCHMAPVIEW="searchMap", STATE_ADDPLACE = "addPlace",
			STATE_NOTFOUND = "notFound", STATE_ELEVATION = "elevationTool";

	
	public MNStateManager() {
		super();

		addState(new MNAppState(STATE_HOME) {
			@Override
			public void perform(String params) {
				HomeUi view = MNMain.getInstance().getViewManager().getView(HomeUi.class);
				MNMain.getInstance().getLayout().getStatePanel().setState(this, view);
			}
		});

		addState(new MNAppState(STATE_SEARCHPLACE) {
			@Override
			public void perform(String config) {
				SearchPlacePanel view = MNMain.getInstance().getViewManager().getView(SearchPlacePanel.class); 
				MNMain.getInstance().getLayout().getStatePanel().setState(this, view);
				
				PlaceSearchModel model = PlaceSearchModel.fromStateConfig(config); 
				if(model!=null)
					view.showResults(model); 
			}
		});
		
		addState(new MNAppState(STATE_SEARCHMAPVIEW) {
			@Override
			public void perform(String config) {
				SearchMapViewPanel view = MNMain.getInstance().getViewManager().getView(SearchMapViewPanel.class); 
				MNMain.getInstance().getLayout().getStatePanel().setState(this, view);
				
//				PlaceSearchModel model = PlaceSearchModel.fromStateConfig(config); 
//				if(model!=null)
//					view.showResults(model); 
			}
		});
		
		addState(new MNAppState(STATE_ADDPLACE) {
			@Override
			public void perform(String config) {
				ActionManager.getInstance().performAction(new AddPlaceAction(), null); 
//				MNMain.getInstance().getLayout().getStatePanel()
//					.addState(this, new SearchPlacePanel());
			}

		});

	}
	
	@Override
	public void navigate(AppState state, String params) {
		super.navigate(state, params);
		MNMain.getInstance().getLayout().getStatePanel().setState(state, null); 
	}
	
	@Override
	public AppState getDefaultState() {
		return this.getStates().get(STATE_HOME);
	}

	AppState notFoundState = null;

	@Override
	public AppState getNotFoundState() {
		if (notFoundState == null)
			notFoundState = new MNAppState(STATE_NOTFOUND) {
				@Override
				public void perform(String params) {
					super.perform(params);
					MNMain.getInstance().getLayout().setStatusText("Not found", "text-error");
				}
			};
		return notFoundState;
	}


}
