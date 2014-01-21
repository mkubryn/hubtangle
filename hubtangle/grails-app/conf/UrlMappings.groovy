import net.hubtangle.api.ModelNotFoundException
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Controller

class UrlMappings {

	static mappings = {

        "/"(controller: "home", action: "index")

        "/welcome"(controller: "home", action: "welcome")

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
        "/link/$entryId" (controller: "entry", action: "showEntry") {
            type = "Link"
        }

        /**
         * Mappings for users
         */

        "/user/hubs/$action" (controller: "user")

		/*
		 * Mappings for data server proxying
		 */
		"/download/$dsFileId" (controller: "download", action: "download")

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
		//"500"(controller: "error", action: "error500") USE IT IN PRODUCTION!
        "500"(view:'/error')

		"403"(controller: "error", action: "error403")

        "500"(controller: "error", action: "error404", exception: ModelNotFoundException)
        "500"(controller: "error", action: "error403", exception: AccessDeniedException)

    }
}
