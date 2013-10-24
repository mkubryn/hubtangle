package net.hubtangle.ds

/**
 * Controller for uploading files to dataserver.
 * It acts as a proxy in the uploading process. It's main responsibility is to dispatch multipart files to the
 * DsServerClientService which sends them to the dataserver.
 *
 * @author mkubryn
 */
class UploadController {

    def dsServerClientService

	def uploadForm() {
		render(view: "fileUploadForm")
	}

	def upload() {
		def multipartFile = request.getFile('file')
		
		if (!multipartFile || multipartFile.empty) {
			flash.message = 'file cannot be empty'
			render(view: 'uploadForm')
			return
		}

        def location = dsServerClientService.upload(multipartFile)

		render "sent <a href='" + location + "'> download </a>"
	}
}