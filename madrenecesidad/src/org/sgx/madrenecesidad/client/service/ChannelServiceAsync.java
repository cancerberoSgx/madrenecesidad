package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.Tag;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChannelServiceAsync {

	void addChannel(Channel ch, AsyncCallback<Void> callback);

	void deleteChannel(Channel ch, AsyncCallback<Void> callback);

	void getChannels(AsyncCallback<List<Channel>> callback);

	void getChannelByName(String name, AsyncCallback<Channel> callback);

	void searchChannel(String nameFragment, AsyncCallback<List<Channel>> callback);

	void cleanAll(AsyncCallback<Void> callback);

	void getTags(Channel c, AsyncCallback<List<Tag>> callback);

//	void getChanels(AsyncCallback<List<Channel>> callback);

}
