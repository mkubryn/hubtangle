package net.hubtangle.entry

import groovy.transform.EqualsAndHashCode

/**
 * Represents a hub entry linking to a remote content in the web such as an external web page
 * 
 * @author mkubryn
 *
 */
@EqualsAndHashCode
class LinkEntry extends Entry {

	/**
	 * Url of the remote content
	 */
	String url
	
    static constraints = {
	}
	
	def getRenderTemplateName() {
		"linkEntry"
	}
}
