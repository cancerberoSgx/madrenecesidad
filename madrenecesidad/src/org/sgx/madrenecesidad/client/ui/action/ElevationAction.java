package org.sgx.madrenecesidad.client.ui.action;

import java.util.LinkedList;
import java.util.List;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.mvc.MVCArray;
import com.google.gwt.maps.client.overlays.Polyline;
import com.google.gwt.maps.client.services.ElevationResult;
import com.google.gwt.maps.client.services.ElevationService;
import com.google.gwt.maps.client.services.ElevationServiceHandler;
import com.google.gwt.maps.client.services.ElevationStatus;
import com.google.gwt.maps.client.services.LocationElevationRequest;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class ElevationAction implements Action {

	private HandlerRegistration clickHandler;
//	List<LatLng> points=new LinkedList<LatLng>();
	private ElevationActionUi ui;
	private Polyline poly;
	private JsArray<LatLng> path;

	@Override
	public void perform(Object config) {
		ui = new ElevationActionUi(); 
		MNMain.getInstance().getLayout().getActionPanel().setOptionPanel(ui.getElement());
		DOMUtil.addClickHandler(ui.getButton(), new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				doElevations();
			}
		});
		poly = Polyline.newInstance(null); 
		poly.setMap(MNMain.getInstance().getLayout().getMapWidget()); 
//		poly.setPath(path); 
		path = JsArray.createArray().cast();
		MNMain.getInstance().getLayout().setStatusText("Please make two or more clicks to draw the path in which the elevations will be calculated and then press the 'Get elevations' button.", null); 
		clickHandler = MNMain.getInstance().getLayout().getMapWidget().addClickHandler(new ClickMapHandler() {			
			@Override
			public void onEvent(ClickMapEvent event) {
//				points.add(event.getMouseEvent().getLatLng()); 
				path.push(event.getMouseEvent().getLatLng()); 
				poly.setPath(path); 
			}
		}); 
	}

	protected void doElevations() {
		LocationElevationRequest req = LocationElevationRequest.newInstance();
		req.setLocations(path); 
		ElevationService.newInstance().getElevationForLocations(req, new ElevationServiceHandler() {			
			@Override
			public void onCallback(JsArray<ElevationResult> result, ElevationStatus status) {
				String s = "";
				for (int i = 0; i < result.length(); i++) {
					s+=result.get(i).getElevation()+", "; 
				}
				Window.alert("Elevations: "+s); 
			}
		}); 
	}

	@Override
	public void uninstall() {
		if(clickHandler!=null)
			clickHandler.removeHandler();
		poly.setMap(null); 
	}

}
