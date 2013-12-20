package net.hubtangle

import net.hubtable.*
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry;
import net.hubtangle.entry.Hub;

/**
 * Controller managing home pages
 *
 * @author mkubryn
 */
class HomeController {

    def relationService
    def springSecurityService

    def searchService

    def index() {
        if(!springSecurityService.isLoggedIn()) {
            render(view: "homepage", model: [hubMap: [:], lastEntries: []])
            return
        }

        def hubIds = relationService.getRelations(Relations.SUBSCRIPTION, springSecurityService.getCurrentUser().id)

        def entrs = Entry.createCriteria().list(max: grailsApplication.config.ht.homepage.last_entries.limit) {
            'in'('hub.id', hubIds)
            order('dateCreated', 'desc')
        }

        def hubMap = entrs.collectEntries { Entry entry ->
            [(entry.hub.getSignature()): entry.hub]
        }

		render(view: "homepage", model: [hubMap: hubMap, lastEntries: entrs])
	}

    def welcome() {
       render(view: 'welcome')
    }
}