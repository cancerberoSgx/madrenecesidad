package org.sgx.madrenecesidad.client.ui.view.search;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.JavaUtil;
import org.sgx.jsutil.client.appstate.AppStateParamHelper;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.service.MapViewServiceAsync;
import org.sgx.madrenecesidad.client.state.MNStateManager;
import org.sgx.madrenecesidad.client.ui.search.AbstractSearchResultEditor;
import org.sgx.madrenecesidad.client.ui.search.ColumnPrinter;
import org.sgx.madrenecesidad.client.ui.search.MapViewSearchEditor;
import org.sgx.madrenecesidad.client.ui.search.MapViewSearchModel;
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

public class SearchMapViewPanel extends UIObject {

	private static SearchMapViewPanelUiBinder uiBinder = GWT.create(SearchMapViewPanelUiBinder.class);

	interface SearchMapViewPanelUiBinder extends UiBinder<Element, SearchMapViewPanel> {
	}

@UiField Element searchMapViewEdEl, searchButton, resultEditorEl;
//	private MapViewSearchModel psm;
private MapViewSearchEditor formEditor;
AbstractSearchResultEditor<MapView> resultEditor;
private HashMap<String, ColumnPrinter<MapView>> resultColumns; 

public SearchMapViewPanel() {
	setElement(uiBinder.createAndBindUi(this));
	formEditor = new MapViewSearchEditor(); 
	MapViewSearchModel psm = new MapViewSearchModel(); 
	psm.setKeywords("");
//	psm.setInCurrentMapView(false);
	formEditor.load(psm); 
	searchMapViewEdEl.appendChild(formEditor.getElement()); 
	
	resultColumns = new HashMap<String, ColumnPrinter<MapView>> ();
	resultColumns.put("Name", new ColumnPrinter<MapView>() {		
		@Override
		public String getHTML(MapView model) {
			return model.getName();
		}
	}); 
	resultColumns.put("Description", new ColumnPrinter<MapView>() {		
		@Override
		public String getHTML(MapView model) {
			return model.getDescription();
		}
	}); 
	resultColumns.put("Center", new ColumnPrinter<MapView>() {		
		@Override
		public String getHTML(MapView model) {
			return MNUtil.htmlPrintPoint(model.getCenter()); 
		}
	});
	
	
	
	resultEditor = new AbstractSearchResultEditor<MapView>("Map") {
		@Override
		public Map<String, ColumnPrinter<MapView>> getColumns() {
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

public void showResults(MapViewSearchModel model) {
	resultEditorEl.getStyle().setProperty("display", "block"); 
	formEditor.load(model); 
//	System.out.println("showResults");
//		MapViewSearchModel model = formEditor.flush(); 
	MapViewServiceAsync service = MNMain.getInstance().getServiceFactory().getMapViewService();
	service.searchMapView(model.getKeywords(), new AsyncCallback<List<MapView>>() {			
		@Override
		public void onSuccess(List<MapView> result) {
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
	MapViewSearchModel model = formEditor.flush(); 
	String config = AppStateParamHelper.toUrl(JavaUtil.toMap2("keywords", model.getKeywords())); 
	MNMain.getInstance().getStateManager().navigate(MNStateManager.STATE_SEARCHMAPVIEW, config); 
}

}
