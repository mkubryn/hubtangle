class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/r/upload"(controller: "upload", action: "upload")
        "/r/download/$id"(controller: "download", action: "download")
        "/r/delete/$id"(controller: "download", action: "deleteFile")


        "/"(view:"/index")
		"500"(view:'/error')
	}
}
