package net.hubtangle

class HubTagLib {
    static namespace = "sec"

    def hubService

    /**
     * Renders the body if currently authenticated user has a right to post on hub of passed id
     *
     * @attr hubId REQUIRED the hub id
     */
    def ifUserCanPostOnHub = { attrs, body ->
        String hubId = getHubId(attrs)

        if (hubService.canPostOnHub(Long.parseLong(hubId))) {
            out << body()
        }
    }

    def ifUserIsSubscribingHub = { attrs, body ->
        def hubId = getHubId(attrs)

        if (hubService.isSubscribingHub(hubId)) {
            out << body()
        }
    }

    def ifUserIsNotSubscribingHub = { attrs, body ->
        def hubId = getHubId(attrs)

        if (!hubService.isSubscribingHub(hubId)) {
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
}