package net.hubtangle

import grails.plugins.springsecurity.Secured;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.groovy.grails.plugins.i18n.I18nGrailsPlugin;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestContextUtils;

import net.hubtangle.entry.Hub;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.user.User;
import net.hubtangle.utils.ControllerUtils;

import static net.hubtangle.helpers.ParamsHelper.*;

/**
 * Controller responsible for publishing content in hubtangle
 * @author mkubryn
 *
 */
class PublishController {

	def log = LoggerFactory.getLogger(PublishController.class)
	
	def springSecurityService
	def messageSource
	def hubService
	
    def entry() { 
		def hubId = asLong(params.hub)
		
		// requestor has no right to post on this hub
		if(!hubService.canPostOnHub(hubId)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN)
			return
		}
		
		render (view: "prepareEntry", model: [])
	}
	
	def hub() {
		println "publishing entry on hub " + params['hub']
	}
	
	/**
	 * Handles {@link PostEntry} creation requests, then creates and persists new {@link PostEntry}
	 * @return
	 */
	@Secured(['ROLE_USER'])
	def createPostEntry() {
		def hubId = asLong(params.id)
		
		// prepare post		
		def newEntry = new PostEntry(title: params['title'], 
			description: params['description'])
		
		newEntry.author = springSecurityService.getCurrentUser().id
		newEntry.hub = Hub.get(hubId)
		
		// validate post
		if(!newEntry.validate()) {
			// TODO: human readable validation errors
			render(view: 'validationErrors', model: [prepareEntry: newEntry])
			return
		}
		
		newEntry.save()
		
		// render redirect tag on user's page
		render ControllerUtils.createJsRedirector("/hubtangle/hub/${newEntry.hub.id}#${newEntry.id}")
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
