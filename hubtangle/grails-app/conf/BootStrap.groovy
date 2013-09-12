import net.hubtangle.*
import net.hubtangle.ds.*
import net.hubtangle.entry.Hub;
import net.hubtangle.entry.ImageEntry;
import net.hubtangle.entry.LinkEntry;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.entry.QuoteEntry;
import net.hubtangle.entry.VideoEntry;

class BootStrap {

	static String version = '{{ 0.0.1 }}'
	
    def init = { servletContext ->
		
		if(User.getAll().size() > 0) {
			return;
		}
		
		/*
		 * Test users
		 */
		def user1 = new User(nick: "Martin")
		def user1Id = user1.save(failOnError: true)
		println "bootstrap: user1 saved with id: $user1Id"
		
		/*
		 * Test hubs
		 */
		def hub1 = new Hub(name: "Trip 2013 - Martin and Aga", 
			description: "Hub from our trip.", 
			dateCreated: new Date(), creator: user1)
		
		hub1.addToEntries(new LinkEntry(title: "Check this link", url: "http://pieniny.org/trasy", author: user1, hub: hub1, dateCreated: new Date(), description: "Great content."))
		hub1.addToEntries(new QuoteEntry(title: "Quote of the day", quoteAuthor: "Aga.", text: "Life is life.", author: user1, hub: hub1, dateCreated: new Date(), description: "Great content."))
		hub1.addToEntries(new ImageEntry(url: "../img/test/grails.jpg", title: "We did it!", author: user1, hub: hub1, dateCreated: new Date(), description: "This is a description"))
		hub1.addToEntries(new VideoEntry(url: "http://www.youtube.com/embed/5fa32SkaL6U", title: "River.", author: user1, hub: hub1, dateCreated: new Date(), description: "Nice but expensive!"))
		hub1.addToEntries(new PostEntry(title: "Few words about me.", author: user1, hub: hub1, dateCreated: new Date(), description: "My name is.."))
		
		def hub2 = new Hub(name: "Java",
			description: "Explore Java world.",
			dateCreated: new Date(), creator: user1)
		hub2.addToEntries(new PostEntry(title: "welcome to java hub", author: user1, hub: hub1, dateCreated: new Date(), description: "Enjoy the Java world."))
		
		[hub1, hub2].each {
			def hubId = it.save(failOnError: true)
			def name = it.name
			
			println "bootstrap: Hub $name saved with id:" + hubId.toString()
		}
    }
	
    def destroy = {
    }
}
