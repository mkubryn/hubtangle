class UrlMappings {

	static mappings = {

		"/hub/$hubId" (controller: "hub") {
			action = [GET: "hub", POST: "saveHub", PUT: "updateHub", DELETE: "deleteHub"]
		}
		
		"/hub/$hubId/$action" (controller: "hub") {
			
		}
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
