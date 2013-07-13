package org.sgx.madrenecesidad.client.state;

import java.util.Map;

import org.sgx.jsutil.client.JavaUtil;
import org.sgx.jsutil.client.appstate.AbstractAppStateManager;
import org.sgx.jsutil.client.appstate.AppState;
import org.sgx.jsutil.client.appstate.AppStateParamHelper;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.state.impl.EditMapState;
import org.sgx.madrenecesidad.client.state.impl.ViewMapState;
import org.sgx.madrenecesidad.client.ui.action.ActionManager;
import org.sgx.madrenecesidad.client.ui.action.AddPlaceAction;
import org.sgx.madrenecesidad.client.ui.action.HomeUi;
import org.sgx.madrenecesidad.client.ui.search.MapViewSearchModel;
import org.sgx.madrenecesidad.client.ui.search.PlaceSearchModel;
import org.sgx.madrenecesidad.client.ui.view.search.SearchMapViewPanel;
import org.sgx.madrenecesidad.client.ui.view.search.SearchPlacePanel;

/**
 * responsible of knowng and mantain all the states of the application and know the state names. 
 * @author sg
 *
 */
public class MNStateManager extends AbstractAppStateManager {

	public static final String 
		
	
		STATE_HOME = "home", 
		STATE_NOTFOUND = "notFound",
		
		STATE_SEARCHPLACE = "searchPlace", 
		STATE_ADDPLACE = "addPlace",
		
		STATE_ADDMAPVIEW="addMap",
		STATE_SEARCHMAPVIEW="searchMap", 
		STATE_VIEWPLACE="viewPlace", 			
		 
		STATE_ELEVATION = "elevationTool", 
		STATE_VIEWMAP = "viewMap", 

		
		PARAM_ID = "id", 
		PARAM_ADD = "add",
		PARAM_CMD = "cmd",
		
		
		CMD_EDIT = "edit", 
		CMD_ADD = "add", 
		CMD_VIEW = "view";

	
	public MNStateManager() {
		super();

		addState(new MNAppState(STATE_HOME) {
			@Override
			public void perform(String params) {
				HomeUi view = MNMain.getInstance().getViewManager().getView(HomeUi.class);
				MNMain.getInstance().getLayout().getStatePanel().setState(this, view);
			}
		});

		
		
		addState(new MNAppState(STATE_SEARCHMAPVIEW) {
			@Override
			public void perform(String config) {
				SearchMapViewPanel view = MNMain.getInstance().getViewManager().getView(SearchMapViewPanel.class); 
				MNMain.getInstance().getLayout().getStatePanel().setState(this, view);
				
				MapViewSearchModel model = MapViewSearchModel.fromStateConfig(config);
//				PlaceMapModel model = PlaceSearchModel.fromStateConfig(config); 
				if(model!=null)
					view.showResults(model); 
			}
		});
		
		addState(new MNAppState(STATE_ADDPLACE) {
			@Override
			public void perform(String config) {
				SearchPlacePanel view = MNMain.getInstance().getViewManager().getView(SearchPlacePanel.class); 
				MNMain.getInstance().getLayout().getStatePanel().setState(this, view);
				
//				ActionManager.getInstance().performAction(new AddPlaceAction(), null); 
//				MNMain.getInstance().getLayout().getStatePanel()
//					.addState(this, new SearchPlacePanel());
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
		addState(new ViewMapState());
		addState(new EditMapState()); 

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

	public void navigate(String state, Map<String, String> params) {
		String pstr = AppStateParamHelper.toUrl(params); 
		this.navigate(state, pstr); 
	}


}
