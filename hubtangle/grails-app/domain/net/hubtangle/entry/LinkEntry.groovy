package net.hubtangle.entry

import net.hubtangle.security.acl.AclSecured

/**
 * Represents a hub entry linking to a remote content in the web such as an external web page
 * 
 * @author mkubryn
 *
 */
@AclSecured
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
