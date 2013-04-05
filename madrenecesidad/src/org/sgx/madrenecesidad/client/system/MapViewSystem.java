package org.sgx.madrenecesidad.client.system;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.MapView;
import org.sgx.madrenecesidad.client.model.Tag;
import org.sgx.madrenecesidad.client.service.ChannelService;
import org.sgx.madrenecesidad.client.service.ChannelServiceAsync;
import org.sgx.madrenecesidad.client.service.MapViewService;
import org.sgx.madrenecesidad.client.service.MapViewServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MapViewSystem extends AbstractSystem {
	
	private final MapViewServiceAsync mapviewService = GWT.create(MapViewService.class);
	protected List<MapView> mapViews;
//	JsArrayString channelNames;
//	private JsArray<JavaScriptObject> channelJSO;
//	protected Object buildNames;

	public MapViewSystem() {
		
//		channelService.getChannels(new AsyncCallback<List<Channel>>() {
//			
//			@Override
//			public void onSuccess(List<Channel> result) {
//				channels = result;
//				notifyAllChanged(); 
////				notifyAllLoaded(); 
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				dispathRPCError("getTagNames", caught);
//			}
//		}); 
	}

//	protected void buildNamesAndJSO() {
//		channelNames = JsArrayString.createArray().cast(); 
//		channelJSO = JsArray.createArray().cast();
//		for(Channel ch : channels) {
//			channelNames.push(ch.getName()); 
//			channelJSO.push(ch.toJSO()); 
//		}
//	}
//
//	public List<Channel> getChannels() {
//		return channels;
//	}
//	
//	public JsArrayString getChannelNames() {
//		return channelNames;
//	}

}
