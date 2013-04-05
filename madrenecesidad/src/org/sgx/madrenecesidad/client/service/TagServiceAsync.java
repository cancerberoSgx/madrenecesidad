package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Tag;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TagServiceAsync {

//	void addTag(Tag tag, AsyncCallback<Key> callback);

//	void searchTag(String nameFragment, AsyncCallback<List<TagDTO>> callback);

	void addTag(Tag tag, AsyncCallback<Long> callback);

	void getTagById(Long id, AsyncCallback<Tag> callback);

	void getTags(AsyncCallback<List<Tag>> callback);

	void searchTag(String nameFragment, AsyncCallback<List<Tag>> callback);

	void deleteTag(Tag tag, AsyncCallback<Void> callback);

	void cleanAll(AsyncCallback<Void> callback);
}
