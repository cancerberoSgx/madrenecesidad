package org.sgx.madrenecesidad.client.ui.action;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.service.MNServiceFactory;
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
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	Element modalBody, modal, addButton, closeButton;
	
	private HandlerRegistration clickHandler;
	private LatLng position;

	private PlaceEditor editor;
	
	@Override
	public void perform(Object config) {
		
		MNMain.getInstance().getLayout()
				.setStatusText("Click on the map for the location of the place you whish to add", null);
		clickHandler = MNMain.getInstance().getLayout().getMapWidget().addClickHandler(new ClickMapHandler() {
			
			@Override
			public void onEvent(ClickMapEvent event) {
				position=event.getMouseEvent().getLatLng();
				doAddPlace();
			}
		});
		
	}

	protected void doAddPlace() {
		//a marker
		MarkerOptions opts = MarkerOptions.newInstance();
		opts.setPosition(position);
		opts.setTitle("new place's position"); 
		Marker marker = Marker.newInstance(opts);
		marker.setMap(MNMain.getInstance().getLayout().getMapWidget());
		clickHandler.removeHandler();
		
		//a place editor
		Place p = new Place(); 
		p.setCenter(new GeoPt((float)position.getLatitude(), (float)position.getLongitude()));
		p.setName("some place name..."); 
		p.setDescription("some place description"); 
		editor = new PlaceEditor();
		editor.load(p); 
		modalBody.appendChild(editor.getElement());
		Document.get().getBody().appendChild(this.getElement());
		Bootstrap.modal(modal);
		
		
		DOMUtil.addClickHandler(addButton, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				Place place = editor.flush(); 
				MNServiceFactory.getInstance().getPlaceService().addPlace(place, new AsyncCallback<Long>() {
					
					@Override
					public void onSuccess(Long result) {
						MNMain.getInstance().getLayout()
							.setStatusText("New Place Added successfully! New place id: "+result, "text-success");
						Bootstrap.modal(modal, "hide"); 
					}
					
					@Override
					public void onFailure(Throwable caught) {
						MNMain.getInstance().getLayout()
							.setStatusText("An error occurred while trying to add the place: "+caught, "text-error");					
						Bootstrap.modal(modal, "hide"); 
					}
				}); 
			}
		});
		DOMUtil.addClickHandler(closeButton, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				Bootstrap.modal(modal, "hide"); 
			}
		});
		
	}

	@Override
	public void uninstall() {
		this.getElement().removeFromParent();
		clickHandler.removeHandler(); 
	}

}
