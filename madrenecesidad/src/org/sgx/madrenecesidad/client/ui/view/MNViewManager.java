package org.sgx.madrenecesidad.client.ui.view;

import java.util.HashMap;
import java.util.Map;

import org.sgx.madrenecesidad.client.ui.action.HomeUi;

import com.google.gwt.user.client.ui.UIObject;

/**
 * mantains a singleton dictionary for views so they don't have to be created each time. 
 * @author sg
 *
 */
public class MNViewManager {
private static MNViewManager instance;

private MNViewManager() {
	views=new HashMap<String, UIObject>(); 
}

public static MNViewManager getInstance() {
	if (instance == null)
		instance = new MNViewManager();
	return instance;
}
Map<String, UIObject> views; 
//public UIObject getView(String c) {
//	if(!views.containsKey(c))
//		views.put(c, createView(c)); 
//	return views.get(c); 
//}

public UIObject getView(String c) {
	if(!views.containsKey(c))
		views.put(c, createView(c));
	UIObject v = views.get(c); 
//	System.out.println("getView "+v+" - "+c);	
	return v; 
}
public <T extends UIObject> T getView(Class<T> c) {
	return (T) getView(c.getName()); 
//	if(!views.containsKey(c))
//		views.put(c.getName(), createView(c.getName())); 
//	UIObject view = views.get(c); 
//	return (T) view; 
}

private UIObject createView(String c) {
	UIObject view = null; 
	if(c.equals(SearchPlacePanel.class.getName())) {
		view = new SearchPlacePanel(); 
	}
	else if(c.equals(HomeUi.class.getName())) {
		view = new HomeUi(); 
	}
	else if(c.equals(SearchMapViewPanel.class.getName())) {
		view = new SearchMapViewPanel(); 
	}
	
	return view; 
}
}
