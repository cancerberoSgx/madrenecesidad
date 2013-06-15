package org.sgx.madrenecesidad.client.ui.view;

import java.util.List;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.service.PlaceServiceAsync;
import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchEditor;
import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class SearchPlacePanel extends UIObject {

	private static SearchPlacePanelUiBinder uiBinder = GWT.create(SearchPlacePanelUiBinder.class);

	interface SearchPlacePanelUiBinder extends UiBinder<Element, SearchPlacePanel> {
	}

	@UiField Element searchPlaceEdEl, searchButton;
//	private PlaceSearchModel psm;
	private PlaceSearchEditor editor; 
	
	public SearchPlacePanel() {
		setElement(uiBinder.createAndBindUi(this));
		editor = new PlaceSearchEditor(); 
		PlaceSearchModel psm = new PlaceSearchModel(); 
		psm.setKeywords("big fish");
		psm.setInCurrentMapView(false);
		editor.load(psm); 
		searchPlaceEdEl.appendChild(editor.getElement()); 
		DOMUtil.addClickHandler(searchButton, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
				doSearch(); 
			}
		}); 
	}

	protected void doSearch() {
		PlaceSearchModel model = editor.flush(); 
		PlaceServiceAsync service = MNMain.getInstance().getServiceFactory().getPlaceService();
		service.searchPlace(model.getKeywords(), new AsyncCallback<List<Place>>() {
			
			@Override
			public void onSuccess(List<Place> result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		}); 
	}

}
