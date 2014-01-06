package net.hubtangle


import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry;
import net.hubtangle.entry.Hub
import net.hubtangle.model.exception.ModelValidationException
import net.hubtangle.utils.ClassMatchingEntryMapper
import org.springframework.security.access.AccessDeniedException

/**
 * Manages hubs and entries
 */
class HubService {

    def springSecurityService
    def relationService

    def Hub getHub(Long hubId) {
        def hub = Hub.get(hubId)
        if(!hub) {
            throw new IllegalArgumentException('No hub with id ' + hubId)
        }

        if(!canReadHub(hub)) {
            throw new AccessDeniedException("[SEC] User ${getCurrentUserId()} " +
                    "tried to read hub $hub without permission.")
        }

        hub
    }

    def Entry getEntry(Long entryId) {
        def entry = Entry.get(entryId)
        if(!entry) {
            throw new IllegalArgumentException('No entry with id ' + entryId)
        }

        if(!canReadHub(entry.hub)) {
            throw new AccessDeniedException("[SEC] User ${getCurrentUserId()} tried to read entry " +
                    "$entry without permission.")
        }

        entry
    }

    def Hub saveHub(Hub hub) {
        beforeSaveHub(hub)

        hub.creator = getCurrentUser()
        if(!hub.id || !hub.dateCreated) {
            hub.dateCreated = new Date()
        }

        hub.save()

        if(!hub.hasErrors()) {
            afterSaveHub(hub)
        }

        hub
    }

    def Entry saveEntry(Entry entry) {
        beforeSaveEntry(entry)

        entry.author = getCurrentUser()

        if(!entry.id || !entry.dateCreated) {
            entry.dateCreated = new Date()
        }

        entry.save()

        if(!entry.hasErrors()) {
            afterSaveEntry(entry)
        }

        entry
    }

    def subscribeHub(Long hubId) {
        def hub = getHub(hubId)

        relationService.createRelation(Relations.SUBSCRIPTION, hubId, getCurrentUserId())
    }

    def unsubscribeHub(Long hubId) {
        relationService.deleteRelation(Relations.SUBSCRIPTION, hubId, getCurrentUserId())
    }

    def isSubscribingHub(Long hubId) {
        relationService.relationExists(Relations.SUBSCRIPTION, hubId, getCurrentUserId())
    }

    def boolean canPostOnHub(Long hubId) {
        relationService.relationExists(Relations.WRITE, hubId, getCurrentUserId())
    }

    def boolean canViewEntry(Long entryId) {
        // relationService.relationExists(Relations.READ, hubId, userId
        true
    }

    def canEditEntry(Long entryId) {
        if(!entryId) {
            return true
        }
        Entry.get(entryId)?.author?.id == getCurrentUserId()
    }

    def canModerateHub(Long hubId) {
        relationService.relationExists(Relations.MODERATION, hubId, getCurrentUserId())
    }

    /*
     * Private methods
     */

    private beforeSaveHub(Hub hub) {
        // is logged in?
        getCurrentUser()

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

    private beforeSaveEntry(Entry entry) {
        // is logged in?
        getCurrentUser()

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

    private getCurrentUser(boolean failOnNotLoggedIn = true) {
        def user = springSecurityService.getCurrentUser()
        if (!user && failOnNotLoggedIn) {
            throw new AccessDeniedException("Asked for user without authenticated user in context!")
        }
        user
    }

    private getCurrentUserId(boolean failOnNotLoggedIn = true) {
        def userId = springSecurityService.getCurrentUser()?.id
        if (!userId && failOnNotLoggedIn) {
            throw new AccessDeniedException("Asked for user id without authenticated user in context!")
        }
        userId
    }

    private canReadHub(Hub hub) {
        def user = getCurrentUser()
        if(!hub.isPublic) {
            def canRead = relationService.relationExists(Relations.READ, hub.id, user.id)
            return canRead
        }
        return true
    }
}