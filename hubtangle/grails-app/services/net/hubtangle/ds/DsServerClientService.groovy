package net.hubtangle.ds

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.Method
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.FileItemFactory
import org.apache.commons.fileupload.disk.DiskFileItem
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.InputStreamBody
import org.codehaus.groovy.grails.io.support.IOUtils
import org.springframework.beans.factory.InitializingBean
import org.springframework.util.Assert
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile

import static javax.servlet.http.HttpServletResponse.*;
/**
 * Service managing data server capabilities
 *
 * @author mkubryn
 */
class DsServerClientService implements InitializingBean {

    /** Dataserver REST uri base*/
    def DS_SERVER_REST

    def configurationService

    @Override
    void afterPropertiesSet() throws Exception {
        DS_SERVER_REST = configurationService.getProp('ht.cluster.dataserver.uri', null)
        Assert.notNull(DS_SERVER_REST, "Dataserver rest url have to be provided!")

        log.info("Using dataserver rest URI: " + DS_SERVER_REST)
    }

    /**
     * Sends passed File to dataserver.
     *
     * @param file file to send
     * @param fieldName
     * @param contentType
     * @param isFormField
     *
     * @return identifier of new file in dataserver
     */
    def upload(InputStream input, String contentType, String fileName, boolean isFormField = false) {

        FileItemFactory factory = new DiskFileItemFactory()

        // Create Apache Commons FileItem & write file at fullFilePathString into it
        FileItem fi = factory.createItem("file", contentType, isFormField, fileName)

        // copy file from input to fileItem
        IOUtils.copy(input, fi.getOutputStream())

        // Convert FileItem to Spring wrapper: CommonsMultipartFile
        MultipartFile mf = new CommonsMultipartFile(fi)

        upload(mf)
    }

    /**
     * Sends multipart representation of a file to dataserver
     *
     * @param multipartImageFile
     * @return download location of freshly created file
     */
    def upload(CommonsMultipartFile multipartImageFile) {

        def http = new HTTPBuilder(DS_SERVER_REST + "/r/upload")

        def dsResponse = http.request(Method.POST, ContentType.TEXT) { req ->
            requestContentType: "multipart/form-data"

            MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)

            // Adding Multi-part file parameter "imageFile"
            multiPartContent.addPart("file", new InputStreamBody(multipartImageFile.inputStream,
                    multipartImageFile.contentType, multipartImageFile.originalFilename))

            req.setEntity(multiPartContent)

            response.success = { HttpResponseDecorator resp ->

                if(resp.statusLine.statusCode == SC_FOUND) {
                    /*
                     * This is where we get a real value out of response and
                     * pass it to dsId
                     */
                    return resp.getHeaders("location").first().getValue()
                }
            }
        }

        dsResponse
    }
}
