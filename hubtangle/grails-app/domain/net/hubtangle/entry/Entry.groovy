package net.hubtangle.entry

import java.text.SimpleDateFormat

import javax.persistence.Transient;

import net.hubtangle.user.User;

/**
 * Represents an abstract entry. Entries belong to hubs
 * @author mkubryn
 */
abstract class Entry implements Comparable<Entry> {
	
	/**
	 * Author of this antry
	 */
	User author
	
	/**
	 * Parent hub of this entry
	 */
	Hub hub
	
	/**
	 * Entry title
	 */
	String title
	
	/**
	 * Creation date
	 */
	Date dateCreated
	
	/**
	 * Entry description
	 */
	String description
	
	/*
	 * GORM Mappings
	 */
	static belongsTo = [author: User, hub: Hub]
	
	static constraints = {
		description (nullable: true, size: 0..1024 )
		title (nullable: true) //FIXME - title shuldn't be nullable
    }
	
	static mapping = {
		/*
		 * Each subclass should have it's own sub-table
		 */
		tablePerHierarchy false
	}
	
	@Transient
	static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat('MMM')
	
	@Transient
	static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat('dd')
	
	def getDayOfMonth() {
		DAY_FORMAT.format(dateCreated)
	}
	
	def getMonthName() {
		MONTH_FORMAT.format(dateCreated)
	}
	
	/**
	 * Provides name of a GSP template to use in the rendering process of this entry
	 * 
	 * @return name of a GSP template to render using this entry
	 */
	abstract def getRenderTemplateName()

	@Override
	public int compareTo(Entry o) {
		return -1 * entryIsEarlierThan(o)
	}
	
	/**
	 * Checks weather this entry has earlier creation date than provided entry.
	 * @param anotherEntry entry to compare 
	 * @return true, if this entry has earlier creation date
	 */
	def entryIsEarlierThan(anotherEntry) {
		dateCreated.compareTo(anotherEntry.dateCreated)
	}
}