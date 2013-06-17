package org.sgx.madrenecesidad.client.ui.view;

import java.util.List;
import java.util.Map;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.JavaUtil;
import org.sgx.jsutil.client.appstate.AppStateParamHelper;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.service.PlaceServiceAsync;
import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchEditor;
import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchModel;
import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchResultEditor;
import org.sgx.madrenecesidad.client.ui.state.MNStateManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class SearchPlacePanel extends UIObject {

private static SearchPlacePanelUiBinder uiBinder = GWT.create(SearchPlacePanelUiBinder.class);

interface SearchPlacePanelUiBinder extends UiBinder<Element, SearchPlacePanel> {
}

@UiField Element searchPlaceEdEl, searchButton, resultEditorEl;
//	private PlaceSearchModel psm;
private PlaceSearchEditor formEditor;
PlaceSearchResultEditor resultEditor; 

public SearchPlacePanel() {
	setElement(uiBinder.createAndBindUi(this));
	formEditor = new PlaceSearchEditor(); 
	PlaceSearchModel psm = new PlaceSearchModel(); 
	psm.setKeywords("");
	psm.setInCurrentMapView(false);
	formEditor.load(psm); 
	searchPlaceEdEl.appendChild(formEditor.getElement()); 		

	resultEditor = new PlaceSearchResultEditor();	
	resultEditorEl.appendChild(resultEditor.getElement()); 
	
	DOMUtil.addClickHandler(searchButton, new DOMUtil.EventHandler() {			
		@Override
		public void onEvent(Event event) {
			searchButtonClick(); 
		}
	}); 
}

public void showResults(PlaceSearchModel model) {
	resultEditorEl.getStyle().setProperty("display", "block"); 
	formEditor.load(model); 
//		PlaceSearchModel model = formEditor.flush(); 
	PlaceServiceAsync service = MNMain.getInstance().getServiceFactory().getPlaceService();
	service.searchPlace(model.getKeywords(), new AsyncCallback<List<Place>>() {			
		@Override
		public void onSuccess(List<Place> result) {
//				Window.alert("founded: "+result.size()); 
			resultEditor.load(result); 
		}			
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("fail: "+caught);
		}
	}); 
}
protected void searchButtonClick() {
	PlaceSearchModel model = formEditor.flush(); 
	String config = AppStateParamHelper.toUrl(JavaUtil.toMap2("keywords", model.getKeywords())); 
	MNMain.getInstance().getStateManager().navigate(MNStateManager.STATE_SEARCHPLACE, config); 
}

}
