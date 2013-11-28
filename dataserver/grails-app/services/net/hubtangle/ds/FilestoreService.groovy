package net.hubtangle.ds

import liquibase.util.MD5Util
import org.springframework.beans.factory.InitializingBean
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletResponse

/**
 * Service managing Dataserver's filestore
 */
class FilestoreService implements InitializingBean {

    def grailsApplication

    @Override
    void afterPropertiesSet() throws Exception {
        def filestoreDir = getFilestoreDir()
        def filestore = new File(filestoreDir)

        if(!filestore.exists()) {
            filestore.mkdirs()
        }
    }

    /**
     * Saves file in the filestore
     *
     * @param multipartFile The MultiPartFile to save
     * @return dsFile representing saved file
     */
    def saveFileInStore(MultipartFile multipartFile) {

        def filestoreLocation = computeLocationForMultipartFile(multipartFile)
        def realFileDestination = new File(getFilestoreDir() + File.separator + filestoreLocation)

        /*
         * Transfer file to filestore
         */
        realFileDestination.getParentFile().mkdirs()
        multipartFile.transferTo(realFileDestination)

        def dsFile = new DsFile(location: filestoreLocation, contentType: multipartFile.getContentType(),
                name: multipartFile.getOriginalFilename(), size: multipartFile.getSize())

        dsFile.save()
    }

    def getFileFromFilestore(DsFile dsFile) {
        def locationInFilestore = dsFile.location
        def file = new File(getFilestoreDir(), locationInFilestore)

        file
    }

    private computeLocationForMultipartFile(MultipartFile multipartFile) {

        def md5BasedLocation = "" << MD5Util.computeMD5(multipartFile.getInputStream())
        md5BasedLocation.insert(2, File.separator)
        md5BasedLocation.insert(5, File.separator)

        md5BasedLocation.toString()
    }

    private getFilestoreDir() {
        grailsApplication.config.ht.ds.filestore.dir
    }
}