package org.sgx.madrenecesidad.client.ui.action;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.ui.AppMain;
import org.sgx.madrenecesidad.client.ui.editors.PlaceEditor;

import com.google.appengine.api.datastore.GeoPt;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;

public class AddMapViewAction extends UIObject implements AppStateAction {

	private static AddMapViewActionUiBinder uiBinder = GWT.create(AddMapViewActionUiBinder.class);

	interface AddMapViewActionUiBinder extends UiBinder<Element, AddMapViewAction> {
	}

	public AddMapViewAction() {
		setElement(uiBinder.createAndBindUi(this));
	}

	@UiField
	Element editorEl, addButton; //modalBody, modal, , closeButton;
	
//	private HandlerRegistration clickHandler;
	private LatLng position;

	private PlaceEditor editor;
	
	@Override
	public void perform(Object config) {
		
		AppMain layout = MNMain.getInstance().getLayout();
		layout.setStatusText("Confirm you want to add this map view...", null);
//		clickHandler = layout.getMapWidget().addClickHandler(new ClickMapHandler() {
//			
//			@Override
//			public void onEvent(ClickMapEvent event) {
//				position=event.getMouseEvent().getLatLng();
//				doAddPlace();
//			}
//		});
		
//		layout.getEditorPanel().appendChild(this.getElement()); 
//		layout.getCollapseEditorButton().setVisible(true); 
	}

	protected void doAddPlace() {
		AppMain layout = MNMain.getInstance().getLayout();	
		MapView mv = new MapView(); 
		
//		mv.setCenter(new GeoPt((float)layout.getMapWidget().getCenter().getLatitude(), (float)layout.getMapWidget().getCenter().getLongitude())); 
//		mv.setZoom(layout.getMapWidget().getZoom()); 

		mv.setCenter(new GeoPt(0,1)); 
		mv.setZoom(1); 
		
//		Place p = new Place(); 
//		p.setCenter(new GeoPt((float)position.getLatitude(), (float)position.getLongitude()));
//		p.setName("some place name..."); 
//		p.setDescription("some place description"); 
//		editor = new PlaceEditor();
//		editor.load(p); 
//		modalBody.appendChild(editor.getElement());
//		Document.get().getBody().appendChild(this.getElement());
//		Bootstrap.modal(modal);
//		
//		
		DOMUtil.addClickHandler(addButton, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
//				Place place = editor.flush(); 
//				MNServiceFactory.getInstance().getPlaceService().addPlace(place, new AsyncCallback<Long>() {					
//					@Override
//					public void onSuccess(Long result) {
//						MNMain.getInstance().getLayout()
//							.setStatusText("New Place Added successfully! New place id: "+result, "text-success");
//						Bootstrap.modal(modal, "hide"); 
//					}					
//					@Override
//					public void onFailure(Throwable caught) {
//						MNMain.getInstance().getLayout()
//							.setStatusText("An error occurred while trying to add the place: "+caught, "text-error");					
//						Bootstrap.modal(modal, "hide"); 
//					}
//				}); 
			}
		});
//		DOMUtil.addClickHandler(closeButton, new DOMUtil.EventHandler() {
//			@Override
//			public void onEvent(Event event) {
//				Bootstrap.modal(modal, "hide"); 
//			}
//		});
		
	}

	@Override
	public void uninstall() {
		this.getElement().removeFromParent();
//		clickHandler.removeHandler(); 
	}

}
