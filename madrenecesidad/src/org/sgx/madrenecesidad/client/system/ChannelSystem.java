package org.sgx.madrenecesidad.client.system;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.Tag;
import org.sgx.madrenecesidad.client.service.ChannelService;
import org.sgx.madrenecesidad.client.service.ChannelServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ChannelSystem extends AbstractSystem {
	
	private final ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	protected List<Channel> channels;
	JsArrayString channelNames;
	private JsArray<JavaScriptObject> channelJSO;
	protected Object buildNames;

	public ChannelSystem() {
		
//appleService.addApple(a1, new AsyncCallback<Void>() {
//       
//    @Override
//    public void onSuccess(Void result) {
//        
//    	channelService.getAppleByName("", new AsyncCallback<Apple>() {
//			
//			@Override
//			public void onSuccess(Apple result) {				
//				assertTrue(result.getDescription()==null); 
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//			}
//		}); 
//    }
//   
//    @Override
//    public void onFailure(Throwable caught) {
//    }
//}); 
		
		channelService.getChannels(new AsyncCallback<List<Channel>>() {
			
			@Override
			public void onSuccess(List<Channel> result) {
				channels = result;
				notifyAllChanged(); 
//				notifyAllLoaded(); 
			}
			
			@Override
			public void onFailure(Throwable caught) {
				dispathRPCError("getTagNames", caught);
			}
		}); 
	}

	protected void buildNamesAndJSO() {
		channelNames = JsArrayString.createArray().cast(); 
		channelJSO = JsArray.createArray().cast();
		for(Channel ch : channels) {
			channelNames.push(ch.getName()); 
			channelJSO.push(ch.toJSO()); 
		}
	}

	public List<Channel> getChannels() {
		return channels;
	}
	
	public JsArrayString getChannelNames() {
		return channelNames;
	}

}
