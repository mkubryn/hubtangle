package net.hubtangle.entry

/**
 * Represents an image entry
 * @author mkubryn
 *
 */
class ImageEntry extends Entry {

	// TODO url -> internal file id
	
	String url
	
    static constraints = {
    }
	
	
	def getRenderTemplateName() {
		"imageEntry"
	}
}
