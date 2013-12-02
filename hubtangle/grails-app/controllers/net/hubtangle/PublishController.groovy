package net.hubtangle

import java.lang.reflect.UndeclaredThrowableException;

import grails.plugins.springsecurity.Secured;

import javax.servlet.http.HttpServlet;

import org.codehaus.groovy.grails.plugins.i18n.I18nGrailsPlugin;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.support.RequestContextUtils;

import net.hubtangle.entry.Hub;
import net.hubtangle.entry.PostEntry;
import net.hubtangle.helpers.RequestHelper;
import net.hubtangle.model.exception.ModelValidationException;
import net.hubtangle.user.HUser;
import net.hubtangle.utils.ControllerUtils;

import static net.hubtangle.helpers.RequestHelper.*;
import static javax.servlet.http.HttpServletResponse.*;
/**
 * Controller responsible for publishing content in hubtangle
 * @author mkubryn
 *
 */
class PublishController {

	def springSecurityService
	def hubService
	
	/**
	 * Renders entry creation menu or delegates to specific entry creation view, 
	 * for example <code>postEntryCreate</code> view
	 */
    @Secured(['ROLE_USER'])
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
    @Secured(['ROLE_USER'])
	def hub() {
		render(view: 'createHub')
	}

    def saveHub() {

        def hub = new Hub(description: params.description, name: params.name, dateCreated: new Date())

        hub = hubService.saveHub(hub)

        if (hub.hasErrors())  {
            render(view: "validationErrors", model: [bean: hub])
            return
        }

        render ControllerUtils.createJsRedirector("/hubtangle/hub/${hub.id}")
    }
	
	/**
	 * Handles entry creation requests. Entry creation process will be delegated to 
	 * @return
	 */
	@Secured(['ROLE_USER'])
	def saveEntry() {

        println "Save entry: " + params

		def hubId = asLong(params.id)
		
		// Remove read only properties
		params.remove("id")
		params.remove("hubId")
		
		// Save with service
		def newEntry = null;
		try {
			newEntry = hubService.createAndSaveEntry(params, hubId)
		} catch (IllegalArgumentException e) {
			log.warn("Entry publish request was invalid.", e)
			response.sendError(SC_BAD_REQUEST)
			return
		} catch (AccessDeniedException e) {
			log.warn("[SECURITY] Access denied while publishing entry.", e)
			response.sendError(SC_FORBIDDEN)
			return
		} catch (UndeclaredThrowableException e) {
		
			if(e.getCause() instanceof ModelValidationException) {
				def mve = e.getCause()
				log.info("Validation exception while publishing entry.", e)
				/*
				 * Validation went wrong - render validation errors
				 */
				render(view: 'validationErrors', model: [bean: mve.modelBean])
			}
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
	def axEntryDescription() {
		def type = params.type
		render (view: "createEntry/examples/${type}Example")
	}
}
