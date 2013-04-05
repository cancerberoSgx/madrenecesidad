package org.sgx.madrenecesidad.client.util.gmapsmissingapi;

import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.layers.TransitLayer;

public class MyTransitLayer extends TransitLayer {
	protected MyTransitLayer() {
	}

	public native final void setMap(MapImpl m)/*-{
		this.setMap(m);
	}-*/;

}
