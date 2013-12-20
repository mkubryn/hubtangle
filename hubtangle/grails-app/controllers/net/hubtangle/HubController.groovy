package net.hubtangle

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import net.hubtangle.entry.Entry;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.transport.http.client.HttpResponseProperties;
import static net.hubtangle.helpers.RequestHelper.*;

import net.hubtangle.entry.Hub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HubController {

    def hubService

    def index() { }
	
	/**
	 * Handles show particular {@link Hub} requests
	 */
	@Secured(['ROLE_USER'])
	def hub() {
		def hub = Hub.get(asLong(params.hubId))
        if(!hub) {
            log.info("[SEC] Attempt to obtain not existing hub with id=${params.hubId}")

            response.sendError(HttpServletResponse.SC_NOT_FOUND)
            return
        }

        def page = asInteger(params.page)
        if(!page || page <= 0){
            page = 1
        }

        page--

        def entriesPerPage = grailsApplication.config.ht.hub.enties.per.page as int
        def entrySearchCriteria = Entry.createCriteria()

        def offset = page * entriesPerPage

        def entries = entrySearchCriteria.list(max: entriesPerPage, offset: offset) {
            eq('hub.id', hub.id)
            order('dateCreated', 'desc')
        }

        // page numbers for pagination
        def previousPageNumber = page > 0 ? page : null
        def nextPage = (offset+entriesPerPage < countEntriesInHub(hub)) ? (page+2) : null

		[hub: hub, entries: entries, previousPage: previousPageNumber, nextPage: nextPage]
	}

    @Secured(['ROLE_USER'])
    def subscribe() {
        hubService.subscribeHub(params.id as Long)
        flash.message = 'Hub subscribed!'
        chain(action: 'hub', params: [hubId: params.id])
    }

    private countEntriesInHub(hub) {
        Entry.countByHub(hub)
    }
}
