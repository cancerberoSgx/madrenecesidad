package org.sgx.madrenecesidad.client.ui.search;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.JavaUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.state.MNStateManager;
import org.sgx.madrenecesidad.client.ui.editors.MNEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

public abstract class AbstractSearchResultEditor<T  extends Searchable> extends UIObject implements MNEditor<List<T>>{

	private static PlaceSearchResultEditorUiBinder uiBinder = GWT.create(PlaceSearchResultEditorUiBinder.class);

	interface PlaceSearchResultEditorUiBinder extends UiBinder<Element, AbstractSearchResultEditor> {
	}

	@UiField TableElement table;
	@UiField Element title; 
	String typeName; 
	
//	String[] columnName = {"Name", "Descripion", "Actions"}; 
	
	public AbstractSearchResultEditor(String typeName) {
		this.typeName=typeName;
		setElement(uiBinder.createAndBindUi(this));		
	}
	
	
	/**
	 * it is convenient to use LinkedHashMap Map implementation so the order of keys / columns ins insertion-order.
	 * @return
	 */
	public abstract Map<String, ColumnPrinter<T>> getColumns();
	
	@Override
	public List<T> flush() {
//		Sizzle.sizzleArray(selector, context)
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(List<T> t) {
		DOMUtil.clear(table);		
		if(t==null||t.size()==0) {
			title.setInnerText("No Places found. ");
		}
		else {			
			title.setInnerText(t.size()+" places found. ");
			installHeaders(); 
			for(T p : t) {
				addRow(p); 
			}
		}
	}


	private void installHeaders() {
		TableSectionElement thead = Document.get().createTHeadElement();
		table.appendChild(thead); 
		TableRowElement tr = Document.get().createTRElement();
		thead.appendChild(tr); 
		for(String columnName : getColumns().keySet()) {
			TableCellElement th = Document.get().createTHElement();
			th.setInnerText(columnName); 
			tr.appendChild(th); 
		}		 
	}

	private void addRow(T p) {

		TableRowElement tr = Document.get().createTRElement();
//		tr.setAttribute("data-"+cprinter.getTypeName()+"-"+p.getIndexId()+"");
		table.appendChild(tr);
		
		for(String colName : getColumns().keySet()) {
			ColumnPrinter cprinter = getColumns().get(colName); 
			TableCellElement td = Document.get().createTDElement();
			td.setInnerText(cprinter.getHTML(p)); 
			tr.appendChild(td); 
			addClickHandler(td, p);
		}

//		TableRowElement tr = Document.get().createTRElement();
//		tr.setAttribute("data-place-id", p.getId()+""); 
//		table.appendChild(tr); 
//		
//		TableCellElement td = Document.get().createTDElement();
//		td.setInnerText(p.getName()); 
//		tr.appendChild(td); 
//		
//		td = Document.get().createTDElement();
//		td.setInnerText(p.getDescription()); 
//		tr.appendChild(td);
//		
//		td = Document.get().createTDElement();
//		td.setInnerText("ACTIONS"); 
//		tr.appendChild(td);
		
		 //TOOD: use event delegation
	}

	private void addClickHandler(TableCellElement td, final T p) {
		td.getStyle().setProperty("cursor", "pointer"); 
		DOMUtil.addClickHandler(td, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
//				Window.alert("editing: "+p); 
				MNMain.states().navigate(MNStateManager.STATE_VIEWPLACE, JavaUtil.toMap2(MNStateManager.PARAM_ID, p.getIndexId())); 
			}
		}); 
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
