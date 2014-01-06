package net.hubtangle

import grails.plugins.springsecurity.Secured
import net.hubtangle.api.command.CreateEntryCommand
import net.hubtangle.entry.Entry
import net.hubtangle.entry.Hub
import net.hubtangle.entry.ImageEntry
import net.hubtangle.entry.LinkEntry
import net.hubtangle.entry.PostEntry
import net.hubtangle.entry.VideoEntry
import net.hubtangle.utils.CommandUtils
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
    def entry(Long hub, String type, Long entry) {
        log.debug "Request to save hub. params: $params"

		if(!hub) {
			// send 404
			response.sendError(SC_BAD_REQUEST)
			return
		}
		
		if(!type) {
			// render menu of possible entry types to create
			render (view: "entryMenu", model: [hubId: hub])
			return
		}

        // editing
        def command = new CreateEntryCommand()
        if(entry) {
            def entryToEdit = hubService.getEntry(entry)
            if(entryToEdit) {
                CommandUtils.copyProperties(entryToEdit, command)
            }
        }

		// render specific entry creation form
		render (view: "createEntry/${type}Entry", model: [hubId: hub, command: command])
	}
	
	/**
	 * Renders hub creation menu
	 */
    @Secured(['ROLE_USER'])
	def hub() {
		render(view: 'createHub')
	}

    @Secured(['ROLE_USER'])
    def saveHub() {
        def hub = new Hub(description: params.description, title: params.title, dateCreated: new Date())

        hub = hubService.saveHub(hub)

        if (hub.hasErrors())  {
            render(view: "validationErrors", model: [bean: hub])
            return
        }

        render ControllerUtils.createJsRedirector("${request.contextPath}/hub/${hub.id}")
    }

    @Secured(['ROLE_USER'])
    def savePostEntry(CreateEntryCommand command, Long id) {
        processSaveEntry(command, id, new PostEntry())
    }

    @Secured(['ROLE_USER'])
    def saveImageEntry(CreateEntryCommand command, Long id) {
        processSaveEntry(command, id, new ImageEntry())
    }

    @Secured(['ROLE_USER'])
    def saveLinkEntry(CreateEntryCommand command, Long id) {
        processSaveEntry(command, id, new LinkEntry())
    }

    @Secured(['ROLE_USER'])
    def saveVideoEntry(CreateEntryCommand command, Long id) {
        processSaveEntry(command, id, new VideoEntry())
    }

	/**
	 * Renders description for post entries	
	 */
	def axEntryDescription() {
		def type = params.type
		render (view: "createEntry/examples/${type}Example")
	}

    private processSaveEntry(CreateEntryCommand command, Long hubId, Entry entryOfType) {
        def entryToUpdate = hubService.getEntry(command.entityId)
        if(!entryToUpdate) {
            entryToUpdate = entryOfType
        }

        CommandUtils.copyProperties(command, entryToUpdate)

        if(!entryToUpdate.hub) {
            entryToUpdate.hub = hubService.getHub(hubId)
        }

        def newEntry = hubService.saveEntry(entryToUpdate)

        if(newEntry.hasErrors()) {
            render(view: 'validationErrors', model: [bean: newEntry])
            return
        }

        redirectToEntry(newEntry)
    }

    private redirectToEntry(Entry entry) {
        render ControllerUtils.createJsRedirector("${request.contextPath}/hub/${entry.hub.id}#${entry.id}")
    }

    private getCreateEntryCommandFromView() {

    }
}
