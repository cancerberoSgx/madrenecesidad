package org.sgx.madrenecesidad.client.geolocation;

import com.google.gwt.core.client.Callback;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;

public class GeoLocationManager {
	Geolocation geo = null; 
	public GeoLocationManager(){
		this.geo = Geolocation.getIfSupported();
	}
	public Geolocation getGeo() {
		return geo;
	}
//	public static interface Callback {
//		
//	}
//	
//public void getPosition(Callback<Position, PositionError> callback) {
//	Geolocation geo = Geolocation.getIfSupported();
//	if(geo==null)
//		return; 
//	geo.getCurrentPosition(callback); 
//}
/**
 * @return
 */
public boolean isSupported() {
	return Geolocation.getIfSupported()!=null;
}
}
