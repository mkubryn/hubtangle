package net.hubtangle.ds

import liquibase.util.MD5Util

import static javax.servlet.http.HttpServletResponse.*;
import javax.servlet.http.HttpServletResponse

/**
 * Handles upload requests in Dataserver
 */
class UploadController {

    def filestoreService
    def grailsApplication

    def upload() {
        println "req: " + params
        def multipartFile = request.getFile('file')

        if (!validateMultipartFile(multipartFile)) {
            response.sendError(SC_BAD_REQUEST)
            return
        }

        def dsFile = filestoreService.saveFileInStore(multipartFile)

        if(dsFile.hasErrors()) {
            log.error("Storing dsFile: [$dsFile] in dataserver failed due to validation errors. " +
                    "Errors " + dsFile.getErrors())
            response.sendError(SC_BAD_REQUEST)
            return
        }

        redirect(uri: getDownloadPathForDsFile(dsFile))
    }

    private getDownloadPathForDsFile(dsFile) {
        "/r/download/" + dsFile.id
    }

    private validateMultipartFile(multipartFile) {
        multipartFile && !multipartFile.empty
    }
}