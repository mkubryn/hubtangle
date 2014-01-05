package net.hubtangle

import org.slf4j.LoggerFactory

class HubTagLib {
    static namespace = "sec"
    private static log = LoggerFactory.getLogger(this)

    def hubService

    /**
     * Renders the body if currently authenticated user has a right to post on hub of passed id
     *
     * @attr hubId REQUIRED the hub id
     */
    def ifUserCanPostOnHub = { attrs, body ->
        String hubId = getHubId(attrs)

        if (securityAware { hubService.canPostOnHub(Long.parseLong(hubId)) }) {
            out << body()
        }
    }

    def ifUserIsHubModerator = { attrs, body ->
        String hubId = getHubId(attrs)

        if (securityAware { hubService.canModerateHub(Long.parseLong(hubId)) }) {
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

    private getHubId(attrs) {
        String hubId = attrs.hubId
        if (!attrs) {
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