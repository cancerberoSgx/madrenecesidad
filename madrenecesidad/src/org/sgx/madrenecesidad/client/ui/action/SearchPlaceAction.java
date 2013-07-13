package org.sgx.madrenecesidad.client.ui.action;

import java.util.List;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.service.MNServiceFactory;
import org.sgx.madrenecesidad.client.ui.search.PlaceSearchEditor;
import org.sgx.madrenecesidad.client.ui.search.PlaceSearchModel;
import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SearchPlaceAction extends Composite implements AppStateAction {

	private static SearchPlaceActionUiBinder uiBinder = GWT.create(SearchPlaceActionUiBinder.class);

	interface SearchPlaceActionUiBinder extends UiBinder<Widget, SearchPlaceAction> {
	}

	public SearchPlaceAction() {
		initWidget(uiBinder.createAndBindUi(this));
//		System.out.println("seba123SearchPlaceAction");
	}

	@UiField
	Element modalBody, modal, searchButton, closeButton;
	private PlaceSearchModel psm;
	private PlaceSearchEditor pse;
	
	@Override
	public void perform(Object config) {
		psm = new PlaceSearchModel(); 
		psm.setKeywords("big fish");
		psm.setInCurrentMapView(false); 
		pse = new PlaceSearchEditor(); 
		pse.load(psm); 
		modalBody.appendChild(pse.getElement());
		Document.get().getBody().appendChild(modal); 
		Bootstrap.modal(modal);
		DOMUtil.addClickHandler(searchButton, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
				doSearch(); 
			}
		}); 
	}

	protected void doSearch() {
		String keywords = pse.flush().getKeywords();
		MNServiceFactory.getInstance().getPlaceService().searchPlace(keywords, new AsyncCallback<List<Place>>() {			
			@Override
			public void onSuccess(List<Place> result) {
				String s = "";
				for(Place p : result) {
					s+=p.getName(); 
				}
				MNMain.getInstance().getLayout()
					.setStatusText("search result: ("+result.size()+") "+s , "text-success");
				Bootstrap.modal(modal, "hide"); 
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MNMain.getInstance().getLayout()
					.setStatusText("An error occurred while trying to search place: "+caught, "text-error");					
				Bootstrap.modal(modal, "hide"); 
			}
		});				
	}


	@Override
	public void uninstall() {
		this.getElement().removeFromParent();
	}

}
