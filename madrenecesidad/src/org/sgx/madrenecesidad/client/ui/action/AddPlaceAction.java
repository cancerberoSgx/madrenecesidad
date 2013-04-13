package org.sgx.madrenecesidad.client.ui.action;

import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.ui.editors.PlaceEditor;
import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;

import com.google.appengine.api.datastore.GeoPt;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AddPlaceAction extends Composite implements Action {

	private static AddPlaceActionUiBinder uiBinder = GWT.create(AddPlaceActionUiBinder.class);

	interface AddPlaceActionUiBinder extends UiBinder<Widget, AddPlaceAction> {
	}

	public AddPlaceAction() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Element modalBody, modal;
	
	private HandlerRegistration clickHandler;
	private LatLng position;
	
	@Override
	public void perform(Object config) {
		MNMain.getInstance().getLayout()
				.setStatusText("Click on the map for the location of the place you whish to add", null);
		clickHandler = MNMain.getInstance().getLayout().getMapWidget().addClickHandler(new ClickMapHandler() {
			
			@Override
			public void onEvent(ClickMapEvent event) {
				MarkerOptions opts = MarkerOptions.newInstance();
				position=event.getMouseEvent().getLatLng();
				opts.setPosition(position);
				opts.setTitle("new place's position"); 
				Marker marker = Marker.newInstance(opts);
				marker.setMap(MNMain.getInstance().getLayout().getMapWidget());
				clickHandler.removeHandler();
				doAddPlace();
			}
		});
		
	}

	protected void doAddPlace() {
		Place p = new Place(); 
		p.setCenter(new GeoPt((float)position.getLatitude(), (float)position.getLongitude()));
		p.setName("some place name..."); 
		p.setDescription("some place description"); 
		PlaceEditor ed = new PlaceEditor();
		ed.load(p); 
		modalBody.appendChild(ed.getElement());
		Document.get().getBody().appendChild(this.getElement());
		Bootstrap.modal(modal);
	}

	@Override
	public void uninstall() {
		this.getElement().removeFromParent();
		clickHandler.removeHandler(); 
	}

}
