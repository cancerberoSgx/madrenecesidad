package org.sgx.madrenecesidad.client.ui.gwteditors;

import org.sgx.gwteditors.client.impl1.complex.PropertyHaverEditor2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class SearchResults extends Composite {

	private static SearchResultsUiBinder uiBinder = GWT.create(SearchResultsUiBinder.class);

	interface SearchResultsUiBinder extends UiBinder<Widget, SearchResults> {
	}
	
	@UiField Element el1;
	
	public SearchResults() {
		initWidget(uiBinder.createAndBindUi(this));
		EditablePlace place = EditablePlace.create(); 
		PropertyHaverEditor2<EditablePlace> ed = new PropertyHaverEditor2<EditablePlace>(); 
//		el1.appendChild(ed.getWidget().getElement()); 
		RootPanel.get().add(ed); 
		ed.load(place); 
	}

}
