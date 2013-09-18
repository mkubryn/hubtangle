package net.hubtangle

/**
 * Provides taglib implementation for Open-Ds integration
 * @author mkubryn
 *
 */
class DsTagLib {
	static namespace = "ds"
	
	def img = { attrs ->
		def name = attrs.name
		def idnetifier = attrs.id
		
		out << createImgTag(name, idnetifier)
	}
	
	def createImgTag(name, idnetifier) {
		def sb = new StringBuilder()
		
		// TODO ds url for tags is hardcoded. Should be configurable
		def downloadUrl = 'http://localhost:8090/open-ds/srv/' << ""
		
		if(idnetifier) {
			downloadUrl << idnetifier
		} else if (name) {
			downloadUrl << "." << name
		}
		
		
		sb << """
			<img src='${downloadUrl}' />'
		"""
		
		//sb.toString() //FIXME mock
		"<img src='http://localhost:8080/hubtangle/img/test/rnd/" + (new Random().next(8) % 5) + ".jpg' />"
	}
	
	
}
