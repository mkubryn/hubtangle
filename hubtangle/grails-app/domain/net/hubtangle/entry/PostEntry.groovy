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
		content size: 0..3000 
    }

	def getRenderTemplateName() {
		"postEntry"
	}
}