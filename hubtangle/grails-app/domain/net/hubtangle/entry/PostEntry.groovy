package net.hubtangle.entry

/**
 * Represents a post entry
 * @author mkubryn
 *
 */
class PostEntry extends Entry {
	
	/**
	 * Content of this post
	 */
	String content
	
    static constraints = {
    }

	def getRenderTemplateName() {
		"postEntry"
	}
}