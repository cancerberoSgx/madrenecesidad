package org.sgx.madrenecesidad.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.CookieManager;

import org.junit.Test;
import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.model.Tag;
import org.sgx.madrenecesidad.client.service.ChannelService;
import org.sgx.madrenecesidad.client.service.PlaceService;
import org.sgx.madrenecesidad.client.service.TagService;

import com.google.appengine.api.datastore.GeoPt;

/**
 * JUnit test of project's madrenecesidad RPC services. Note: this must be executed 
 * with appengine datastore HRD setting "unapplied job percentage" == 0.001 in Launch configuration.
 * 
 * @see http://cancerberonia.blogspot.com/2012/10/testing-gwt-service-classes.html
 * 
 * @author sg
 * 
 */
public class PlaceServiceTest1 extends AbstractTest {

	@Test
	public void test() {

		PlaceService placeService = newPlaceService();

		// delete all channels
		placeService.cleanAll();
		assertEquals(0, placeService.getAllPlaces().size());

		// add new channel
		Place p1 = new Place();
		p1.setName("place1");
		p1.setDescription("descr1");
		p1.setCenter(new GeoPt(0.001f, 1.11f));
		Long id1 = placeService.addPlace(p1);
		assertTrue(id1 > 0);
		
		p1=placeService.getPlaceById(id1);
		assertTrue(p1.getId()>0); 
		assertTrue(p1.getName().equals("place1"));
		assertTrue(p1.getDescription().equals("descr1"));
		assertTrue(p1.getCenter().getLatitude()>0 && p1.getCenter().getLatitude()<0.1);
		
		assertTrue(placeService.searchPlace("crazypolygon").size()==0);
		assertTrue(placeService.searchPlace("place1").size()==1);
		assertTrue(placeService.searchPlace("descr1").size()==1);
		
		placeService.deletePlace(p1);
		assertTrue(placeService.searchPlace("place1").size()==0);
		assertTrue(placeService.searchPlace("descr1").size()==0);
	}

}
