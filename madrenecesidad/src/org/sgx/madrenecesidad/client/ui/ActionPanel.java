package org.sgx.madrenecesidad.client.ui;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.ui.action.ActionManager;
import org.sgx.madrenecesidad.client.ui.action.AddPlaceAction;
import org.sgx.madrenecesidad.client.ui.action.DirectionsAction;
import org.sgx.madrenecesidad.client.ui.action.ElevationAction;
import org.sgx.madrenecesidad.client.ui.action.MeasureDistanceAction;
import org.sgx.madrenecesidad.client.ui.action.SearchAddressAction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.services.TravelMode;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;

public class ActionPanel extends UIObject {

	private static ActionPanelUiBinder uiBinder = GWT.create(ActionPanelUiBinder.class);

	interface ActionPanelUiBinder extends UiBinder<Element, ActionPanel> {
	}

	@UiField
	Element actionAddPlaceAnchor, actionSearchPlaceAnchor, 
	actionSearchAddressAnchor, actionMeasureDistanceAnchor, actionOptionPanelEl, 
		toolDirectionsAnchor, toolElevationAnchor,
		mapTypeRoadmapAnchor, mapTypeSatelliteAnchor, mapTypeHybridAnchor, mapTypeTerrainAnchor;
	
	@UiField
	InputElement layerTrafficCheck, layerTransitCheck, layerBicyclingCheck, layerCloudCheck, layerPanoramioCheck;

	public ActionPanel() {
		setElement(uiBinder.createAndBindUi(this));
		DOMUtil.addClickHandler(actionSearchAddressAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				new SearchAddressAction().perform(null);
			}
		});
		DOMUtil.addClickHandler(actionMeasureDistanceAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				new MeasureDistanceAction().perform(null);
			}
		});

		DOMUtil.addClickHandler(layerTrafficCheck, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				LayerManager.getInstance().setLayer(LayerManager.LAYER_TRAFFIX, layerTrafficCheck.isChecked());
			}
		});
		
		DOMUtil.addClickHandler(layerTransitCheck, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				LayerManager.getInstance().setLayer(LayerManager.LAYER_TRANSIT, layerTransitCheck.isChecked());
			}
		});
		
		DOMUtil.addClickHandler(layerBicyclingCheck, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				LayerManager.getInstance().setLayer(LayerManager.LAYER_BICYCLING, layerBicyclingCheck.isChecked());
			}
		});
		
		DOMUtil.addClickHandler(layerCloudCheck, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				LayerManager.getInstance().setLayer(LayerManager.LAYER_CLOUD, layerCloudCheck.isChecked());
			}
		});
		
		DOMUtil.addClickHandler(layerPanoramioCheck, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				LayerManager.getInstance().setLayer(LayerManager.LAYER_PANORAMIO, layerPanoramioCheck.isChecked());
			}
		});
		
		DOMUtil.addClickHandler(toolDirectionsAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				ActionManager.getInstance().performAction(new DirectionsAction(), null); 
			}
		});
		
		DOMUtil.addClickHandler(mapTypeRoadmapAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				MNMain.getInstance().getLayout().getMapWidget().setMapTypeId(MapTypeId.ROADMAP); 
			}
		});
		DOMUtil.addClickHandler(mapTypeSatelliteAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				MNMain.getInstance().getLayout().getMapWidget().setMapTypeId(MapTypeId.SATELLITE); 
			}
		});
		DOMUtil.addClickHandler(mapTypeHybridAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				MNMain.getInstance().getLayout().getMapWidget().setMapTypeId(MapTypeId.HYBRID); 
			}
		});
		DOMUtil.addClickHandler(mapTypeTerrainAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				MNMain.getInstance().getLayout().getMapWidget().setMapTypeId(MapTypeId.TERRAIN);
			}
		});
		DOMUtil.addClickHandler(toolElevationAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				ActionManager.getInstance().performAction(new ElevationAction(), null); 
			}
		});
		DOMUtil.addClickHandler(actionAddPlaceAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				ActionManager.getInstance().performAction(new AddPlaceAction(), null); 
			}
		});
		DOMUtil.addClickHandler(actionSearchPlaceAnchor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				ActionManager.getInstance().performAction(new AddPlaceAction(), null); 
			}
		});
		
		
	}
	
	public void setOptionPanel(Element panel) {
		DOMUtil.clear(actionOptionPanelEl); 
		actionOptionPanelEl.appendChild(panel); 
	}

}
