package org.sgx.madrenecesidad.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.sgx.madrenecesidad.client.MNMain;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.layout.client.Layout.Layer;
import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.layers.BicyclingLayer;
import com.google.gwt.maps.client.layers.TrafficLayer;
import com.google.gwt.maps.client.layers.TransitLayer;
import com.google.gwt.maps.client.panoramiolib.PanoramioLayer;
import com.google.gwt.maps.client.weatherlib.CloudLayer;

/**
 * 
 * @author sg
 * 
 */
public class LayerManager {
	private static LayerManager instance;

	private LayerManager() {
		layerStatus = new HashMap<String, Boolean>();
		layers = new HashMap<String, JavaScriptObject>();
	}

	public static LayerManager getInstance() {
		if (instance == null)
			instance = new LayerManager();
		return instance;
	}

//	public static interface Layer {
//		void setMap(MapWidget m);
//	}
	
	public static final String 
			LAYER_TRAFFIX="traffic", 
			LAYER_TRANSIT="transit", 
			LAYER_BICYCLING="Bicycling", 
			LAYER_CLOUD="cloud", LAYER_PANORAMIO="panoramio";  

	Map<String, Boolean> layerStatus;
	Map<String, JavaScriptObject> layers;

	public void setLayer(String layer, Boolean status) {
		JavaScriptObject l = layers.get(layer);
		if(l==null) {
			l=buildNewLayer(layer);
			layers.put(layer, l);
		}
		layerStatus.put(layer, status);
		layers.put(layer,  l);
		
		MapWidget map = MNMain.getInstance().getLayout().getMapWidget();
		if(status)
			layerSetMap(map.getMVCObject(), l); 
		else
			layerSetMap(null, l); 
	}

	private final native void layerSetMap(MapImpl map, JavaScriptObject l)/*-{
		if(l!=null)
			l.setMap(map); 
	}-*/;


	private JavaScriptObject buildNewLayer(String layer) {
		if(layer.equals(LAYER_TRAFFIX))
			return TrafficLayer.newInstance(); 
		if(layer.equals(LAYER_TRANSIT))
			return TransitLayer.newInstance(); 
		if(layer.equals(LAYER_BICYCLING))
			return BicyclingLayer.newInstance();
		if(layer.equals(LAYER_CLOUD))
			return CloudLayer.newInstance();
		if(layer.equals(LAYER_PANORAMIO))
			return PanoramioLayer.newInstance(); 
		
		else
			return null;
	}
}
