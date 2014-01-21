package net.hubtangle

import org.slf4j.LoggerFactory

class HubTagLib {
    static namespace = "hub"
    private static log = LoggerFactory.getLogger(this)

    def permissionService
    def hubService

    /**
     * Renders the body if currently authenticated user has a right to post on hub of passed id
     *
     * @attr hubId REQUIRED the hub id
     */
    def ifUserCanPostOnHub = { attrs, body ->
        String hubId = getHubId(attrs)

        if (securityAware { permissionService.canPostOnHub(Long.parseLong(hubId)) }) {
            out << body()
        }
    }

    def ifUserIsHubModerator = { attrs, body ->
        String hubId = getHubId(attrs)

        if (securityAware { permissionService.canModerateHub(Long.parseLong(hubId)) }) {
            out << body()
        }
    }

    def ifUserIsSubscribingHub = { attrs, body ->
        def hubId = getHubId(attrs)

        if (securityAware { hubService.isSubscribingHub(hubId) }) {
            out << body()
        }
    }

    def ifUserIsNotSubscribingHub = { attrs, body ->
        def hubId = getHubId(attrs)

        if ( securityAware {  !hubService.isSubscribingHub(hubId) }) {
            out << body()
        }
    }

    def ifUserCanEditEntry = { attrs, body ->
        def entryId = getEntryId(attrs)

        if ( securityAware { permissionService.canEditEntry(entryId) }) {
            out << body()
        }
    }

    def ifUserIsHubAdministrator = { attrs, body ->
        def hubId = getHubId(attrs)

        if (securityAware { permissionService.canAdministrateHub(hubId) }) {
            out << body()
        }
    }

    private getEntryId(attrs) {
        String entryId = attrs.entryId
        if (!entryId) {
            throw new AssertionError("You must provide entryId parameter")
        }
        entryId as Long
    }

    private getHubId(attrs) {
        String hubId = attrs.hubId
        if (!hubId) {
            throw new AssertionError("You must provide hubId parameter")
        }
        hubId as Long
    }

    private def securityAware = { Closure func ->
        try {
            return func()
        } catch (Exception e) {
            log.debug 'Exception while accessing service: ', e
            return false
        }
    }
}