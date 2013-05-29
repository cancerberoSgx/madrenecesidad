package org.sgx.madrenecesidad.client.ui;

import java.util.ArrayList;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.SimpleCallback;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.ui.help.AboutTheAuthor;
import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;
import org.sgx.madrenecesidad.client.util.gmapsmissingapi.PlaceSearchTypes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.controls.MapTypeControlOptions;
import com.google.gwt.maps.client.events.center.CenterChangeMapEvent;
import com.google.gwt.maps.client.events.center.CenterChangeMapHandler;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.maptypeid.MapTypeIdChangeMapEvent;
import com.google.gwt.maps.client.events.maptypeid.MapTypeIdChangeMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.maps.client.overlays.MarkerShape;
import com.google.gwt.maps.client.placeslib.PlaceResult;
import com.google.gwt.maps.client.placeslib.PlaceSearchHandler;
import com.google.gwt.maps.client.placeslib.PlaceSearchRequest;
import com.google.gwt.maps.client.placeslib.PlacesService;
import com.google.gwt.maps.client.placeslib.PlacesServiceStatus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.UIObject;

public class AppMain extends UIObject {

	private static AppMainUiBinder uiBinder = GWT.create(AppMainUiBinder.class);

	interface AppMainUiBinder extends UiBinder<Element, AppMain> {
	}

	@UiField
	Element mainMapContainer, userPanelEl, actionPanelEl, statusText, 
	helpAboutTheAuthor;
	private MapWidget mapWidget;
	private ActionPanel actionPanel;

	public AppMain() {
		setElement(uiBinder.createAndBindUi(this));
		loadMapApi();
		Document.get().getBody().appendChild(new AppMainCss().getElement());
		userPanelEl.appendChild(new UserPanel().getElement()); 
		actionPanel=new ActionPanel();
		actionPanelEl.appendChild(actionPanel.getElement());
		MNMain.getInstance().addAfterAttachListener(new SimpleCallback() {			
			@Override
			public void call() {
				Bootstrap.dropdown(".dropdown-toggle"); 
			}
		}); 
		
		DOMUtil.addClickHandler(helpAboutTheAuthor, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				new AboutTheAuthor(Document.get().getBody()); 
			}
		});
	}
	public ActionPanel getActionPanel() {
		return this.actionPanel; 
	}

	public MapWidget getMapWidget() {
		return mapWidget;
	}
	private void loadMapApi() {
		boolean sensor = true;

		// load all the libs for use in the maps
		ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
		loadLibraries.add(LoadLibrary.ADSENSE);
		loadLibraries.add(LoadLibrary.DRAWING);
		loadLibraries.add(LoadLibrary.GEOMETRY);
		loadLibraries.add(LoadLibrary.PANORAMIO);
		loadLibraries.add(LoadLibrary.PLACES);
		loadLibraries.add(LoadLibrary.WEATHER);
		 loadLibraries.add(LoadLibrary.VISUALIZATION);

		Runnable onLoad = new Runnable() {
			@Override
			public void run() {
				drawMap();
			}
		};
		
		LoadApi.go(onLoad, loadLibraries, sensor);
	}
	
	/**
	 * 
	 * @param text
	 * @param type one of "muted", "text-warning", "text-error", "text-info", "text-success"
	 */
	public void setStatusText(String text, String type) {
		if(type==null||type.equals(""))
			type="text-info"; 
		statusText.setInnerText(text); 
		statusText.setClassName(type);
	}

	private void drawMap() {
		LatLng center = LatLng.newInstance(40.46387840039735, -3.735442161560054);
		MapOptions mapOpts = MapOptions.newInstance();
		mapOpts.setMapTypeId(MapTypeId.ROADMAP);
		mapOpts.setZoom(10);
//		mapOpts.setScaleControl(true);
		mapOpts.setCenter(center);
		mapWidget = new MapWidget(mapOpts);
//		mapWidget.addCenterChangeHandler(new CenterChangeMapHandler() {
//			@Override
//			public void onEvent(CenterChangeMapEvent event) {
//				// TODO Auto-generated method stub
//
//			}
//		});
		// mapWidget.addMapTypeIdChangeHandler(new MapTypeIdChangeMapHandler() {
		// @Override
		// public void onEvent(MapTypeIdChangeMapEvent event) {
		// Window.alert(mapWidget.getMapTypeId().value());
		// }
		// });
//		mapWidget.addClickHandler(new ClickMapHandler() {
//			@Override
//			public void onEvent(ClickMapEvent event) {
////				Window.alert(event.getMouseEvent().getLatLng() + " lang");
//				gmapClicked(event); 
//			}
//		});

		// mapWidget.setOptions(opts); //.setCenter(latlng)
		mainMapContainer.appendChild(mapWidget.getElement());
		
//		mapWidget.getParent().getElement().getStyle().setProperty("border", "4px solid pink");
		int minMapHeight=400;
		int h = Math.max(Window.getClientHeight()-100, minMapHeight);		
		mapWidget.setSize("100%", +h+"px");
	}

//	protected void gmapClicked(ClickMapEvent event) {
////		 Window.alert("zoom: " + mapWidget.getZoom()+ ", LatLng: " + mapWidget.getCenter()); 
//
//		LatLng clickLocation = event.getMouseEvent().getLatLng();
//		
//		MarkerOptions opts = MarkerOptions.newInstance();
////		opts.setClickable(true);
////		opts.setDraggable(true);
//		opts.setMap(mapWidget); 
//		opts.setTitle("hello!"); 
//		opts.setPosition(clickLocation); 
//		Marker marker1 = Marker.newInstance(opts);// event.getMouseEvent().getLatLng(), markerOpts);
//		marker1.addClickHandler(new ClickMapHandler() {
//			@Override
//			public void onEvent(ClickMapEvent event) {
//				// Marker m = event.getSource().cast();
//				Window.alert("marker clicked: " + event.getSource());
//			}
//		});
//		
////		PlaceSearchRequest req = PlaceSearchRequest.newInstance();
////		req.setLocation(clickLocation);
////		req.setRaidus(50000);
////		req.setTypes(PlaceSearchTypes.embassy);
////		// gmap1=mapWidget.getm
////		PlacesService placeService = PlacesService.newInstance(mapWidget); // gmap1.newPlacesService(); //PlacesService.newInstance(ma);
////		placeService.search(req, new PlaceSearchHandler() {
////			@Override
////			public void onCallback(JsArray<PlaceResult> results, PlacesServiceStatus status) {
////				Window.alert("embassies found in the area : " + results.length());
////				for (int i = 0; i < results.length(); i++) {
////					PlaceResult result = results.get(i);
////				}
////			}
////		});
//	}
}
