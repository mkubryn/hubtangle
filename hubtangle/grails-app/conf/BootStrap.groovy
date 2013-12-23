import net.hubtangle.*
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.auth.*
import net.hubtangle.ds.*
import net.hubtangle.entry.Hub;
import net.hubtangle.entry.ImageEntry;
import net.hubtangle.entry.LinkEntry;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.entry.QuoteEntry;
import net.hubtangle.entry.VideoEntry
import net.hubtangle.entry.Comment

class BootStrap {

	static String version = '{{ 0.0.7 }}'
	
	def springSecurityService
    def relationService
	
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
		def hub1 = new Hub(title: "Trip 2013 - Martin and Aga",
			description: "Hub from our trip.", 
			dateCreated: new Date(), creator: users[0])

        def hub2 = new Hub(title: "Java",
            description: "Explore Java world.",
            dateCreated: new Date(), creator: users[1])

        [hub1, hub2].each {
            def hubId = it.save(failOnError: true)
            def name = it.title

            println "bootstrap: Hub $name saved with id:" + hubId.toString()
        }

        def entries = [
            //hub1
            new ImageEntry(dsFileId: 1, title: "We did it!", author: users[0], hub: hub1, dateCreated: new Date(), description: "This is a description"),
            new LinkEntry(title: "Check this link", url: "http://pieniny.org/trasy", author: users[0], hub: hub1, dateCreated: new Date(), description: "Great content."),
            //new QuoteEntry(title: "Quote of the day", quoteAuthor: "Aga.", text: "Life is life.", author: users[0], hub: hub1, dateCreated: new Date(), description: "Great content."),
            new VideoEntry(url: "http://www.youtube.com/embed/5fa32SkaL6U", title: "River.", author: users[0], hub: hub1, dateCreated: new Date(), description: "Nice but expensive!"),
            new PostEntry(title: "Few words about me.", author: users[0], hub: hub1, dateCreated: new Date(), description: "My title is..", content: "This\nis\n\t\tcontent"),

            //hub2
            new PostEntry(title: "welcome to java hub", author: users[0], hub: hub2, dateCreated: new Date(), description: "Enjoy the Java world.", content: "Yeay"),
        ]

        entries.each {it.save(failOnError: true)}


        def comments = [
           new Comment(content: 'com 1 Dude this is awesome!', entry: entries[0], dateCreated: new Date(), author: users[0]),
           new Comment(content: 'com 2 Too long. Didn\'t read', entry: entries[0], dateCreated: new Date(), author: users[1]),
           new Comment(content: 'com 3', entry: entries[0], dateCreated: new Date(), author: users[1]),
           new Comment(content: 'com 4', entry: entries[0], dateCreated: new Date(), author: users[1]),
           new Comment(content: 'com 5', entry: entries[0], dateCreated: new Date(), author: users[1]),
           new Comment(content: 'com 6', entry: entries[0], dateCreated: new Date(), author: users[1]),

        ]

        comments.each {it.save(failOnError: true)}

        relationService.createRelation(Relations.SUBSCRIPTION, hub1.id, users.first().id)
        relationService.createRelation(Relations.SUBSCRIPTION, hub2.id, users.first().id)
        relationService.createRelation(Relations.WRITABLE, hub1.id, users.first().id)
        relationService.createRelation(Relations.WRITABLE, hub2.id, users.first().id)

        println "bootstrap() done."
    }
	
    def destroy = {
    }
}
