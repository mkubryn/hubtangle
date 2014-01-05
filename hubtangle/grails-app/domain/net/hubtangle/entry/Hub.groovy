package net.hubtangle.entry

import groovy.transform.EqualsAndHashCode
import net.hubtangle.api.search.Indexed
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
	 * Name of this {@link Hub}.
	 */
    @Indexed
	String title

	/**             <
	 * Text describing this {@link Hub}.
	 */
    @Indexed
	String description

	/**
	 * This {@link Hub} creation date
	 */
    @Indexed
	Date dateCreated
	
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
