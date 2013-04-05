package org.sgx.madrenecesidad.client;

import org.sgx.madrenecesidad.client.app2.AppMain;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;

public class MadreNecesidad implements EntryPoint/*, AppControllerListener*/ {

	public void onModuleLoad() {
		 
//		main1(); 
//		main2();
//		main3(); 
		
		gwtBootStrapTest();
	}

	private void gwtBootStrapTest() {
		Document.get().getBody().appendChild(new AppMain().getElement()); 
//		Document.get().getBody().appendChild(new SimpleHTMLWidget().getElement());
		doBootStrap();
	}
	private  native final void doBootStrap()/*-{
		$wnd.$('.dropdown-toggle').dropdown(); 
	}-*/; 

//	private void main3() {
//		
//		//needed for image sprites
//		MNResources res = GWT.create(MNResources.class);  
//		res.style().ensureInjected(); 
//		
//		
//		YUI.Use(MNConstants.initialYUIModules, new YUICallback() {
//
//			@Override
//			public void ready(final YuiContext Y) {
//				//YUI is ready
//				
//				AppControllerUtil.initApp(Y, MadreNecesidad.this); 
//				
//			}
//		});
//	}
//
//	@Override
//	public void ready(AppController cc) {
//		cc.renderMainLayout();
//		cc.performCurrentUrlUseCase(); 
//		//client Context is ready
////		cc = AppController.getInstance();
////		YuiContext Y = cc.getY(); 
//		
//		
////		Node parent = Y.one("body"); 
////		parent.addClass("yui3-skin-sam"); 
////		// create a GWT Widget Panel that wraps our yui widget parent.
////		HTMLPanel gwtParent = parent.asHTMLPanel(); 
////				
////		MainLayout1 comp1 = new MainLayout1(Y);
////		gwtParent.add(comp1);
//		
//	}
	
	
//	private void main1() {
//		Main.getInstance().start(); 
//	}
//
//	private void main2() {
//		Main2.getInstance().start(); 
//	}


}
