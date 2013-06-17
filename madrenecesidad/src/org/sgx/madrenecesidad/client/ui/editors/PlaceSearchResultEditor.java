package org.sgx.madrenecesidad.client.ui.editors;

import java.util.List;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.model.Place;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class PlaceSearchResultEditor extends UIObject implements MNEditor<List<Place>>{

	private static PlaceSearchResultEditorUiBinder uiBinder = GWT.create(PlaceSearchResultEditorUiBinder.class);

	interface PlaceSearchResultEditorUiBinder extends UiBinder<Element, PlaceSearchResultEditor> {
	}

	@UiField TableElement table;
	@UiField Element title; 
	
	String[] columnName = {"Name", "Descripion", "Actions"}; 
	
	public PlaceSearchResultEditor() {
		setElement(uiBinder.createAndBindUi(this));
	}

	@Override
	public List<Place> flush() {
//		Sizzle.sizzleArray(selector, context)
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(List<Place> t) {
		DOMUtil.clear(table);		
		if(t==null||t.size()==0) {
			title.setInnerText("No Places found. ");
		}
		else {			
			title.setInnerText(t.size()+" places found. ");
			installHeaders(); 
			for(Place p : t) {
				addPlace(p); 
			}
		}
	}


	private void installHeaders() {
		TableSectionElement thead = Document.get().createTHeadElement();
		table.appendChild(thead); 
		TableRowElement tr = Document.get().createTRElement();
		thead.appendChild(tr); 
		for (int i = 0; i < columnName.length; i++) {
			TableCellElement th = Document.get().createTHElement();
			th.setInnerText(columnName[i]); 
			tr.appendChild(th); 
		}		 
	}

	private void addPlace(Place p) {
		TableRowElement tr = Document.get().createTRElement();
		tr.setAttribute("data-place-id", p.getId()+""); 
		table.appendChild(tr); 
		
		TableCellElement td = Document.get().createTDElement();
		td.setInnerText(p.getName()); 
		tr.appendChild(td); 
		
		td = Document.get().createTDElement();
		td.setInnerText(p.getDescription()); 
		tr.appendChild(td);
		
		td = Document.get().createTDElement();
		td.setInnerText("ACTIONS"); 
		tr.appendChild(td);
	}
	
//	private void addNoResultsMsg() {
//		htmlu
//		TableRowElement tr = Document.get().createTRElement();
//		TableCellElement td = Document.get().createTDElement();
//		td.setInnerText("No Places found. "); 
//		td.setAttribute("colspan", "3"); 
//		tr.appendChild(td); 
//		table.appendChild()
//		table.a
//	}


}
