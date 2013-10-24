package net.hubtangle.ds

import javax.servlet.http.HttpServletResponse

/**
 * Controller for downloading files from datasever.
 */
class DownloadController {

    def filestoreService

    def download() {
        def dsFile = DsFile.get(params.id)
        if(dsFile) {
            sendFileInResponse(response, dsFile)
        }
        return
    }

    def listFiles() {
        render view: "listFiles", model: [dsFiles: DsFile.getAll()]
    }

    def deleteFile() {
        def dsFile = DsFile.get(params.id)
        if(!dsFile) {
            render "not found"
            return
        }

        dsFile.delete()
        render "OK"
    }

    /**
     * Sends file to the client via response's output stream
     *
     * @param file real file in filestore
     * @param dsFile dsFile representation of the file
     * @param response http response
     */
    private sendFileInResponse(HttpServletResponse response, DsFile dsFile) {

        def file = filestoreService.getFileFromFilestore(dsFile)

        if (file.exists()) {
            response.setContentType(dsFile.contentType)
            response.setHeader("Content-disposition", "filename=${dsFile.name}")
            response.outputStream << file.bytes
            return
        }
    }
}
