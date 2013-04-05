package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.Tag;

//import com.gdevelop.gwt.syncrpc.SyncProxy;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("channelService")
public interface ChannelService extends RemoteService {
	public void addChannel(Channel ch); 
	public void deleteChannel(Channel ch); 
	public List<Channel> getChannels();
//	List<Channel> getChanels(); 
	Channel getChannelByName(String name);
	List<Channel> searchChannel(String nameFragment);
	void cleanAll();
	List<Tag> getTags(Channel c);
	
}
