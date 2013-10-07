import net.hubtangle.*
import net.hubtangle.auth.*
import net.hubtangle.ds.*
import net.hubtangle.entry.Hub;
import net.hubtangle.entry.ImageEntry;
import net.hubtangle.entry.LinkEntry;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.entry.QuoteEntry;
import net.hubtangle.entry.VideoEntry;

class BootStrap {

	static String version = '{{ 0.0.6 }}'
	
	def springSecurityService
	
    def init = { servletContext ->
		
		/*
		 * Test users and security
		 */
		// create base roles	
		def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)
		
		// create admin
		def adminUser = SecUser.findByUsername('admin') ?: new SecUser(
			username: 'admin',
			password: 'admin',
			enabled: true).save(failOnError: true)

		// add admin authority to admin
		if (!adminUser.authorities.contains(adminRole)) {
			SecUserSecRole.create adminUser, adminRole
		}
		
		// create users
		def users = [
			new SecUser(username: 'user1', password: 'password', enabled: true),
			new SecUser(username: 'user2', password: 'password', enabled: true),
			new SecUser(username: 'user3', password: 'password', enabled: true)
		]
		
		users.each { user ->
			//save user
			user.save(failOnError: true)
			
			// add user authority to user
			if (!user.authorities.contains(userRole)) {
				SecUserSecRole.create user, userRole
			}
		}
		
		/*
		 * Test hubs
		 */
		def hub1 = new Hub(name: "Trip 2013 - Martin and Aga", 
			description: "Hub from our trip.", 
			dateCreated: new Date(), creator: users[0])
		
		hub1.addToEntries(new LinkEntry(title: "Check this link", url: "http://pieniny.org/trasy", author: users[0], hub: hub1, dateCreated: new Date(), description: "Great content."))
		//hub1.addToEntries(new QuoteEntry(title: "Quote of the day", quoteAuthor: "Aga.", text: "Life is life.", author: users[0], hub: hub1, dateCreated: new Date(), description: "Great content."))
		hub1.addToEntries(new ImageEntry(dsFileId: 1, title: "We did it!", author: users[0], hub: hub1, dateCreated: new Date(), description: "This is a description"))
		hub1.addToEntries(new VideoEntry(url: "http://www.youtube.com/embed/5fa32SkaL6U", title: "River.", author: users[0], hub: hub1, dateCreated: new Date(), description: "Nice but expensive!"))
		hub1.addToEntries(new PostEntry(title: "Few words about me.", author: users[0], hub: hub1, dateCreated: new Date(), description: "My name is..", content: "This\nis\n\t\tcontent"))
		
		def hub2 = new Hub(name: "Java",
			description: "Explore Java world.",
			dateCreated: new Date(), creator: users[1])
		hub2.addToEntries(new PostEntry(title: "welcome to java hub", author: users[0], hub: hub1, dateCreated: new Date(), description: "Enjoy the Java world.", content: "Yeay"))
		
		[hub1, hub2].each {
			def hubId = it.save(failOnError: true)
			def name = it.name
			
			println "bootstrap: Hub $name saved with id:" + hubId.toString()
		}
    }
	
    def destroy = {
    }
}
