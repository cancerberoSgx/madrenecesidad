package org.sgx.madrenecesidad.client.ui.action;

import org.sgx.madrenecesidad.client.MNMain;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.geometrylib.SphericalUtils;
import com.google.gwt.user.client.Window;

public class MeasureDistanceAction implements AppStateAction {

	int clickcount=0;
	ClickMapEvent click1, click2;
	HandlerRegistration clickHandler=null;
	@Override
	public void perform(Object config) {
		MNMain.getInstance().getLayout().setStatusText("Please make two clicks in the map for measure the distance between them", null); 
		clickHandler = MNMain.getInstance().getLayout().getMapWidget().addClickHandler(new ClickMapHandler() {
			
			@Override
			public void onEvent(ClickMapEvent event) {
				System.out.println("MeasureDistanceAction click");
				if(clickcount==0) {
					click1=event;
					clickcount++;
				}
				else if(clickcount==1) {
					click2=event;
					clickcount++;
					clickHandler.removeHandler();
					doMeasure();
				}
			}
		}); 
	}
	protected void doMeasure() {
		LatLng pos1 = click1.getMouseEvent().getLatLng();
		LatLng pos2 = click2.getMouseEvent().getLatLng();
		double d = SphericalUtils.computeDistanceBetween(pos1, pos2); 
		Window.alert("distance between two points is: "+d+" meters"); 
	}
	@Override
	public void uninstall() {
		if(clickHandler!=null)
			clickHandler.removeHandler(); 
	}

}
