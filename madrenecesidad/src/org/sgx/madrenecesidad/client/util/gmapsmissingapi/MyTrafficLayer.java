package org.sgx.madrenecesidad.client.util.gmapsmissingapi;

import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.layers.TrafficLayer;

public class MyTrafficLayer extends TrafficLayer {
	protected MyTrafficLayer() {
	}

	public native final void setMap(MapImpl m)/*-{
		this.setMap(m);
	}-*/;

}
