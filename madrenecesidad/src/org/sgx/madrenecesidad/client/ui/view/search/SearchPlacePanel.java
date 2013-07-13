package org.sgx.madrenecesidad.client.ui.view.search;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.JavaUtil;
import org.sgx.jsutil.client.appstate.AppStateParamHelper;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.service.PlaceServiceAsync;
import org.sgx.madrenecesidad.client.state.MNStateManager;
import org.sgx.madrenecesidad.client.ui.search.AbstractSearchResultEditor;
import org.sgx.madrenecesidad.client.ui.search.ColumnPrinter;
import org.sgx.madrenecesidad.client.ui.search.PlaceSearchEditor;
import org.sgx.madrenecesidad.client.ui.search.PlaceSearchModel;
import org.sgx.madrenecesidad.client.ui.search.Searchable;
import org.sgx.madrenecesidad.client.util.MNUtil;

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
AbstractSearchResultEditor<Place> resultEditor;
private Map<String, ColumnPrinter<Place>> resultColumns; 

public SearchPlacePanel() {
	setElement(uiBinder.createAndBindUi(this));
	formEditor = new PlaceSearchEditor(); 
	PlaceSearchModel psm = new PlaceSearchModel(); 
	psm.setKeywords("");
	psm.setInCurrentMapView(false);
	formEditor.load(psm); 
	searchPlaceEdEl.appendChild(formEditor.getElement()); 
	
	resultColumns = new LinkedHashMap<String, ColumnPrinter<Place>>();
	resultColumns.put("Name", new ColumnPrinter<Place>() {		
		@Override
		public String getHTML(Place model) {
			return model.getName();
		}
	}); 
	resultColumns.put("Description", new ColumnPrinter<Place>() {		
		@Override
		public String getHTML(Place model) {
			return model.getDescription();
		}
	}); 
	resultColumns.put("Center", new ColumnPrinter<Place>() {		
		@Override
		public String getHTML(Place model) {
			return MNUtil.htmlPrintPoint(model.getCenter()); 
		}
	});
	
	
	resultEditor = new AbstractSearchResultEditor<Place>("Place") {
		@Override
		public Map<String, ColumnPrinter<Place>> getColumns() {
			return resultColumns;
		}
	};	
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
//	System.out.println("showResults");
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
