package org.sgx.madrenecesidad.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.CookieManager;

import org.junit.Test;
import org.sgx.madrenecesidad.client.model.Channel;
import org.sgx.madrenecesidad.client.model.Tag;
import org.sgx.madrenecesidad.client.service.ChannelService;
import org.sgx.madrenecesidad.client.service.TagService;


/**
 * JUnit test of project's madrenecesidad RPC services. Note: this must be
 * executed with appengine datastore HRD setting "unapplied job percentage" ==
 * 0.001 in Launch configuration. 
 * 
 * @see
 * http://cancerberonia.blogspot.com/2012/10/testing-gwt-service-classes.html
 * 
 * @author sg
 * 
 */
public class ChannelServiceTest1 extends AbstractTest {


	@Test
	public void test() {
		
		
				
		ChannelService channelService = newChannelService(); 
		TagService tagService = newTagService(); 
		
		// delete all channels
		channelService.cleanAll(); 
		assertEquals(0, channelService.getChannels().size());

		// add new channel
		Channel ch1 = new Channel();
		ch1.setName("channel1");
		ch1.setDescription("channel1 description");
		channelService.addChannel(ch1);
		ch1 = channelService.getChannelByName("channel1");
		assertTrue(ch1!=null);
		
		Channel ch2 = channelService.getChannelByName("channel1");
		assertTrue(ch2.getDescription().equals("channel1 description"));
		assertEquals(1, channelService.getChannels().size());
		assertEquals(1, channelService.searchChannel("channel1").size());
		assertEquals(0, channelService.searchChannel("channel2").size());
		assertEquals(1, channelService.searchChannel("description").size());

		// add a new tag and associate it with the channel
		tagService.cleanAll(); 
		assertEquals(0, channelService.getTags(ch1).size());
		assertEquals(0, tagService.searchTag("tag1").size());
		assertEquals(0, tagService.getTags().size());

		Tag t1 = new Tag();
		t1.setName("tag1");
		t1.setDescription("tag1 description");
		t1.setChannel(ch1);
		Long t1Id = tagService.addTag(t1);
		t1 = tagService.getTagById(t1Id); //reasing - important !
		assertTrue(t1Id > 0);

		assertEquals(1, tagService.searchTag("tag1").size());
		assertEquals(1, tagService.getTags().size());
		assertTrue(tagService.getTagById(t1Id) != null);
		assertEquals("tag1 description", tagService.getTagById(t1Id)
				.getDescription());

		assertEquals(1, channelService.getTags(ch1).size());
		assertEquals("tag1", channelService.getTags(ch1).get(0).getName());
		assertEquals(1, tagService.searchTag("tag1").size());
		assertEquals("tag1", tagService.searchTag("tag1").get(0).getName());

		assertEquals(1, channelService.getTags(ch1).size());

		// delete the tag
		tagService.deleteTag(t1);
		assertEquals(0, tagService.searchTag("tag1").size());
		assertEquals(0, tagService.getTags().size());
		assertTrue(tagService.getTagById(t1Id) == null);
		assertEquals(0, channelService.getTags(ch1).size());

		// delete the channel
		channelService.deleteChannel(ch1);
		assertEquals(0, channelService.getChannels().size());
		assertEquals(0, channelService.searchChannel("channel1").size());

	}

}
