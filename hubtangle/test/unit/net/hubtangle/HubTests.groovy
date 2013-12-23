package net.hubtangle



import grails.test.mixin.*
import net.hubtangle.entry.Hub;

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Hub)
class HubTests {

	@Test
	public void obtainingNonPersistentHubUniqueName() throws Exception {
		def hub = new Hub(title: "The test hub")
		
		assertNotNull hub.getSignature()
		assertEquals "non-persistent_The_test_hub", hub.getSignature()
	}
  
	@Test
	public void obtainingPersistentHubUniqueName() throws Exception {
		def hub = new Hub(title: "The test hub")
		hub.id = 1
		
		assertNotNull hub.getSignature()
		assertEquals "13_The_test_hub", hub.getSignature()
	}
  
}
