//package org.sgx.madrenecesidad.client.system;
//
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.List;
//
//import org.sgx.madrenecesidad.client.model.Tag;
//import org.sgx.madrenecesidad.client.service.TagService;
//import org.sgx.madrenecesidad.client.service.TagServiceAsync;
////import org.sgx.madrenecesidad.client.util.JsUtil;
//import org.sgx.yuigwt.yui.util.JsObject;
//import org.sgx.yuigwt.yui.util.JsUtil;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.core.client.JavaScriptException;
//import com.google.gwt.core.client.JavaScriptObject;
//import com.google.gwt.core.client.JsArray;
//import com.google.gwt.core.client.JsArrayString;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//
///**
// *
// * @author sg
// *
// */
//public class TagSystem extends AbstractSystem {
//	private static TagSystem instance;
////	private AsyncCallback<String> emptyCallback;
//
//	private TagSystem() {
//		super(); 
////		emptyCallback = new AsyncCallback<String>() {			
////			@Override
////			public void onSuccess(String result) {
////			}			
////			@Override
////			public void onFailure(Throwable caught) {
////			}
////		}; 
//		tagService.getTags(new AsyncCallback<List<Tag>>() {
//			
//			@Override
//			public void onSuccess(List<Tag> result) {
//				tags = result; 
//				tagNames = JsArrayString.createArray().cast();
//				tagsJSO = JsArray.createArray().cast(); 
//				for(Tag tag : result) {
//					tagNames.push(tag.getName()); 
//					tagsJSO.push(tag.toJSO()); 
//				}				
////				for(SystemListener l : listeners)
////					l.loaded(); 
//				notifyAllChanged(); 
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				dispathRPCError("getTagNames", caught); 
//			}
//		}); 
//	}
//
//	public static TagSystem getInstance() {
//		if (null == instance) {
//			instance = new TagSystem();
//		}
//		return instance;
//	}
//	
//	private final TagServiceAsync tagService = GWT.create(TagService.class);
//	protected List<Tag> tags;
//	JsArrayString tagNames;
//	private JsArray<JavaScriptObject> tagsJSO; 
//	
////	public TagSystem() {
////		tagNames=null; 		
////	}
//
//	public TagServiceAsync getTagService() {
//		return tagService;
//	}
//
//	public List<Tag> getTags() {
//		return tags;
//	}
//
//	public JsArrayString getTagNames() {
//		return tagNames;
//	}
//	
//	public JsArray<JavaScriptObject> getTagsJSO() {
//		return tagsJSO; 
//	}
//
//
//	public void addTag(final Tag tag, final AsyncCallback<Long> rpcCallback) {
//		tagService.addTag(tag, new AsyncCallback<Long>() {			
//			@Override
//			public void onSuccess(Long result) {
//				if(rpcCallback!=null)
//					rpcCallback.onSuccess(result); 
//				
//				//update local data and notify the change
//				tags.add(tag); 
//				tagNames.push(tag.getName()); 
//				tagsJSO.push(tag.toJSO()); 
//				
//				notifyAllChanged(); 
//				
////				for(SystemListener l : listeners)
////					l.changed(); 
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				if(rpcCallback!=null)
//					rpcCallback.onFailure(caught); 
//			}
//		}); 
//	}
//	public void deleteTag(final Tag t, final AsyncCallback<Void> rpcCallback) {
//		tagService.deleteTag(t, new AsyncCallback<Void>() {			
//			@Override
//			public void onSuccess(Void result) {
//				if(rpcCallback!=null)
//					rpcCallback.onSuccess(result); 
//				tags.remove(t); 
//				JsUtil.arrayRemoveItem(tagNames, t.getName()); 
//				JsUtil.arrayRemoveItem(tagsJSO, t.toJSO(), new Comparator<JavaScriptObject>() {
//					@Override
//					public int compare(JavaScriptObject o1, JavaScriptObject o2) {
//						JsObject t1 = o1.cast(); 
//						JsObject t2 = o2.cast();
//						return t1.objGetString("name").compareTo(t2.objGetString("name")); 
//					}
//				});
//				notifyAllChanged();
////				for(SystemListener l : listeners)
////					l.changed(); 
//			}			
//			@Override
//			public void onFailure(Throwable caught) {
//				if(rpcCallback!=null)
//					rpcCallback.onFailure(caught);
//			}
//		}); 
//	}
//
//
//
//
//	
//	
//	// admin 
//	public void adminCleanAllTags() {
//		List<Tag> alltags = getTags();
//		for(Tag t : alltags) {
//			deleteTag(t, null); 
////			tagService.deleteTag(t, null); 
//		}
//		
//	}
//
//	public Tag getTag(String tagName) {
//		for(Tag t : tags) {
//			if(t.getName().equals(tagName))
//				return t; 
//		}
//		return null; 
//	}
//
//	public void updateTag(final Tag t, final AsyncCallback<Long> rpcCallback) {
//		tagService.addTag(t, new AsyncCallback<Long>() {
//			
//			@Override
//			public void onSuccess(Long result) {
//				Tag tt = getTag(t.getName());
////				Window.alert(tt+" - "+t.getName()); 
//				if(tt==null)
//					return; 
//				tt.copyFrom(t);
//				for (int i = 0; i < tagsJSO.length(); i++) {
//					JavaScriptObject jso = tagsJSO.get(i); 
//					if(JsUtil.get(jso, "name").equals(t.getName())) {
//						tagsJSO.set(i, t.toJSO()); 
////						Window.alert(t.getDescription());
//					}
//				}
//				notifyAllChanged();
////				for(SystemListener l : listeners)
////					l.changed(); 
//				if(rpcCallback!=null)
//					rpcCallback.onSuccess(result);
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				if(rpcCallback!=null)
//					rpcCallback.onFailure(caught);
//			}
//		}); 
//	}; 
//	
//	
//	
////	JsArrayString tagNames=null; 
////	public JsArrayString getTagNames(SystemListener<JsArrayString> listener) {
////		if(tagNames==null) {
////			tagService.getTagNames(new AsyncCallback<List<TagObj>>() {
////				
////				@Override
////				public void onSuccess(List<TagObj> result) {
//////					tagNames = JsUtil.stringSplit(result, ","); 
////				}
////				
////				@Override
////				public void onFailure(Throwable caught) {
////					dispathRPCError("getTagNames", caught); 
////				}			
////			}); 
////			return JsArrayString.createArray().cast(); 
////		}
////		else {
////			return tagNames; 
////		}
////	}
//
//}
