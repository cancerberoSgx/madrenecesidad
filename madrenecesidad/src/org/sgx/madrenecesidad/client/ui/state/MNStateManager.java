package org.sgx.madrenecesidad.client.ui.state;

import java.util.HashMap;
import java.util.Map;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.appstate.AbstractAppStateManager;
//import org.sgx.jsutil.client.appstate.AppStateAction;
import org.sgx.jsutil.client.appstate.AppState;
import org.sgx.jsutil.client.appstate.AppStateParamHelper;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.ui.action.ActionManager;
import org.sgx.madrenecesidad.client.ui.action.AddPlaceAction;
import org.sgx.madrenecesidad.client.ui.action.ElevationAction;
import org.sgx.madrenecesidad.client.ui.action.HomeUi;
import org.sgx.madrenecesidad.client.ui.action.SearchPlaceAction;
import org.sgx.madrenecesidad.client.ui.view.NotFound;
import org.sgx.madrenecesidad.client.ui.view.SearchPlacePanel;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

public class MNStateManager extends AbstractAppStateManager {

	public static final String STATE_HOME = "home", STATE_SEARCHPLACE = "searchPlace", STATE_ADDPLACE = "addPlace",
			STATE_NOTFOUND = "notFound", STATE_ELEVATION = "elevationTool";

	public MNStateManager() {
		super();

		addState(new MNAppState(STATE_HOME) {// new AppStateAction() {
			@Override
			public void perform(String params) {
				Element containerEl = MNMain.getInstance().getLayout().getEditorPanel().getElement();
				// DOMUtil.clear(containerEl);
				containerEl.appendChild(new HomeUi().getElement());
			}
		});

		addState(new MNAppState(STATE_SEARCHPLACE) {
			@Override
			public void perform(String config) {
				// Window.alert("search place");
				// ActionManager.getInstance().performAction(new SearchPlaceAction(), null);
				Element containerEl = MNMain.getInstance().getLayout().getEditorPanel().getElement();
				// DOMUtil.clear(containerEl);
				containerEl.appendChild(new SearchPlacePanel().getElement());
			}

		});

//		addState(new ElevationAction());

		
		// addState(new MNState(STATE_HOME, containerEl) {
		// @Override
		// public void perform(String params) {
		// super.perform(params);
		// DOMUtil.clear(getContainerEl());
		// getContainerEl().appendChild(new HomeUi().getElement());
		// // Map<String, String> p = AppStateParamHelper.toParams(params);
		// }
		// });
		// addState(new MNState(STATE_SEARCHPLACE, containerEl) {
		// @Override
		// public void perform(String params) {
		// // Map<String, String> p = AppStateParamHelper.toParams(params);
		// // String keywords = p.containsKey("keywords") ? p.get("keywords") : "";
		// ActionManager.getInstance().performAction(new SearchPlaceAction(), null);
		// }
		// });
		// addState(new MNState(STATE_ADDPLACE, containerEl) {
		// @Override
		// protected UIObject buildNewView() {
		// AddPlaceAction action = new AddPlaceAction();
		// ActionManager.getInstance().performAction(action, null);
		// return AddPlaceAction.
		// }
		// });

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
					StateHelper.getContainerEl().appendChild(new NotFound().getElement());
				}
			};
		return notFoundState;
	}

	// @Override
	// public AppStateAction getNotFoundAction() {
	// return new AppStateAction() {
	// @Override
	// public void perform(Object config) {
	// Element containerEl = MNMain.getInstance().getLayout().getEditorPanel().getElement();
	// // DOMUtil.clear(containerEl);
	// containerEl.appendChild(new NotFound().getElement());
	// }
	// @Override
	// public void uninstall() {
	// }
	// };
	// }
}
