package org.sgx.madrenecesidad.client.service;

import java.util.List;

import org.sgx.madrenecesidad.client.model.Tag;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("tagService")
public interface TagService extends RemoteService {
//	Key addTag(Tag tag);
	Long addTag(Tag tag);
	Tag getTagById(Long id);
	List<Tag> getTags();
	List<Tag> searchTag(String nameFragment);
	void deleteTag(Tag tag);
	void cleanAll(); 

//	List<TagDTO> searchTag(String nameFragment);
}
