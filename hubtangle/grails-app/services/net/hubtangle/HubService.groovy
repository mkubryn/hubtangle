package net.hubtangle


import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry;
import net.hubtangle.entry.Hub
import net.hubtangle.model.exception.ModelValidationException
import net.hubtangle.user.HUser
import net.hubtangle.utils.ClassMatchingEntryMapper
import org.springframework.security.access.AccessDeniedException

class HubService {

    def springSecurityService
	def entryMapper = ClassMatchingEntryMapper.instance
    def relationService

    def saveHub(Hub hub) {
        def user = springSecurityService.getCurrentUser()
        hub.creator = user

        if (hub.save()) {
           relationService.createRelation(Relations.WRITABLE, hub.id, user.id)
           relationService.createRelation(Relations.SUBSCRIPTION, hub.id, user.id)
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

		newEntry.author = HUser.get(getCurrentUserId())
		newEntry.hub = Hub.get(hubId)

		// validate entry
		if(!newEntry.validate()) {
			throw new ModelValidationException(modelBean: newEntry)
		}

        newEntry.save(failOnError: true)

        relationService.createRelation(Relations.READABLE, newEntry.id, getCurrentUserId())
        relationService.createRelation(Relations.WRITABLE, newEntry.id, getCurrentUserId())

        rabbitSend 'entity.created.topic', 'all', newEntry

        newEntry
	}
	
	/**
	 * Checks weather currently authenticated user has right to post on {@link Hub} of given id
	 * @param hubId hub id
	 * @return true if user has right to post on hub, false otherwise
	 */
    def canPostOnHub(hubId) {
        relationService.relationExists(Relations.WRITABLE, hubId, getCurrentUserId())
	}
	
	/**
	 * Checks if currently authenticated user can see {@link Entry} of provided id.
	 * @param entryId entry identifier
	 * @return true or false
	 */
	def canViewEntry(Long entryId) {
       // relationService.relationExists(Relations.READABLE, hubId, userId
        true
    }

    /**
     * Creates a subscription of passed hub (of passed id) to the currently authenticated user
     *
     * @param hubId identifier of a hub to subscribe
     * @return
     */
    def subscribeHub(Long hubId) {
        if(!hubId) {
            throw  new IllegalArgumentException('Hub id must not be null')
        }

        if(!Hub.exists(hubId)){
            throw new IllegalArgumentException('Hub not found')
        }

        def userId = getCurrentUserId()

//        if(!relationService.relationExists(Relations.READABLE, hubId, userId)) {
//            throw new IllegalArgumentException('User has no rights to read hub with id ${hubId}')
//        }

        relationService.createRelation(Relations.SUBSCRIPTION, hubId, userId)
    }

    def isSubscribingHub(Long hubId) {
        if(!springSecurityService.isLoggedIn()) {
            return false
        }

        relationService.relationExists(Relations.SUBSCRIPTION, hubId, getCurrentUserId())
    }

    private getCurrentUserId() {
        def userId = springSecurityService.getCurrentUser().id
        if(!userId) {
            throw new IllegalStateException("Asked for user id without authenticated user in context!")
        }
        userId
    }
}