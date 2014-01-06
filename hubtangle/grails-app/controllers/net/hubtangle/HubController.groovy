package net.hubtangle

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.transport.http.client.HttpResponseProperties;
import static net.hubtangle.helpers.RequestHelper.*;

import net.hubtangle.entry.Hub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HubController {
    private static final log = LoggerFactory.getLogger(this)

    def hubService
    def springSecurityService
    def tagService

    def index() { }
	
	/**
	 * Handles show particular {@link Hub} requests
	 */
	def hub(Long hubId, Integer page) {
		def hub = hubService.getHub(hubId)

        if(!page || page <= 0){
            page = 1
        }

        page--

        def entriesPerPage = grailsApplication.config.ht.hub.entries.per.page as int
        def entrySearchCriteria = Entry.createCriteria()

        def offset = page * entriesPerPage

        def entries = entrySearchCriteria.list(max: entriesPerPage, offset: offset) {
            eq('hub.id', hub.id)
            order('dateCreated', 'desc')
        }

        // prepare tags
        def tagMap = entries.collectEntries { entry ->
            [(entry.id): tagService.getTagsForModel(entry)]
        }

        // page numbers for pagination
        def previousPageNumber = page > 0 ? page : null
        def nextPage = (offset+entriesPerPage < countEntriesInHub(hub)) ? (page+2) : null

		[hub: hub, entries: entries, tagMap: tagMap, previousPage: previousPageNumber, nextPage: nextPage]
	}

    @Secured(['ROLE_USER'])
    def subscribe(Long id) {
        hubService.subscribeHub(id)
        flash.message = 'Hub subscribed!'

        chain(action: 'hub', params: [hubId: id])
    }

    @Secured(['ROLE_USER'])
    def unsubscribe(Long id) {
        hubService.unsubscribeHub(id)
        flash.message = 'Hub unsubscribed!'

        chain(action: 'hub', params: [hubId: id])
    }

    @Secured(['ROLE_USER'])
    def manage(Long id) {
        if(!hubService.canModerateHub(id)) {
            log.warn "[SEC] Attempt to manage hub by user which is not it's moderator. " +
                    "User id ${springSecurityService.getCurrentUser().id}, Hub id: ${params.id}"
            response.sendError(HttpServletResponse.SC_FORBIDDEN)
            return
        }
    }

    private Long countEntriesInHub(Hub hub) {
        Entry.countByHub(hub)
    }
}
