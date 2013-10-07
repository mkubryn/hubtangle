package net.cnx

import javax.servlet.http.HttpServletResponse
import liquibase.util.MD5Util
import static net.cnx.ParamsHelper.*

class ServiceController {

	/*
	 * TODO move DS_FILESTORE_DIR it to conf
	 */
	def DS_FILESTORE_DIR = "/home/mkubryn/devel/hubtangle-eclipse-ws/ds_filestore"
	
	/*
	 * TODO remove clearFileStore method
	 */
	def clearFileStore() {
		
		DsFile.executeUpdate("delete DsFile")
		
		//new File(DS_FILESTORE_DIR).deleteDir()
		def res = new File(DS_FILESTORE_DIR).mkdirs()
		
		render "$res"
	}
	
	def uploadForm() {
		// just render view
	}
    
	def download() {
		def param = params['fid']
		
		/*
		 * Handle filename identifier 
		 */
		if(param && param.startsWith(".")) {
			/*
			 * TODO remove finding files by filename in production! 
			 */
			def fileName = param.substring(1)
			def dsFile = DsFile.findByName(fileName)
			
			if(dsFile)
				param = dsFile.id				
		}
		
		/*
		 * Handle numeric identifier
		 */
		def fileId = asLong(param)

		if(!fileId) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		
		def dsFile = DsFile.get(fileId)
		
		if(!dsFile) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
			return
		}
		
		def file = new File(DS_FILESTORE_DIR + File.separator + dsFile.location)
		
		if(!file.exists()) {
			log.warn("File with id [$fileId] has it's representation if dsFile [$dsFile] but it's not present in the filestore.")
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
			return
		}
		
		sendFile(file, dsFile)
	}
	
	def upload() {
		def multipartFile = request.getFile('myFile')
		
		if (!multipartFile || multipartFile.empty) {
			flash.message = 'file cannot be empty'
			render(view: 'uploadForm')
			return
		}
		
		def result = saveFileInStore(multipartFile)
		
		render "<div id='dsFileId'>${result}</div>"
	}
	
	/**
	 * Saves file in the filestore
	 * 
	 * @param multipartFile The MultiPartFile to save
	 * @return dsFile representing saved file
	 */
	private saveFileInStore(multipartFile) {
		def md5BasedLocation = "" << MD5Util.computeMD5(multipartFile.getInputStream())
		md5BasedLocation.insert(2, File.separator)
		md5BasedLocation.insert(5, File.separator)
		
		def realFileDestination = new File(DS_FILESTORE_DIR + File.separator + md5BasedLocation)
		realFileDestination.getParentFile().mkdirs()
		
		multipartFile.transferTo(realFileDestination)
		
		def dsFile = new DsFile(location: md5BasedLocation, contentType: multipartFile.getContentType(), 
			name: multipartFile.getOriginalFilename(), size: multipartFile.getSize())
		
		dsFile.save()
		
		if(dsFile.hasErrors()) {
			log.error("Saving file [$dsFile] in dataserver failed. Errors " + dsFile.getErrors())
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
			return false
		}
		
		dsFile.id
	}
	
	/**
	 * Sends file to the client via response's output stream
	 * 
	 * @param file real file in filestore
	 * @param dsFile dsFile representation of the file
	 * @param response http response
	 */
	private sendFile(file, dsFile) {
		if (file.exists()) {
		   response.setContentType(dsFile.contentType)
		   response.setHeader("Content-disposition", "filename=${dsFile.name}")
		   response.outputStream << file.bytes
		   return
		}
	}
}
