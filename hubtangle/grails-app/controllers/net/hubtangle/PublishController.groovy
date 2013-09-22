package net.hubtangle

import grails.plugins.springsecurity.Secured;

import javax.servlet.http.HttpServlet;

import org.codehaus.groovy.grails.plugins.i18n.I18nGrailsPlugin;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.support.RequestContextUtils;

import net.hubtangle.entry.Hub;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.helpers.RequestHelper;
import net.hubtangle.model.exception.ModelValidationException;
import net.hubtangle.user.User;
import net.hubtangle.utils.ControllerUtils;

import static net.hubtangle.helpers.RequestHelper.*;
import static javax.servlet.http.HttpServletResponse.*;
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
	
	/**
	 * Renders entry creation menu or delegates to specific entry creation view, 
	 * for example <code>postEntryCreate</code> view
	 */
    def entry() { 
		// ger required params
		def hubId = asLong(params.hub)
		def type = params.type
		
		if(!hubId) {
			// send 404
			response.sendError(SC_BAD_REQUEST)
			return
		}
		
		if(!type) {
			// render menu of possible entry types to create
			render (view: "entryMenu", model: [hubId: hubId])
			return
		}
		
		// render specific entry creation form
		render (view: "createEntry/${type}Entry", model: [hubId: hubId])
	}
	
	/**
	 * Renders hub creation menu
	 * @return
	 */
	def hub() {
		println "publishing entry on hub " + params['hub']
	}
	
	/**
	 * Handles entry creation requests. Entry creation process will be delegated to 
	 * @return
	 */
	@Secured(['ROLE_USER'])
	def saveEntry() {
		def hubId = asLong(params.id)
		
		println "DESC: " + params.description
		
		// Remove read only properties
		params.remove("id")
		params.remove("hubId")
		
		// Save with service
		def newEntry = null;
		try {
			newEntry = hubService.createAndSaveEntry(params, hubId)
		} catch (IllegalArgumentException e) {
			render "ERROR " + e.getMessage()
			return
		} catch (AccessDeniedException e) {
			render "ACCESS IS DENIED " + e.getMessage()
			return
		} catch (ModelValidationException e) {
		
		e.printStackTrace() //TODO rm me
		
			/*
			 * Validation went wrong - render validation errors
			 */
			render(view: 'validationErrors', model: [entry: e.modelBean])
			return
		}
		
		/*
		 * Everything was OK, redirect to entry
		 */
		render ControllerUtils.createJsRedirector("/hubtangle/hub/${newEntry.hub.id}#${newEntry.id}")
	}

	/**
	 * Renders description for post entries	
	 */
	def axPostEntryDescription() {
		render "Just a post.." //TODO prepare business description for post entries
	}
	
	/**
	 * Renders description for photo entries
	 */
	def axPhotoEntryDescription() {
		render "Photo and description.." //TODO prepare business description for photo
	}
}
