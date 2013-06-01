package org.sgx.madrenecesidad.client.ui.action;

import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchEditor;
import org.sgx.madrenecesidad.client.ui.editors.PlaceSearchModel;
import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SearchPlaceAction extends Composite implements Action {

	private static SearchPlaceActionUiBinder uiBinder = GWT.create(SearchPlaceActionUiBinder.class);

	interface SearchPlaceActionUiBinder extends UiBinder<Widget, SearchPlaceAction> {
	}

	public SearchPlaceAction() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Element modalBody, modal, addButton, closeButton;
	
//	private HandlerRegistration clickHandler;
//	private LatLng position;

//	private PlaceEditor editor;
	
	@Override
	public void perform(Object config) {
		
//		MNMain.getInstance().getLayout()
//				.setStatusText("Click on the map for the location of the place you whish to add", null);
//		clickHandler = MNMain.getInstance().getLayout().getMapWidget().addClickHandler(new ClickMapHandler() {
//			
//			@Override
//			public void onEvent(ClickMapEvent event) {
//				position=event.getMouseEvent().getLatLng();
//				doAddPlace();
//			}
//		});
		doSearchPlace(); 
	}

	protected void doSearchPlace() {
		
		PlaceSearchModel psm = new PlaceSearchModel(); 
		psm.setKeywords("big fish");
		psm.setInCurrentMapView(false); 
		PlaceSearchEditor pse = new PlaceSearchEditor(); 
		pse.load(psm); 
		modalBody.appendChild(pse.getElement());
		Document.get().getBody().appendChild(modal); 
		Bootstrap.modal(modal);
	}

	@Override
	public void uninstall() {
		this.getElement().removeFromParent();
	}

}
