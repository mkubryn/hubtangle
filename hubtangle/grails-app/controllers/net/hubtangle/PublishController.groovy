package net.hubtangle

import grails.plugins.springsecurity.Secured
import net.hubtangle.entry.Hub
import net.hubtangle.model.exception.ModelValidationException
import net.hubtangle.utils.ControllerUtils
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException

import java.lang.reflect.UndeclaredThrowableException

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN
import static net.hubtangle.helpers.RequestHelper.asLong;
/**
 * Controller responsible for publishing content in hubtangle
 * @author mkubryn
 *
 */
class PublishController {

    private static log = LoggerFactory.getLogger(this)

    def springSecurityService
	def hubService
	
	/**
	 * Renders entry creation menu
	 */
    @Secured(['ROLE_USER'])
    def entry() {
        log.debug "Request to save hub. params: $params"

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
	 */
    @Secured(['ROLE_USER'])
	def hub() {
		render(view: 'createHub')
	}

    /**
     * Handles hub creation requests.
     */
    def saveHub() {
        def hub = new Hub(description: params.description, title: params.title, dateCreated: new Date())

        hub = hubService.saveHub(hub)

        if (hub.hasErrors())  {
            render(view: "validationErrors", model: [bean: hub])
            return
        }

        render ControllerUtils.createJsRedirector("${request.contextPath}/hub/${hub.id}")
    }
	
	/**
	 * Handles entry creation requests.
	 */
	@Secured(['ROLE_USER'])
	def saveEntry() {
        log.debug 'Save entry request. Params: $params'
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
		render ControllerUtils.createJsRedirector("${request.contextPath}/hub/${newEntry.hub.id}#${newEntry.id}")
	}

	/**
	 * Renders description for post entries	
	 */
	def axEntryDescription() {
		def type = params.type
		render (view: "createEntry/examples/${type}Example")
	}
}
