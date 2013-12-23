package net.hubtangle.entry

import groovy.transform.EqualsAndHashCode
import net.hubtangle.api.search.Indexed

/**
 * Represents an image entry
 * 
 * @author mkubryn
 *
 */
@EqualsAndHashCode
class ImageEntry extends Entry {

    @Indexed
	Long dsFileId
	
    static constraints = {
    }
	
	def getRenderTemplateName() {
		"imageEntry"
	}
}
