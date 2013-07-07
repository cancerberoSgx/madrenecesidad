package org.sgx.madrenecesidad.client.ui.editors;

import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Place;

import com.google.appengine.api.datastore.GeoPt;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MapViewEditor extends Composite implements MNEditor<MapView>{

	private static MapViewEditorUiBinder uiBinder = GWT.create(MapViewEditorUiBinder.class);

	interface MapViewEditorUiBinder extends UiBinder<Widget, MapViewEditor> {
	}

	public MapViewEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField InputElement nameEntry, positionLatEntry, positionLongEntry; 
	@UiField TextAreaElement descriptionEntry; 
	
	@Override
	public MapView flush() {
//		Place p = new Place(); 
//		p.setName(nameEntry.getValue()); 
//		p.setDescription(descriptionEntry.getValue()); 
//		p.setCenter(new GeoPt(
//			Float.parseFloat(positionLatEntry.getValue()), 
//			Float.parseFloat(positionLongEntry.getValue())
//		)); 
//		return p;
		return null;
	}

	@Override
	public void load(MapView t) {
		nameEntry.setValue(t.getName()); 
		descriptionEntry.setValue(t.getDescription()); 
		positionLatEntry.setValue(t.getCenter().getLatitude()+""); 
		positionLongEntry.setValue(t.getCenter().getLongitude()+"");
	}

}
