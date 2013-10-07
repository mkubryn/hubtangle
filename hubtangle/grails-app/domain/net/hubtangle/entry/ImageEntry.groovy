package net.hubtangle.entry

/**
 * Represents an image entry
 * 
 * @author mkubryn
 *
 */
class ImageEntry extends Entry {

	Long dsFileId
	
    static constraints = {
    }
	
	def getRenderTemplateName() {
		"imageEntry"
	}
}
