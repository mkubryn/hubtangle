package hubtangle

import net.hubtangle.entry.Entry;
import net.hubtangle.entry.Hub;
import net.hubtangle.entry.LinkEntry;
import net.hubtangle.user.HUser;

import static org.junit.Assert.*
import org.junit.*

class HubIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void saveHub() {
		def user = getTestUser()
		
		def hub = new Hub(title: "Java",
			description: "Explore Java world.", 
			creator: user)
		
		assertNotNull "Hub not saved", hub.save()
		assertEquals "Hub not equal after persisting", hub, Hub.get(hub.id)
    }

	@Test
	public void saveHubWithEntries() throws Exception {
		def user = getTestUser()
		
		def hub1 = new Hub(title: "Java",
			description: "Explore Java world.", 
			creator: user)
		
		assertNotNull(hub1.save())
		
		def entry1 = new LinkEntry(author: user, hub: hub1, url: "http://")
		assertNotNull "Saving hub entry1 failed.", entry1.save()
		hub1.addToEntries(entry1)
		
		def entryFromDb = Entry.get(entry1.id)
		def hubFromDb = Hub.get(hub1.id)
		
		assertNotNull entryFromDb
		assertNotNull hubFromDb
		
		assertEquals "Entry author", user, entryFromDb.author
		assertEquals "Entry parent hub", hubFromDb, entryFromDb.hub
		
		assertTrue "Hub doesn't contain entry", hubFromDb.entries.contains(entryFromDb)
	}	
	
	def getTestUser() {
		def user1 = HUser.findByUsername("Marcin")
		if(user1) {
			return user1
		}
		
		user1 = new HUser(username: "Marcin")
		assertNotNull "Test user not saved", user1.save()
		user1
	}
}
