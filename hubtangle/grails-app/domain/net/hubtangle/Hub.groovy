package net.hubtangle

import groovy.transform.EqualsAndHashCode
import net.hubtangle.api.search.Indexed
import net.hubtangle.entry.HubAccessibility
import net.hubtangle.user.HUser

/**
 * 
 * Represents a {@link Hub}. This class should be as lightweight as possible.
 * @author mkubryn
 *
 */
@EqualsAndHashCode
class Hub implements Serializable {

	/**
	 * Name of this hub
	 */
    @Indexed
	String title

	/**
	 * Text describing this hub
	 */
    @Indexed
	String description

	/**
	 * This hub creation date
	 */
    @Indexed
	Date dateCreated

    /**
     * Hub accessibility
     */
    HubAccessibility accessibility = HubAccessibility.PUBLIC

	/**
	 * {@link HUser} who created this {@link Hub}
	 */
	static belongsTo = [creator: HUser]

	static constraints = {
		title unique: true
        title size: 3..50
    }
	
	static mapping = {
	}
	
	def getSignature() {
		(id ? id * 13 : "non-persistent") + "_" + title.replaceAll("\\s+", "_")
	}
}
