package net.hubtangle


import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry;
import net.hubtangle.entry.Hub
import net.hubtangle.model.exception.ModelValidationException
import net.hubtangle.utils.ClassMatchingEntryMapper
import org.springframework.security.access.AccessDeniedException

class HubService {

    def springSecurityService
    def relationService

    def entryMapper = ClassMatchingEntryMapper.instance

    /**
     * Saves hub
     * @param hub hub to save
     * @return  saved hub
     */
    def saveHub(Hub hub) {
        beforeSaveHub(hub)

        // validate and save
        hub.creator = springSecurityService.getCurrentUser()
        if(!hub.validate()) {
            throw new ModelValidationException(modelBean:  hub)
        }
        hub.save(failOnError: true)

        afterSaveHub(hub)
        hub
    }

    private beforeSaveHub(Hub hub) {
        // is logged in?
        if(!springSecurityService.isLoggedIn()) {
            throw new AccessDeniedException('Tried to save hub without security context.')
        }

        // is moderator (if hub exists already)?
        if(hub.id) {
            boolean canEdit = relationService.relationExists(Relations.MODERATION, hub.id, getCurrentUserId())
            if(!canEdit) {
                throw new AccessDeniedException("User with id=${getCurrentUserId()} " +
                        "tried to moderate hub=${hub.id} he doesn't own.")
            }
        }
    }

    private afterSaveHub(Hub hub) {
        rabbitSend 'entity.created.topic', 'all', hub

        def currentUserId = getCurrentUserId()
        relationService.createRelation(Relations.WRITE, hub.id, currentUserId)
        relationService.createRelation(Relations.SUBSCRIPTION, hub.id, currentUserId)
        relationService.createRelation(Relations.MODERATION, hub.id, currentUserId)
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
        if (!hubId || !entryData) {
            throw new IllegalArgumentException("Hub id and entry data must not be null.")
        }

        Entry entry = entryMapper.map(entryData)
        entry.hub = Hub.get(hubId)
        if(!entry.hub) {
            throw new IllegalArgumentException("Invalid hub identifier: " + hubId)
        }

        saveEntry(entry)
    }

    def saveEntry(Entry entry) {
        beforeSaveEntry(entry)

        // validate entity and save
        entry.author = springSecurityService.getCurrentUser()
        if (!entry.validate()) {
            throw new ModelValidationException(modelBean: entry)
        }
        entry.save(failOnError: true)

        afterSaveEntry(entry)

        entry
    }

    private beforeSaveEntry(Entry entry) {
        // is logged in?
        if (!springSecurityService.isLoggedIn()) {
            log.warn("Attempt to create entry on hub(id=${hubId}) without security context!")
            throw new AccessDeniedException("No security context!")
        }

        // can post on this hub?
        if (!canPostOnHub(entry.hub.id)) {
            log.warn("User(${getCurrentUserId()}) tried to post on hub (id=${entry.hub.id} without permission!")
            throw new AccessDeniedException("No permission to create entry!")
        }

        // can edit this entry (if entry already exists) ?
        if(!canEditEntry(entry.id)) {
            log.warn("User(${getCurrentUserId()}) tried to post on hub (id=${hubId} without permission!")
            throw new AccessDeniedException("No permission to create entry!")
        }
    }

    private afterSaveEntry(Entry newEntry) {
        def currentUserId = getCurrentUserId()

        relationService.createRelation(Relations.READ, newEntry.id, currentUserId)
        relationService.createRelation(Relations.WRITE, newEntry.id, currentUserId)
        relationService.createRelation(Relations.MEMBER, newEntry.hub.id, currentUserId)

        rabbitSend 'entity.created.topic', 'all', newEntry
    }

    /**
     * Checks weather currently authenticated user has right to post on {@link Hub} of given id
     * @param hubId hub id
     * @return true if user has right to post on hub, false otherwise
     */
    def canPostOnHub(hubId) {
        if (!springSecurityService.isLoggedIn()) {
            return false
        }
        relationService.relationExists(Relations.WRITE, hubId, getCurrentUserId())
    }

    /**
     * Checks if currently authenticated user can see {@link Entry} of provided id.
     * @param entryId entry identifier
     * @return true or false
     */
    def canViewEntry(Long entryId) {
        // relationService.relationExists(Relations.READ, hubId, userId
        true
    }

    /**
     * Checks if currently authenticated user can see {@link Entry} of provided id.
     * @param entryId entry identifier
     * @return true or false
     */
    def canEditEntry(Long entryId) {
        if(!entryId) {
            return true
        }
        Entry.get(entryId)?.author?.id == getCurrentUserId()
    }

    /**
     * Checks if currently authenticated user is moderator of hub.
     * @param hubId hub identifier
     * @return true or false
     */
    def canModerateHub(Long hubId) {
        relationService.relationExists(Relations.MODERATION, hubId, getCurrentUserId())
    }

    /**
     * Creates a subscription of passed hub (of passed id) to the currently authenticated user
     *
     * @param hubId identifier of a hub to subscribe
     * @return
     */
    def subscribeHub(Long hubId) {
        if (!hubId) {
            throw new IllegalArgumentException('Hub id must not be null')
        }

        if (!Hub.exists(hubId)) {
            throw new IllegalArgumentException('Hub not found')
        }

        def userId = getCurrentUserId()

//        if(!relationService.relationExists(Relations.READ, hubId, userId)) {
//            throw new IllegalArgumentException('User has no rights to read hub with id ${hubId}')
//        }

        relationService.createRelation(Relations.SUBSCRIPTION, hubId, userId)
    }

    /**
     * Creates a subscription of passed hub (of passed id) to the currently authenticated user
     *
     * @param hubId identifier of a hub to subscribe
     * @return
     */
    def unsubscribeHub(Long hubId) {
        if (!hubId) {
            throw new IllegalArgumentException('Hub id must not be null')
        }

        def userId = getCurrentUserId()

        relationService.deleteRelation(Relations.SUBSCRIPTION, hubId, userId)
    }

    def isSubscribingHub(Long hubId) {
        if (!springSecurityService.isLoggedIn()) {
            return false
        }

        relationService.relationExists(Relations.SUBSCRIPTION, hubId, getCurrentUserId())
    }

    private getCurrentUserId() {
        def userId = springSecurityService.getCurrentUser()?.id
        if (!userId) {
            throw new IllegalStateException("Asked for user id without authenticated user in context!")
        }
        userId
    }
}