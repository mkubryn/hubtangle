class UrlMappings {

	static mappings = {

		"/srv/$fid" (controller: "service") {
			action = [GET: "download", POST: "upload", PUT: "update", DELETE: "delete"]
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
