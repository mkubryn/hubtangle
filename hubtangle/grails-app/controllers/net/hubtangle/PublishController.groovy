package net.hubtangle

/**
 * Controller responsible for publishing content in hubtangle
 * @author mkubryn
 *
 */
class PublishController {

    def entry() { 
		println "publishing entry on hub " + params['hub']
	}
	
	def hub() { }
	
	def renderEntryCreateForm() {
		def entryType = params['type']
		if(!entryType) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		
		/*
		 * TODO - you have to check here weather the requested
		 * view exists or it's method throw an error
		 */
		
		render (template: "createEntry/$entryType")
	}
}
