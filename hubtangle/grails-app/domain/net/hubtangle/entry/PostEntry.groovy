package net.hubtangle.entry

/**
 * Represents a post entry
 * @author mkubryn
 *
 */
class PostEntry extends Entry {
	
    static constraints = {
    }

	def getRenderTemplateName() {
		"postEntry"
	}
}