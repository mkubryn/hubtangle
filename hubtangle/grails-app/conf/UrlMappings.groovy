class UrlMappings {

	static mappings = {

        "/"(controller: "home", action: "index")

		/*
		 * Mapping for hubs
		 */
		"/hub/$hubId" (controller: "hub", action: "hub")
		"/hub/$hubId/$action" (controller: "hub")
		
		/*
		 * Mapping for entries
		 */
		"/article/$entryId" (controller: "entry", action: "showEntry" ) {
			type = "Post"
		}
		"/image/$entryId" (controller: "entry", action: "showEntry") {
			type = "Image"
		}
		"/video/$entryId" (controller: "entry", action: "showEntry") {
			type = "Video"
		}
		
		/*
		 * Mappings for data server
		 */
		"/download/$dsFileId" (controller: "upload", action: "download")

		/*
		 * Standard grails mappings
		 */
		"/$controller/$action/$id?"{
			constraints {
				// apply constraints here
			}
		}

		/*
		 * Error mappings
		 */
		"500"(view:'/error')
		"403"(controller: "error", action: "error403")
	}
}
