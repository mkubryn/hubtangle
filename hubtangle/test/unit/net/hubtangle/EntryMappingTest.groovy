package net.hubtangle

import grails.test.mixin.*
import net.hubtangle.MiscTagLib;
import net.hubtangle.entry.ImageEntry;
import net.hubtangle.entry.LinkEntry;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.model.ClassMatchingEntryMapper

import static org.junit.Assert.*;

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
class EntryMappingTest {
	
	def mappper = new ClassMatchingEntryMapper()
	
	@Test 
	public void mapPostEntry() {
		def props = [type: 'post', title: 'Title', description: 'Desc', notexistingparam: "nooone"]
		
		def entry = mappper.map(props)
		
		assertNotNull(entry)
		assertEquals("Not matching class name", entry.getClass(), PostEntry.class)
		assertEquals("Not matching title", entry.title, props.title)
	}
	
	@Test  
	public void mapImageEntry() { 
		def props = [type: 'image', title: 'Title', description: 'Desc', url: 'google']
		
		def entry = mappper.map(props)
		
		assertNotNull(entry)
		assertEquals("Not matching class name", entry.getClass(), ImageEntry.class)
		assertEquals("Not matching title", entry.title, props.title)
		assertEquals("Not matching title", entry.url, props.url)
	}
	
	@Test 
	public void mapLinkEntry() {
		def props = [type: 'link', title: 'Title', description: 'Desc', url: 'href']
		
		def entry = mappper.map(props)
		
		assertNotNull(entry)
		assertEquals("Not matching class name", entry.getClass(), LinkEntry.class)
		assertEquals("Not matching title", entry.title, props.title)
		assertEquals("Not matching title", entry.url, props.url)
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void mapUnknownEntry() {
		def props = [type: 'unknown', title: 'Title']
		
		mappper.map(props)
	}

}
