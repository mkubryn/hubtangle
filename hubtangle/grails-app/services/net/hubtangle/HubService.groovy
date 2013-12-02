package net.hubtangle

import net.hubtangle.entry.Entry;
import net.hubtangle.entry.Hub
import net.hubtangle.model.exception.ModelValidationException
import net.hubtangle.user.HUser
import net.hubtangle.utils.ClassMatchingEntryMapper
import net.hubtangle.utils.AceKeys
import net.hubtangle.utils.ControllerUtils
import org.springframework.security.access.AccessDeniedException

class HubService {

    def springSecurityService
    def aclService
	def entryMapper = new ClassMatchingEntryMapper()

    def saveHub(Hub hub) {

        def user = springSecurityService.getCurrentUser()
        hub.creator = user

       if (hub.save()) {
           aclService.addAce(AceKeys.HUB_W, hub.id)
       }

        hub
    }

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

		newEntry.author = HUser.get(aclService.getCurrentUserId())
		newEntry.hub = Hub.get(hubId)

		// validate entry
		if(!newEntry.validate()) {
			throw new ModelValidationException(modelBean: newEntry)
		}

        newEntry.save(failOnError: true)
        aclService.addAce(AceKeys.ENTRY_R, newEntry.getId())
        aclService.addAce(AceKeys.ENTRY_W, newEntry.getId())

        newEntry
	}
	
	/**
	 * Checks weather currently authenticated user has right to post on {@link Hub} of given id
	 * @param hubId hub id
	 * @return true if user has right to post on hub, false otherwise
	 */
    def canPostOnHub(hubId) {
        aclService.hasAce(AceKeys.HUB_W, hubId)
	}
	
	/**
	 * Checks if currently authenticated user can see {@link Entry} of provided id.
	 * @param entryId entry identifier
	 * @return true or false
	 */
	def canViewEntry(entryId) {
        //aclService.hasAce(AceKeys.ENTRY_R, entryId)
        true
    }
}