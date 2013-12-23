package net.hubtangle.entry

import groovy.transform.EqualsAndHashCode
import net.hubtangle.api.search.Indexed

import java.text.SimpleDateFormat

import javax.persistence.Transient;

import net.hubtangle.user.HUser;

/**
 * Represents an abstract entry. Entries belong to hubs
 * @author mkubryn
 */
@EqualsAndHashCode
abstract class Entry implements Comparable<Entry>, Serializable {

	/**
	 * Author of this antry
	 */
	HUser author
	
	/**
	 * Parent hub of this entry
	 */
	Hub hub
	
	/**
	 * Entry title
	 */
    @Indexed
	String title
	
	/**
	 * Creation date
	 */
    @Indexed
	Date dateCreated
	
	/**
	 * Entry description
	 */
    @Indexed
	String description
	
	/*
	 * GORM Mappings
	 */
	static belongsTo = [author: HUser, hub: Hub]
	
	static constraints = {
		description (nullable: true, size: 0..300 )
		title (nullable: true) //FIXME - title shuldn't be nullable
    }
	
	static mapping = {
		/*
		 * Each subclass should have it's own sub-table
		 */
		tablePerHierarchy false
        dateCreated index: 'entry_date_created_idx'
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
	 * Provides title of a GSP template to use in the rendering process of this entry
	 * 
	 * @return title of a GSP template to render using this entry
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