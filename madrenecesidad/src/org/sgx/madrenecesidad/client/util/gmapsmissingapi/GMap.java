//package org.sgx.madrenecesidad.client.util.gmapsmissingapi;
//
//import org.sgx.yuigwt.yui.node.Node;
//
//import com.google.gwt.dom.client.Element;
//import com.google.gwt.maps.client.MapImpl;
//import com.google.gwt.maps.client.MapOptions;
//import com.google.gwt.maps.client.layers.TrafficLayer;
//import com.google.gwt.maps.client.placeslib.PlacesService;
//
///**
// * instanceable Utility for showing a gmap  in an Element dom.client api and not forced to gwt widgets.
// * 
// * TODO: could override mapImpl methods. ?
// * 
// * @author sg
// * 
// */
//public class GMap {
//
//	MapImpl mapImpl;
//	Element element;
//	private MapOptions options;
//
//	public GMap(Element element, MapOptions options) {
//		this.element = element;
//		this.options = options;
//		render();
//	}
//
//	public GMap(Node n, MapOptions options) {
//		this.element = n.getDOMNode();
//		this.options = options;
//		render();
//	}
//
//	public void render() {
//		mapImpl = createMap(element, options);
//	}
//
//	public static MapImpl createMap(Element e, MapOptions options) {
//		MapImpl impl = MapImpl.newInstance(e, options);
//		
//		MyTrafficLayer trafficLayer1 = MyTrafficLayer.newInstance().cast(); 
//		trafficLayer1.setMap(impl);
//		
//		
//		// e.addClassName("gwt-map-MapWidget-div");
//		return impl;
//	}
//
//	public static MapImpl createMap(Node e, MapOptions options) {
//		MapImpl impl = MapImpl.newInstance(e.getDOMNode(), options);
//		// e.addClassName("gwt-map-MapWidget-div");
//		return impl;
//	}
//
//	public PlacesService newPlacesService() {
//		return PlacesService.newInstance(element);
//	}
//
//	// getters & setters:
//
//	public MapImpl getMapImpl() {
//		return mapImpl;
//	}
//
//	public void setMapImpl(MapImpl mapImpl) {
//		this.mapImpl = mapImpl;
//	}
//
//	public Element getElement() {
//		return element;
//	}
//
//	public void setElement(Element element) {
//		this.element = element;
//	}
//
//	public MapOptions getOptions() {
//		return options;
//	}
//
//	public void setOptions(MapOptions options) {
//		this.options = options;
//	}
//
//}
