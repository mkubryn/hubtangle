package net.hubtangle

import net.hubtangle.api.ModelNotFoundException
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry
import org.springframework.security.access.AccessDeniedException

/**
 * Manages hubs and entries
 */
class HubService {

    def springSecurityService
    def permissionService
    def relationService

    def Hub getHub(Long hubId) {
        def hub = Hub.get(hubId)
        if(!hub) {
            throw new ModelNotFoundException('No hub with id ' + hubId)
        }

        if(!permissionService.canReadHub(hub)) {
            throw new AccessDeniedException("[SEC] User ${permissionService.getCurrentUserId()} " +
                    "tried to read hub $hub without permission.")
        }

        hub
    }

    def Entry getEntry(Long entryId) {
        def entry = Entry.get(entryId)
        if(!entry) {
            return null
        }

        if(!permissionService.canReadHub(entry.hub)) {
            throw new AccessDeniedException("[SEC] User ${permissionService.getCurrentUserId()} tried to read entry " +
                    "$entry without permission.")
        }

        entry
    }

    def Hub saveHub(Hub hub) {
        beforeSaveHub(hub)

        hub.creator = permissionService.getCurrentUser()
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

        entry.author = permissionService.getCurrentUser()

        if(!entry.id || !entry.dateCreated) {
            entry.dateCreated = new Date()
        }

        entry.save()

        if(!entry.hasErrors()) {
            afterSaveEntry(entry)
        }

        entry
    }

    def boolean deleteEntry(Long entryId) {
        if(!permissionService.canEditEntry(entryId)) {
            throw new AccessDeniedException('[SEC] User ${getCurrentUserId(false)} tried to delete entry ' +
                    '${entryId} wihtout permission!')
        }

        Entry.get(entryId).delete()
        true
    }

    def subscribeHub(Long hubId) {
        def hub = getHub(hubId)

        relationService.createRelation(Relations.SUBSCRIPTION, hubId, permissionService.getCurrentUserId())
    }

    def unsubscribeHub(Long hubId) {
        relationService.deleteRelation(Relations.SUBSCRIPTION, hubId, permissionService.getCurrentUserId())
    }

    def isSubscribingHub(Long hubId) {
        relationService.relationExists(Relations.SUBSCRIPTION, hubId, permissionService.getCurrentUserId())
    }

    /*
     * Private methods
     */

    private beforeSaveHub(Hub hub) {
        // is logged in?
        permissionService.getCurrentUser()

        // is moderator (if hub exists already)?
        if(hub.id) {
            boolean canEdit = relationService.relationExists(Relations.MODERATION, hub.id,
                    permissionService.getCurrentUserId())

            if(!canEdit) {
                throw new AccessDeniedException("User with id=${permissionService.getCurrentUserId()} " +
                        "tried to moderate hub=${hub.id} he doesn't own.")
            }
        }
    }

    private afterSaveHub(Hub hub) {
        rabbitSend 'entity.created.topic', 'all', hub

        def currentUserId = permissionService.getCurrentUserId()
        relationService.createRelation(Relations.WRITE, hub.id, currentUserId)
        relationService.createRelation(Relations.SUBSCRIPTION, hub.id, currentUserId)
        relationService.createRelation(Relations.MODERATION, hub.id, currentUserId)
    }

    private beforeSaveEntry(Entry entry) {
        // is logged in?
        permissionService.getCurrentUser()

        // can post on this hub?
        if (!permissionService.canPostOnHub(entry.hub.id)) {
            log.warn("User(${permissionService.getCurrentUserId()}) tried to post on hub " +
                    "(id=${entry.hub.id} without permission!")

            throw new AccessDeniedException("No permission to create entry!")
        }

        // can edit this entry (if entry already exists) ?
        if(!permissionService.canEditEntry(entry.id)) {
            log.warn("User(${permissionService.getCurrentUserId()}) tried to post on hub " +
                    "(id=${entry?.hub?.id} without permission!")

            throw new AccessDeniedException("No permission to create entry!")
        }
    }

    private afterSaveEntry(Entry newEntry) {
        def currentUserId = permissionService.getCurrentUserId()

        relationService.createRelation(Relations.READ, newEntry.id, currentUserId)
        relationService.createRelation(Relations.WRITE, newEntry.id, currentUserId)
        relationService.createRelation(Relations.MEMBER, newEntry.hub.id, currentUserId)

        rabbitSend 'entity.created.topic', 'all', newEntry
    }


}