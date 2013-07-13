package org.sgx.madrenecesidad.client.ui.view;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.ui.LayerManager;
import org.sgx.madrenecesidad.client.ui.editors.MapViewEditor;
import org.sgx.madrenecesidad.client.ui.util.MNAsyncCallback;
import org.sgx.madrenecesidad.client.util.MNUtil;

import com.google.appengine.api.datastore.GeoPt;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class EditMapView extends UIObject {

	private static AddMapViewUiBinder uiBinder = GWT.create(AddMapViewUiBinder.class);

	interface AddMapViewUiBinder extends UiBinder<Element, EditMapView> {
	}

	@UiField Element editor, button, title;
	private MapViewEditor ed;
	private MapView model;
//	private boolean newMapView; 
	
	public EditMapView(MapView model) {
		setElement(uiBinder.createAndBindUi(this));
		
		if(model==null) {
//			this.newMapView=true;
			model = new MapView();
			model.setCenter(MNUtil.toGeoPt(MNMain.getInstance().getLayout().getMapWidget().getCenter()));		
			model.setLayers(MNMain.mapLayers().getActiveAsString());
			MNMain.lang().internationalizeHtml(title, "Add Map"); 
		}
		else {
//			this.newMapView=false;
			MNMain.lang().internationalizeHtml(title, "Edit Map"); 
		}
		this.model=model;
		ed = new MapViewEditor();
		editor.appendChild(ed.getElement()); 
		ed.load(model); 
		
		DOMUtil.addClickHandler(button, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
				doUpdateMap(ed.flush()); 
			}
		}); 
	}

	protected void doUpdateMap(MapView model) {	
		if(model.getId()<=0) {
			MNMain.services().getMapViewService().add(model, new MNAsyncCallback<Long>("add map"){
				@Override
				public void onSuccess(Long newId) {
					MNMain.layout().setStatusText("New map view successfully added: "+newId, "text-success"); 
				}
			}); 
		}
		else {
//			MNMain.services().getMapViewService().
		}
	}

	/**
	 * this will put the view in "edit entity" mode
	 * @param model
	 */
	public void loadModel(MapView model) {
		
	}
}
