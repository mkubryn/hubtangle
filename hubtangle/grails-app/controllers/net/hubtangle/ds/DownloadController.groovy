package net.hubtangle.ds

import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletResponse

class DownloadController {

    private static final log = LoggerFactory.getLogger(this)
    def grailsApplication

    /**
     * Simple proxy for downloading files from dataserver
     */
    def download() {
        def url = new URL(grailsApplication.config.ht.cluster.dataserver.uri + '/r/download/' + params.dsFileId)

        def connection = url.openConnection()

        try {
            def is = connection.getInputStream()
            def os = response.getOutputStream()
            IOUtils.copy(is, os)
            os.flush()
        } catch(e) {
            log.info "Sending 404 as a result of exception: ", e
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
            return
        }
    }
}
