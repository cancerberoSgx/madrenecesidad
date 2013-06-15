package org.sgx.madrenecesidad.client.ui.action;

import java.util.LinkedList;
import java.util.List;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.maps.client.overlays.Polyline;
import com.google.gwt.maps.client.overlays.PolylineOptions;
import com.google.gwt.maps.client.services.DirectionsLeg;
import com.google.gwt.maps.client.services.DirectionsRequest;
import com.google.gwt.maps.client.services.DirectionsResult;
import com.google.gwt.maps.client.services.DirectionsResultHandler;
import com.google.gwt.maps.client.services.DirectionsService;
import com.google.gwt.maps.client.services.DirectionsStatus;
import com.google.gwt.maps.client.services.DirectionsStep;
import com.google.gwt.maps.client.services.TravelMode;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class DirectionsAction implements AppStateAction {

	HandlerRegistration clickHandler;
	List<Marker> markers;
	int clickCount=0;
	DirectionsActionUi ui;

	public DirectionsAction(){
		markers=new LinkedList<Marker>(); 
	}
	@Override
	public void perform(Object config) {
		String text = "Click on two points of the map and choose a method of transportation for getting the directions";
		MNMain.getInstance().getLayout().setStatusText(text, null);
		ui = new DirectionsActionUi();
		
		DOMUtil.addClickHandler(ui.getDirectionsButton(), new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				doDirections();
			}
		});
		
		MNMain.getInstance().getLayout().getActionPanel().setOptionPanel(ui.getElement());
		clickHandler = MNMain.getInstance().getLayout().getMapWidget().addClickHandler(new ClickMapHandler() {
			
			@Override
			public void onEvent(ClickMapEvent event) {
				clickCount++;
				MarkerOptions opts = MarkerOptions.newInstance();				 
				opts.setTitle(clickCount+""); 
				opts.setPosition(event.getMouseEvent().getLatLng()); 
				Marker marker = Marker.newInstance(opts);
				marker.setMap(MNMain.getInstance().getLayout().getMapWidget());
				markers.add(marker); 
				
			}
		}); 
	}
	protected void doDirections() {
		if(clickHandler!=null)
			clickHandler.removeHandler(); 
		for(Marker m : markers) {
			m.setMap((MapWidget)null); 
		}
		if(markers.size()<2)
			Window.alert("You need two or more points for getting directions"); 
		DirectionsService ds = DirectionsService.newInstance(); 
		DirectionsRequest req = DirectionsRequest.newInstance();
		LatLng origin = markers.get(0).getPosition(), dest = markers.get(1).getPosition();
		req.setOrigin(origin); 
		req.setDestination(dest); 
		req.setTravelMode(TravelMode.WALKING); 
		
		ds.route(req, new DirectionsResultHandler() {			
			@Override
			public void onCallback(DirectionsResult result, DirectionsStatus status) {
				String s = "";

				JsArray<LatLng> polyPath = JsArray.createArray().cast(); 
				for (int i = 0; i < result.getRoutes().length(); i++) {
					JsArray<DirectionsLeg> legs = result.getRoutes().get(i).getLegs();
					for (int j = 0; j < legs.length(); j++) {
						DirectionsLeg leg = legs.get(j);
//						s+="LEG start: "+leg.getStart_Address()+", end: "+leg.getEnd_Address()+"\n";
						for (int k = 0; k < leg.getSteps().length(); k++) {
							DirectionsStep step = leg.getSteps().get(k);
							s+=
//									"STEPS: instructions: "+
							step.getInstructions()
//							+" - distance: "+step.getDistance()
							+"<br/>"
							; 
							
							polyPath.push(step.getStart_Location()); 
						}
					}
				}

				PolylineOptions popts = PolylineOptions.newInstance(); 
				popts.setPath(polyPath); 
				Polyline line = Polyline.newInstance(popts); 
				line.setMap(MNMain.getInstance().getLayout().getMapWidget()); 
				
				ui.getModalBody().setInnerHTML("<h3>Instructions</h3>"+s); 
				Bootstrap.modal(ui.getModal()); 				
			}
		}); 
	}
	@Override
	public void uninstall() {
		if(clickHandler!=null)
			clickHandler.removeHandler();
	}

}
