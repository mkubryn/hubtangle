package net.hubtangle.entry

import net.hubtangle.user.HUser
import net.hubtangle.security.acl.AclSecured;

/**
 * 
 * Represents a {@link Hub}.
 * @author mkubryn
 *
 */
class Hub {

	/**
	 * Name of this {@link Hub}.
	 */
	String name

	/**
	 * Text describing this {@link Hub}.
	 */
	String description

	/**
	 * This {@link Hub} creation date
	 */
	Date dateCreated

	/**
	 * Entries of this {@link Hub}
	 */
	SortedSet<Entry> entries
	
	/**
	 * {@link HUser} who created this {@link Hub}
	 */
	static belongsTo = [creator: HUser]
	
	static hasMany = [entries: Entry]

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
