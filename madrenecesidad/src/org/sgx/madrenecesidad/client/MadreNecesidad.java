package org.sgx.madrenecesidad.client;

import org.sgx.gwteditors.client.impl1.EditorFramework1;
import org.sgx.gwteditors.client.impl1.complex.PropertyHaverEditor2;
import org.sgx.madrenecesidad.client.ui.AppMain;
import org.sgx.madrenecesidad.client.ui.gwteditors.EditablePlace;
import org.sgx.madrenecesidad.client.ui.gwteditors.SearchResults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootPanel;

public class MadreNecesidad implements EntryPoint {

	public void onModuleLoad() {
		main();
		
//		test1();
//		test2();
	}

//	private void test2() {
//		new EditorFramework1().start(); 
//		EditablePlace place = EditablePlace.create(); 
//		PropertyHaverEditor2<EditablePlace> ed = new PropertyHaverEditor2<EditablePlace>(); 
////		el1.appendChild(ed.getWidget().getElement()); 
//		RootPanel.get().add(ed); 
//		ed.load(place); 
//	}
//
//	private void test1() {
//		SearchResults mainlayout = new SearchResults(); 
//		Document.get().getBody().appendChild(mainlayout.getElement());
//	}

	private void main() {
		AppMain mainlayout = new AppMain();
		MNMain.getInstance().setLayout(mainlayout); 
		Document.get().getBody().appendChild(mainlayout.getElement());
		MNMain.getInstance().notifyAfterAttach();
	}

}
