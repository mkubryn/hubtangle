package net.hubtangle

import grails.plugins.springsecurity.Secured
import net.hubtangle.api.command.CreateImageEntryCommand
import net.hubtangle.api.command.CreateLinkEntryCommand
import net.hubtangle.api.command.CreatePostEntryCommand
import net.hubtangle.api.command.CreateVideoEntryCommand
import net.hubtangle.entry.Entry
import net.hubtangle.entry.Hub
import net.hubtangle.entry.ImageEntry
import net.hubtangle.entry.LinkEntry
import net.hubtangle.entry.PostEntry
import net.hubtangle.entry.VideoEntry
import net.hubtangle.utils.ControllerUtils
import org.slf4j.LoggerFactory

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST
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

    def saveHub() {
        def hub = new Hub(description: params.description, title: params.title, dateCreated: new Date())

        hub = hubService.saveHub(hub)

        if (hub.hasErrors())  {
            render(view: "validationErrors", model: [bean: hub])
            return
        }

        render ControllerUtils.createJsRedirector("${request.contextPath}/hub/${hub.id}")
    }

    def savePostEntry(CreatePostEntryCommand command) {
        def postEntry = new PostEntry(id: command.entryId, description: command.description,
                dsFileId: command.dsFileId, title: command.title, content: command.content)

        processSaveEntry(postEntry, params.id as Long)
    }

    def saveImageEntry(CreateImageEntryCommand command) {
        def imageEntry = new ImageEntry(id: command.entryId,description: command.description,
                dsFileId: command.dsFileId, title: command.title)

        processSaveEntry(imageEntry, params.id as Long)
    }

    def saveLinkEntry(CreateLinkEntryCommand command) {
        def linkEntry = new LinkEntry(id: command.entryId,description: command.description,
                title: command.title, url: command.url)

        processSaveEntry(linkEntry, params.id as Long)
    }

    def saveVideoEntry(CreateVideoEntryCommand command) {
        def videoEntry = new VideoEntry(id: command.entryId,description: command.description,
                title: command.title, url: command.url)

        processSaveEntry(videoEntry, params.id as Long)
    }

	/**
	 * Renders description for post entries	
	 */
	def axEntryDescription() {
		def type = params.type
		render (view: "createEntry/examples/${type}Example")
	}

    private processSaveEntry(Entry entry, Long hubId) {
        entry.hub = hubService.getHub(hubId)
        def newEntry = hubService.saveEntry(entry)

        if(newEntry.hasErrors()) {
            render(view: 'validationErrors', model: [bean: newEntry])
            return
        }

        redirectToEntry(newEntry)
    }

    private redirectToEntry(Entry entry) {
        render ControllerUtils.createJsRedirector("${request.contextPath}/hub/${entry.hub.id}#${entry.id}")
    }
}
