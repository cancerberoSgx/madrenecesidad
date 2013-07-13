//package org.sgx.madrenecesidad.client.ui;
//
//import java.util.ArrayList;
//
//import org.sgx.gwtsizzle.client.Sizzle;
//import org.sgx.jsutil.client.DOMUtil;
//import org.sgx.jsutil.client.DOMUtil.EventHandler;
//import org.sgx.jsutil.client.Callback;
//import org.sgx.jsutil.client.JsUtil;
//import org.sgx.jsutil.client.SimpleCallback;
//import org.sgx.madrenecesidad.client.MNConstants;
//import org.sgx.madrenecesidad.client.MNMain;
//import org.sgx.madrenecesidad.client.ui.util.CollapseButton;
//import org.sgx.madrenecesidad.client.ui.view.help.AboutTheAuthor;
//import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;
//import org.sgx.yuigwt.yui.YUICallback;
//import org.sgx.yuigwt.yui.YuiContext;
//import org.sgx.yuigwt.yui.YuiLoader;
//import org.sgx.yuigwt.yui.dd.Drag;
//import org.sgx.yuigwt.yui.dd.DragConfig;
//import org.sgx.yuigwt.yui.resize.Resize;
//import org.sgx.yuigwt.yui.resize.ResizeConfig;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.core.client.JsArrayMixed;
//import com.google.gwt.dom.client.DivElement;
//import com.google.gwt.dom.client.Document;
//import com.google.gwt.dom.client.Element;
//import com.google.gwt.event.logical.shared.AttachEvent;
//import com.google.gwt.maps.client.LoadApi;
//import com.google.gwt.maps.client.LoadApi.LoadLibrary;
//import com.google.gwt.maps.client.MapImpl;
//import com.google.gwt.maps.client.MapOptions;
//import com.google.gwt.maps.client.MapTypeId;
//import com.google.gwt.maps.client.MapWidget;
//import com.google.gwt.maps.client.base.LatLng;
//import com.google.gwt.uibinder.client.UiBinder;
//import com.google.gwt.uibinder.client.UiField;
//import com.google.gwt.user.client.Event;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.UIObject;
//
//public class CopyOfAppMain extends UIObject {
//
//	private static AppMainUiBinder uiBinder = GWT.create(AppMainUiBinder.class);
//
//	interface AppMainUiBinder extends UiBinder<Element, CopyOfAppMain> {
//	}
//
//	@UiField
//	Element mainMapContainer, userPanelEl, actionPanelEl, statusText, 
//	helpAboutTheAuthor, 
////	collapseMsgButtonEl, collapseEditorButtonEl, 
//	statePanelEl;
//		
//	private MapWidget mapWidget;
//	MapImpl mapImpl;
//	private ActionPanel actionPanel;
//
//	private CollapseButton collapseMsgButton;
//
////	private CollapseButton collapseEditorButton;
//
//	private StatePanel statePanel;
//
//	protected Object resize;
//
//	private DivElement mapEl;
//
////	private EditorPanel editorPanel;
//
//	public CopyOfAppMain() {
//		setElement(uiBinder.createAndBindUi(this));
//		loadMapApi();
//		drawLayout();
//		MNMain.getInstance().addAfterAttachListener(new SimpleCallback() {			
//			@Override
//			public void call() {
//				Bootstrap.dropdown(".dropdown-toggle"); 
//			}
//		}); 
//		
//		DOMUtil.addClickHandler(helpAboutTheAuthor, new DOMUtil.EventHandler() {
//			@Override
//			public void onEvent(Event event) {
//				new AboutTheAuthor(Document.get().getBody()); 
//			}
//		});
//
//		installLanguageSelector(); 
//
//	}
//	
//	
//
//private void drawLayout() {
//	Document.get().getBody().appendChild(new AppMainCss().getElement());
//	userPanelEl.appendChild(new UserPanel().getElement()); 
//	actionPanel=new ActionPanel();
//	actionPanelEl.appendChild(actionPanel.getElement());	
////	collapseMsgButton = new CollapseButton("show messages>>", "hide message<<", statusText); 
////	collapseMsgButtonEl.appendChild(collapseMsgButton.getElement()); 		
////
////	collapseEditorButton = new CollapseButton("show editor>>", "hide neditor<<", statePanelEl); 
////	collapseEditorButtonEl.appendChild(collapseEditorButton.getElement()); 
//	
//	statePanel = new StatePanel(); 
//	statePanelEl.appendChild(statePanel.getElement()); 
//	
////	if(MNMain.states().g)
////	YuiTest.main(null); 
//	doYUIResizableDraggableStuff(); 
//	
//}
//
//
//
//private void installLanguageSelector() {
//	EventHandler langSelHandler = new DOMUtil.EventHandler() {			
//		@Override
//		public void onEvent(Event event) {
//			Element target = event.getEventTarget().cast(); 
//			String langId = target.getAttribute("data-lang-selector");
////			System.out.println("install language selector handlers: "+langId);
//			if(langId!=null&&!langId.equals("")) {
//				MNMain.langManager().loadLang(langId);
//				MNMain.storage().set(MNConstants.STORAGE_LANGUAGE, langId); 					
//			}
//		}
//	}; 
//	for(Element langSelector : Sizzle.sizzleCol("[data-lang-selector]", getElement())) {
//		DOMUtil.addClickHandler(langSelector, langSelHandler); 
//	}
//}
//
//
//
//private static void doYUIResizableDraggableStuff() {
//	String yuijs = "http://yui.yahooapis.com/3.10.3/build/yui/yui-min.js";
//	String[] modules={"resize", "resize-plugin", "dd-plugin"};
//	YUICallback callback = new YUICallback() {			
//		@Override
//		public void ready(YuiContext Y) {
//			ResizeConfig resizeConfig = ResizeConfig.create().node(".main-tools-panel"); 
//			Resize resize = Y.newResize(resizeConfig); 
//			
//			DragConfig ddConfig = DragConfig.create().node(".main-tools-panel");
//			Drag dd = Y.newDDDrag(ddConfig)
//					.addHandle(".tabs, .alert"); 
//////			
////			Y.all(".state-panel .tabs").plug(Y.Plugin().Drag()); 
//			
//			
//		}
//	};
//	YuiLoader.load(yuijs, modules, callback); 
//}
//
//	public ActionPanel getActionPanel() {
//		return this.actionPanel; 
//	}
//
//	public MapWidget getMapWidget() {
//		return mapWidget;
//	}
//	/**
//	 * 
//	 * @param text
//	 * @param type one of "muted", "text-warning", "text-error", "text-info", "text-success"
//	 */
//	public void setStatusText(String text, String type) {
//		if(type==null||type.equals(""))
//			type="text-info"; 
//		statusText.setInnerText(text); 
//		statusText.setClassName(type);
//	}
//	public void setStatusError(String text) {
//		statusText.setInnerText(text); 
//		statusText.setClassName("text-error");
//	}
//	public void setStatusError(String text, Throwable e) {
//		statusText.setInnerText(text+", cause: "+e); 
//		statusText.setClassName("text-error");
//	}
//
////	public CollapseButton getCollapseEditorButton() {
////		return collapseEditorButton;
////	}
//	public StatePanel getStatePanel() {
//		return statePanel;
//	}
//	
//	
//	
//	
//	//map stuff
//
//	private void loadMapApi() {
//		System.out.println("loadMapApi");
//		if(MNConstants.turnTheMapOff)
//			return; 
//		boolean sensor = true;
//
//		// load all the libs for use in the maps
//		ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
//		loadLibraries.add(LoadLibrary.ADSENSE);
//		loadLibraries.add(LoadLibrary.DRAWING);
//		loadLibraries.add(LoadLibrary.GEOMETRY);
//		loadLibraries.add(LoadLibrary.PANORAMIO);
//		loadLibraries.add(LoadLibrary.PLACES);
//		loadLibraries.add(LoadLibrary.WEATHER);
//		 loadLibraries.add(LoadLibrary.VISUALIZATION);
//
//		Runnable onLoad = new Runnable() {
//			@Override
//			public void run() {
//				drawMap();
//				
//			}
//		};
//		LoadApi.go(onLoad, loadLibraries, sensor);	
//		
////		LatLng center = LatLng.newInstance(39.31, -106.02);
////	    MapOptions opts = MapOptions.newInstance();
////	    opts.setZoomControl(true); 
////	    opts.setZoom(8);
////	    opts.setCenter(center);
////	    opts.setMapTypeId(MapTypeId.TERRAIN);
////	    opts.setMapTypeControl(true); 
////	    String id = mainMapContainer.getId(); 
////	    JsUtil.whenDocumentContains(mainMapContainer, new SimpleCallback() {
////			
////			@Override
////			public void call() {
////				// TODO Auto-generated method stub
////				
////			}
////		}); 
////	    Window.alert(JsUtil.documentContains(mainMapContainer)+"");
////	    if(id==null||id.equals("")){	    	
////	    	id=JsUtil.getUnique("map");
////	    	mainMapContainer.setId(id); 
////	    }
////
////	    mapWidget = new MapWidget(opts);
////	    RootPanel p = RootPanel.get(mainMapContainer.getId()); 
////	    p.add(mapWidget); 
////	    mainMapContainer.appendChild(mapWidget.getElement());
////	    mapWidget.setSize("750px", "500px");
//		    
//	}	
//	private void drawMap() {
//		System.out.println("drawMap123: "+JsUtil.documentContains(getElement()));
//		LatLng center = LatLng.newInstance(40.46387840039735, -3.735442161560054);
//		MapOptions mapOpts = MapOptions.newInstance();
//		mapOpts.setMapTypeId(MapTypeId.ROADMAP);
//		mapOpts.setZoom(10);
//		mapOpts.setScaleControl(true);
//		mapOpts.setCenter(center);
//
//		mapWidget = new MapWidget(mapOpts);			
//		int minMapHeight=400;
//		int h = Math.max(Window.getClientHeight()-100, minMapHeight);		
//		mapWidget.setSize("100%", +h+"px");
//		
////		mapEl = Document.get().createDivElement(); 
////		mainMapContainer.appendChild(mapEl); 
////		mapEl.getStyle().setProperty("width", "500px");
////		mapEl.getStyle().setProperty("height", "500px"); 
////		this.mapImpl = MapImpl.newInstance(mainMapContainer, mapOpts); 
////		mapWidget = MapWidget.newInstance(this.mapImpl);  
////		mapWidget.addAttachHandler(new AttachEvent.Handler() {			
////			@Override
////			public void onAttachOrDetach(AttachEvent event) {
////			}
////		}); 
////		mainMapContainer.appendChild(mapWidget.getElement());
//		
//		
//		
////		mapWidget.addCenterChangeHandler(new CenterChangeMapHandler() {
////			@Override
////			public void onEvent(CenterChangeMapEvent event) {
////				// TODO Auto-generated method stub
////
////			}
////		});
//		// mapWidget.addMapTypeIdChangeHandler(new MapTypeIdChangeMapHandler() {
//		// @Override
//		// public void onEvent(MapTypeIdChangeMapEvent event) {
//		// Window.alert(mapWidget.getMapTypeId().value());
//		// }
//		// });
////		mapWidget.addClickHandler(new ClickMapHandler() {
////			@Override
////			public void onEvent(ClickMapEvent event) {
//////				Window.alert(event.getMouseEvent().getLatLng() + " lang");
////				gmapClicked(event); 
////			}
////		});
//	}
//
////	public Element getEditorPanel() {
////		return editorPanel;
////	}
////	protected void gmapClicked(ClickMapEvent event) {
//////		 Window.alert("zoom: " + mapWidget.getZoom()+ ", LatLng: " + mapWidget.getCenter()); 
////
////		LatLng clickLocation = event.getMouseEvent().getLatLng();
////		
////		MarkerOptions opts = MarkerOptions.newInstance();
//////		opts.setClickable(true);
//////		opts.setDraggable(true);
////		opts.setMap(mapWidget); 
////		opts.setTitle("hello!"); 
////		opts.setPosition(clickLocation); 
////		Marker marker1 = Marker.newInstance(opts);// event.getMouseEvent().getLatLng(), markerOpts);
////		marker1.addClickHandler(new ClickMapHandler() {
////			@Override
////			public void onEvent(ClickMapEvent event) {
////				// Marker m = event.getSource().cast();
////				Window.alert("marker clicked: " + event.getSource());
////			}
////		});
////		
//////		PlaceSearchRequest req = PlaceSearchRequest.newInstance();
//////		req.setLocation(clickLocation);
//////		req.setRaidus(50000);
//////		req.setTypes(PlaceSearchTypes.embassy);
//////		// gmap1=mapWidget.getm
//////		PlacesService placeService = PlacesService.newInstance(mapWidget); // gmap1.newPlacesService(); //PlacesService.newInstance(ma);
//////		placeService.search(req, new PlaceSearchHandler() {
//////			@Override
//////			public void onCallback(JsArray<PlaceResult> results, PlacesServiceStatus status) {
//////				Window.alert("embassies found in the area : " + results.length());
//////				for (int i = 0; i < results.length(); i++) {
//////					PlaceResult result = results.get(i);
//////				}
//////			}
//////		});
////	}
//	
//	/** this view needs to be attached to the DOM by itself. 
//	 */
//	public void attachTo(Element parent) {
////		drawLayout(); 
////		loadMapApi();		
//	}
//}
