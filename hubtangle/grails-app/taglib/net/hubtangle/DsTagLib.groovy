package net.hubtangle

/**
 * Provides taglib implementation for Open-Ds integration
 * @author mkubryn
 *
 */
class DsTagLib {
	static namespace = "ds"

    def grailsApplication
	
	def img = { attrs ->
		def name = attrs.name
		def idnetifier = attrs.id
		
		out << createImgTag(name, idnetifier)
	}

    def prettyImg = { attrs ->
        def name = attrs.name
        def idnetifier = attrs.id

        out << createPrettyImgTag(name, idnetifier)
    }

	private createImgTag(name, idnetifier) {
		def sb = new StringBuilder()
		
		def downloadUrl = grailsApplication.config.ht.cluster.dataserver.uri << "/r/download/"

        if(".test_photo.png" == idnetifier) {
            //FIXME remove generating random files from dsTaglib
            return "<img src='http://localhost:8080/hubtangle/img/test/rnd/" + (new Random().next(8) % 5) + ".jpg' />"
        }

		if(idnetifier) {
			downloadUrl << idnetifier
		} else if (name) {
			downloadUrl << "." << name
		}

		sb << """
			<img src='${downloadUrl}' />'
		"""
	}

    private createPrettyImgTag(name, idnetifier) {
        def sb = new StringBuilder()

        def downloadUrl = grailsApplication.config.ht.cluster.dataserver.uri << "/r/download/"

        if(".test_photo.png" == idnetifier) {
            //FIXME remove generating random files from dsTaglib
            return "<img src='http://localhost:8080/hubtangle/img/test/rnd/" + (new Random().next(8) % 5) + ".jpg' />"
        }

        if(idnetifier) {
            downloadUrl << idnetifier
        } else if (name) {
            downloadUrl << "." << name
        }

        sb << """
            <a href='${downloadUrl}' data-rel="prettyPhoto">
			    <img src='${downloadUrl}' />'
            </a>
		"""
    }
}
