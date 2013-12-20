package net.hubtangle.entry

import net.hubtangle.api.Indexed

/**
 * Represents a post entry
 * @author mkubryn
 *
 */
class PostEntry extends Entry {

	/**
	 * Content of this post
	 */
    @Indexed
	String content
	
    static constraints = {
		content size: 0..3000 
    }

	def getRenderTemplateName() {
		"postEntry"
	}
}