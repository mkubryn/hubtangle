package net.hubtangle.entry

import net.hubtangle.api.Indexed

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
