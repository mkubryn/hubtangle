package net.hubtangle.entry

import groovy.transform.EqualsAndHashCode
import net.hubtangle.api.search.Indexed

/**
 * Represents a post entry
 * @author mkubryn
 *
 */
@EqualsAndHashCode
class PostEntry extends Entry {

	/**
	 * Content of this post
	 */
    @Indexed
	String content

    @Indexed
    Long dsFileId
	
    static constraints = {
		content size: 0..10000
        dsFileId nullable: true
    }

	def getRenderTemplateName() {
		"postEntry"
	}
}