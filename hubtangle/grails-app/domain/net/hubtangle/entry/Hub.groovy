package net.hubtangle.entry

import net.hubtangle.api.Indexed
import net.hubtangle.user.HUser

/**
 * 
 * Represents a {@link Hub}. This class should be as lightweight as possible.
 * @author mkubryn
 *
 */
class Hub {

	/**
	 * Name of this {@link Hub}.
	 */
    @Indexed
	String name

	/**
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
		name unique: true
        name size: 3..50
    }
	
	static mapping = {
	}
	
	def getSignature() {
		(id ? id * 13 : "non-persistent") + "_" + name.replaceAll("\\s+", "_")
	}
}
