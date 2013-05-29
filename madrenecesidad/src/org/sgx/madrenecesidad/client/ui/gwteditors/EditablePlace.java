package org.sgx.madrenecesidad.client.ui.gwteditors;

import org.sgx.gwteditors.client.impl1.edbean.EditableBean;
import org.sgx.madrenecesidad.client.model.Place;

import com.google.appengine.api.datastore.GeoPt;
import com.google.gwt.core.shared.GWT;

public abstract class EditablePlace extends Place implements EditableBean {
public static EditablePlace create() {
	EditablePlace p = GWT.create(EditablePlace.class);
	p.setName("a place");
	p.setCenter(new GeoPt(0.1f,0.2f)); 
	p.setPopo("po"); 
	return p;
}

String popo;

public String getPopo() {
	return popo;
}

public void setPopo(String popo) {
	this.popo = popo;
}

}
