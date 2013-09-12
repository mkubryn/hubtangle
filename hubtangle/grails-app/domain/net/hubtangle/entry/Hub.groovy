package net.hubtangle.entry

import net.hubtangle.User;

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
	 * {@link User} who created this {@link Hub}
	 */
	static belongsTo = [creator: User]
	
	static hasMany = [entries: Entry]

	static constraints = {
		name unique: true
	}
	
	static mapping = {
	}
	
	def getSignature() {
		(id ? id * 13 : "non-persistent") + "_" + name.replaceAll("\\s+", "_")
	}
}
