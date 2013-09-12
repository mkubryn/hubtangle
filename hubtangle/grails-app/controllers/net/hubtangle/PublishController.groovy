package net.hubtangle

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.groovy.grails.plugins.i18n.I18nGrailsPlugin;
import org.springframework.web.servlet.support.RequestContextUtils;

import net.hubtangle.entry.Hub;
import net.hubtangle.entry.PostEntry;

import static net.hubtangle.helpers.ParamsHelper.*;

/**
 * Controller responsible for publishing content in hubtangle
 * @author mkubryn
 *
 */
class PublishController {

	def messageSource
	def hubService
	
    def entry() { 
		println "publishing entry on hub " + params['hub']
	}
	
	def hub() { }
	
	/**
	 * Handles {@link PostEntry} creation requests
	 * @return
	 */
	def createPostEntry() {
		def hubId = asLong(params.id)
		
		// requestor has no right to post on this hub
		if(!hubService.canPostOnHub(hubId)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN)
			return
		}

		// preprare post		
		def newEntry = new PostEntry(title: params['title'], 
			description: params['description'])
		
		// FIXME : get currently authenticated user as post author!
		newEntry.author = User.get(1l)
		newEntry.hub = Hub.get(hubId)
		
		// validate post
		if(!newEntry.validate()) {
			// TODO: human readable validation errors
			render(view: 'validationErrors', model: [entry: newEntry])
			return
		}
		
		newEntry.save()
		render "OK!"
	}
	
	/**
	 * Renders entry creator for specific entry type
	 * @return
	 */
	def renderEntryCreateForm() {
		def hubId = params.id
		def entryType = params.type
		
		// requestor has no right to post on this hub
		if(!hubService.canPostOnHub(hubId)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN)
			return
		}
		
		if(!entryType || !hubId) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		
		/*
		 * TODO - you have to check here weather the requested
		 * view exists or it's method throw an error
		 */
		
		render (template: "createEntry/$entryType", model: [hubId: hubId])
	}
}
