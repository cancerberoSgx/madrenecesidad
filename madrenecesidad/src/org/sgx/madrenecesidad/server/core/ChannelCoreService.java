package org.sgx.madrenecesidad.server.core;

import java.util.List;
import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.User;
import org.sgx.madrenecesidad.client.model.Tag;

//import com.gdevelop.gwt.syncrpc.SyncProxy;
import com.google.appengine.api.search.Document;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("channelService")
public interface ChannelCoreService {
		
	public void addChannel(User s, Channel ch); 
	public void deleteChannel(User s, Channel ch); 
	public List<Channel> getChannels(User s);
//	List<Channel> getChanels(); 
	Channel getChannelByName(User s, String name);
	List<Channel> searchChannel(User s, String nameFragment);
	void cleanAll(User s);
	List<Tag> getTags(User s, Channel c);
	
}
