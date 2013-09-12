package net.hubtangle.entry

/**
 * Represents a video entry
 * @author mkubryn
 *
 */
class VideoEntry extends Entry {
	
	/**
	 * Location of the video. 
	 */
	String url

    static constraints = {
    }
	
	
	def getRenderTemplateName() {
		"videoEntry"
	}
}
