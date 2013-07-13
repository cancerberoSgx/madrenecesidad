package org.sgx.madrenecesidad.client;

import org.sgx.madrenecesidad.client.state.MNStateManager;
import org.sgx.madrenecesidad.client.ui.AppMain;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;

public class MadreNecesidad implements EntryPoint {

	public void onModuleLoad() {
		main();		
//		test1();
//		test2();
	}
	private void main() {
		AppMain mainlayout = new AppMain();		
		MNMain.getInstance().setLayout(mainlayout);
		
		MNStateManager stateManager = new MNStateManager(); 
		MNMain.getInstance().setStateManager(stateManager); 
		
//		mainlayout.attachTo(Document.get().getBody());//
		Document.get().getBody().appendChild(mainlayout.getElement()); 
		
		MNMain.getInstance().notifyAfterAttach();
		
		stateManager.navigate(stateManager.getDefaultState(), "");	
		
		if(MNMain.storage().get(MNConstants.STORAGE_LANGUAGE)!=null) {
			MNMain.langManager().loadLang(MNMain.storage().get(MNConstants.STORAGE_LANGUAGE)); 
		}
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

	
}
