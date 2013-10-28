package net.hubtangle.ds

import grails.converters.JSON

import static javax.servlet.http.HttpServletResponse.*
/**
 * Controller for uploading files to dataserver.
 * It acts as a proxy in the uploading process. It's main responsibility is to dispatch multipart files to the
 * DsServerClientService which sends them to the dataserver.
 *
 * @author mkubryn
 */
class UploadController {

    static final int BUFF_SIZE = 100000;
    static final byte[] buffer = new byte[BUFF_SIZE];

    def dsServerClientService

	def uploadForm() {
		render(view: "fileUploadForm")
	}

    def upload = {
        def contentType	= request.getHeader("Content-Type") as String
        def name 		= URLDecoder.decode(request.getHeader('X-Uploadr-Name'), 'UTF-8') as String
        def fileName    = URLDecoder.decode(request.getHeader('X-File-Name'), 'UTF-8') as String
        def info		= session.getAttribute('uploadr')
        int status      = 0
        def statusText  = ""

        // set response content type to json
        response.contentType    = 'application/json'

        // update lastUsed stamp in session
        if (name && info && info.containsKey(name)) {
            session.uploadr[name].lastUsed = new Date()
            session.uploadr[name].lastAction = "upload"
        }

        def location = dsServerClientService.upload(request.getInputStream(), contentType, fileName)
        status = SC_OK

        if (!location) {
            log.warn("Uploading file failed.")
            status = SC_INTERNAL_SERVER_ERROR
        }

        response.setStatus(status)
        render([written: true, fileName: fileName, status: status, statusText: statusText,
                location: location, dsId: getDsId(location)] as JSON)
    }

    private static getDsId(String downloadPath) {
        downloadPath.split("/").last()
    }
}