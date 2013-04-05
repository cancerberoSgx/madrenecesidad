package org.sgx.madrenecesidad.client;

import org.sgx.madrenecesidad.client.ui.AppMain;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;

public class MadreNecesidad implements EntryPoint {

	public void onModuleLoad() {
		gwtBootStrapTest();
	}

	private void gwtBootStrapTest() {
		AppMain mainlayout = new AppMain();
		MNMain.getInstance().setLayout(mainlayout); 
		Document.get().getBody().appendChild(mainlayout.getElement());
		MNMain.getInstance().notifyAfterAttach();
	}

}
