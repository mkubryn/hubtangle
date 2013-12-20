package net.hubtangle

import grails.plugins.springsecurity.SpringSecurityService
import net.hubtangle.entry.Comment
import net.hubtangle.helpers.RequestHelper;

import javax.servlet.http.HttpServletResponse;


import net.hubtangle.entry.Entry;
import net.hubtangle.entry.ImageEntry;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.entry.VideoEntry;

/**
 * Controls entries
 * @author mkubryn
 */
class EntryController {
	
	/** DI of {@link HubService}*/
	def hubService
	
	/** DI of {@link SpringSecurityService} */
	def springSecurityService
	
	/**
	 * Handles show entry requests. Please refer to UrlMappings.groovy
	 */
	def showEntry() {
		def entryId = params.entryId as Long
		def type = params.type
		
		if(!hubService.canViewEntry(entryId)) {
			log.warn("[SECURITY] Access denied to entry")
			response.sendError(HttpServletResponse.SC_FORBIDDEN)
			return
		}
		
		def entry = Entry.get(entryId)
		
		if(!entryHasType(entry, type)) {
			log.warn("[SECURITY] Tried to access entry {} using inappropriate type")
			response.sendError(HttpServletResponse.SC_FORBIDDEN)
			return
		}
		
		render (view: "show${type}Entry", model: [entry: entry])
	}

    def getComments_ajax() {
        def entryId = RequestHelper.asLong(params.entryId)
        def offset = RequestHelper.asInteger(params.offset)
        def batchSize = 2

        if(!entryId) {
            entryId = 1 as Long
        }

        if(!offset) {
            offset = 0
        }

        def comments = Comment.createCriteria().list(max: batchSize, offset: offset) {
            eq('entry.id', entryId)
            order('dateCreated', 'desc')
        }

        render(template: '/layouts/comments/single_comment', model: [comments: comments, offset: (offset + batchSize)])
    }
	
	private entryHasType(entry, type) {
		entry.class.getName().matches(".*${type}Entry")
	}
}
