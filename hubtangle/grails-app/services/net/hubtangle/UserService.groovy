package net.hubtangle

import grails.transaction.Transactional
import net.hubtangle.api.command.SearchCommand
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.utils.GormUtils

@Transactional
class UserService {

    def relationService
    def springSecurityService

    /**
     * Returns hubs which the current user is a member of
     */
    def getHubMemberships(SearchCommand searchCommand) {
        def hubIds = relationService.getRelationsAsLongList(Relations.MEMBER, getCurrentUserId())


    }

    private getCurrentUserId() {
        def userId = springSecurityService.getCurrentUser()?.id
        if (!userId) {
            throw new IllegalStateException("Asked for user id without authenticated user in context!")
        }
        userId
    }
}
