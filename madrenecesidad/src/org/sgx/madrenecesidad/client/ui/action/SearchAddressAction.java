package org.sgx.madrenecesidad.client.ui.action;

import org.sgx.jsutil.client.JsUtil;
import org.sgx.jsutil.client.SimpleCallback;
import org.sgx.madrenecesidad.client.MNMain;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.maps.client.services.Geocoder;
import com.google.gwt.maps.client.services.GeocoderRequest;
import com.google.gwt.maps.client.services.GeocoderRequestHandler;
import com.google.gwt.maps.client.services.GeocoderResult;
import com.google.gwt.maps.client.services.GeocoderStatus;
import com.google.gwt.user.client.Window;

public class SearchAddressAction implements Action{

	@Override
	public void perform(Object config) {
		final String address = Window.prompt("Enter the address you whish to find", "Zapican 2560");
		if(address==null)
			return;
		GeocoderRequest req = GeocoderRequest.newInstance(); 
		req.setAddress(address); 
		Geocoder.newInstance().geocode(req, new GeocoderRequestHandler() {			
			@Override
			public void onCallback(JsArray<GeocoderResult> results, GeocoderStatus status) {
				for (int i = 0; i < results.length(); i++) {
					final GeocoderResult r = results.get(i);
					JsUtil.setTimeout(new SimpleCallback() {						
						@Override
						public void call() {
							MapWidget map = MNMain.getInstance().getLayout().getMapWidget();
							LatLng pos = r.getGeometry().getLocation();							
							map.setCenter(r.getGeometry().getLocation());							
							MarkerOptions opts = MarkerOptions.newInstance();
							opts.setMap(map); 
							opts.setTitle(address); 
							opts.setPosition(pos); 
							Marker marker1 = Marker.newInstance(opts);// event.getMouseEvent().getLatLng(), markerOpts);
							marker1.setMap(map); 
						}
					}, (i+1)*1700); 
				}
			}
		}); 
	}

}
