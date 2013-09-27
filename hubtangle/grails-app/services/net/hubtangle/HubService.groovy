package net.hubtangle

import net.hubtangle.entry.Entry;
import net.hubtangle.entry.Hub
import net.hubtangle.entry.PostEntry
import net.hubtangle.model.ClassMatchingEntryMapper;
import net.hubtangle.model.exception.ModelValidationException
import net.hubtangle.user.User

import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException

class HubService {

	org.slf4j.Logger log = LoggerFactory.getLogger(HubService.class)
	
	def springSecurityService
	def entryMapper = new ClassMatchingEntryMapper()
	
	
	/**
	 * Creates and saves a sub type of {@link Entry} based on passed properties. 
	 * 
	 * Properties have to contain <code>entryType</code> such as "post", "photo" 
	 * and all the rest information required by the target domain class.
	 * 
	 * @param map of entry properties
	 * 
	 * @throws IllegalArgumentException when provided entryData isn't valid or complete
	 * @throws AccessDeniedException when security is violated
	 * @throws ModelValidationException when constraint of target domain class is violated
	 * 
	 * @return persisted entry
	 */
	def createAndSaveEntry(Map entryData, Long hubId) {
		if(!hubId || !entryData) {
			throw new IllegalArgumentException("Hub id and entry data must not be null.")
		}

		if(!springSecurityService.isLoggedIn()) {
			log.warn("[SECURITY] Attempt to create entry on hub(id=${hubId}) without security context!")
			throw new AccessDeniedException("No security context!")
		}
		
		if(!canPostOnHub(hubId)) {
			log.warn("[SECURITY] User(${springSecurityService.getCurrentUser().id}) " +
				"tried to post on hub (id=${hubId} without permission!")
			throw new AccessDeniedException("No permission to create entry!")
		}
		
		// delegate specific entry creation to mapper
		// mapper creates entry throws an exception
		Entry newEntry = entryMapper.map(entryData)
		
		newEntry.author = User.get(springSecurityService.getCurrentUser().id)
		newEntry.hub = Hub.get(hubId)
		
		// validate entry
		if(!newEntry.validate()) {
			throw new ModelValidationException(modelBean: newEntry)
		}
		
		newEntry.save()
	}
	
	
	/**
	 * Checks weather currently authenticated user has right to post on {@link Hub} of given id
	 * @param hubId hub id
	 * @return true if user has right to post on hub, false otherwise
	 */
    def canPostOnHub(hubId) {
		def currentUser = springSecurityService.getCurrentUser()
		if(!currentUser) {
			return false
		}
		
		def hub = Hub.get(hubId)
		if(hub == null) {
			return false
		}
		
		return hub.creator.id.equals(currentUser.id)
	}
	
	/**
	 * Checks if currently authenticated user can see {@link Entry} of provided id.
	 * @param entryId entry identifier
	 * @return true or false
	 */
	def canViewEntry(entryId) {
		/*
		 * TODO implement view entry permission logics
		 */
		Entry.get(entryId) != null
	}
}
