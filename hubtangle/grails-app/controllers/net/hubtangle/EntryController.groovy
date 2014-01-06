package net.hubtangle

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import net.hubtangle.entry.Comment
import net.hubtangle.helpers.RequestHelper;

import javax.servlet.http.HttpServletResponse;


import net.hubtangle.entry.Entry

/**
 * Controls entries and stuff related to them
 *
 * @author mkubryn
 */
class EntryController {
	
	def hubService
	def springSecurityService
	
	/**
	 * Handles show entry requests. Please refer to UrlMappings.groovy
	 */
	def showEntry(Long entryId, String type) {

		def entry = hubService.getEntry(entryId)
        if(!entry) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
            return
        }
		
		if(!entryHasType(entry, type)) {
			log.warn("[SECURITY] Tried to access entry: ${entry} using inappropriate type: ${type}")
			response.sendError(HttpServletResponse.SC_FORBIDDEN)
			return
		}

        def commentsCount = Comment.countByEntry(entry)
		
		render (view: "show${type}Entry", model: [entry: entry, commentsCount: commentsCount,
                type: type.toLowerCase()])
	}

    @Secured('ROLE_USER')
    def deleteEntry(Long id) {
        hubService.deleteEntry(id)

        flash.message = 'Entry has been removed!'
        redirect([controller: 'home', action: 'index'])
    }

    @Secured('ROLE_USER')
    def getComments_ajax(Long id, Integer offset) {
        def entryId = id
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

        render(template: '/layouts/comments/comments_group', model: [comments: comments, offset: (offset + batchSize)])
    }

    @Secured('ROLE_USER')
    def createComment(String text, Long entryId) {
        if(!text || !entryId) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST)
            return
        }

        // FIXME - bad design - comments should be saved in service!

        def entry = hubService.getEntry(entryId)
        def comment = new Comment(author: springSecurityService.getCurrentUser(), dateCreated: new Date(),
                content: text, entry: entry)
        comment.save(failOnError: true)

        render (template: '/layouts/comments/single_comment', model: [comment: comment])
    }

	private entryHasType(entry, type) {
		entry.class.getName().matches(".*${type}Entry")
	}
}