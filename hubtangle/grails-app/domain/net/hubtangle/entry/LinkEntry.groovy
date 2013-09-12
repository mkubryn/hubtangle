package net.hubtangle.entry

/**
 * Represents an hub entry linking to a remote content in the web such as external webpage
 * @author mkubryn
 *
 */
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
