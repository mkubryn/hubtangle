package net.hubtangle

import grails.transaction.Transactional
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.HubAccessibility
import net.hubtangle.user.HUser
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.security.access.AccessDeniedException

@Transactional
class PermissionService implements ApplicationContextAware{

    ApplicationContext applicationContext
    def springSecurityService
    def relationService

    private HUser getCurrentUser(boolean failOnNotLoggedIn = true) {
        def user = springSecurityService.getCurrentUser()
        if (!user && failOnNotLoggedIn) {
            throw new AccessDeniedException("Asked for user without authenticated user in context!")
        }
        user
    }

    private Long getCurrentUserId(boolean failOnNotLoggedIn = true) {
        def user = getCurrentUser(failOnNotLoggedIn)

        user?.id
    }

    private boolean canReadHub(Hub hub) {
        if(hub.accessibility == HubAccessibility.PRIVATE) {
            def user = getCurrentUser()
            def isUsers = hub.creator.id == user.id
            def canRead = relationService.relationExists(Relations.READ, hub.id, user.id)
            return canRead || isUsers
        }
        return true
    }

    def boolean canPostOnHub(Long hubId) {
        relationService.relationExists(Relations.WRITE, hubId, getCurrentUserId())
    }

    def boolean canViewEntry(Long entryId) {
        // relationService.relationExists(Relations.READ, hubId, userId
        true
    }

    def boolean canEditEntry(Long entryId) {
        if(!entryId) {
            return true
        }
        getHubService().getEntry(entryId)?.author?.id == getCurrentUserId()
    }

    def boolean canModerateHub(Long hubId) {
        relationService.relationExists(Relations.MODERATION, hubId, getCurrentUserId())
    }

    def boolean canAdministrateHub(Long hubId) {
        def isUsers = getHubService().getHub(hubId).creator.id
        def hasAdminRelation = relationService.relationExists(Relations.ADMINISTATION, hubId, getCurrentUserId())

        isUsers || hasAdminRelation
    }

    private HubService getHubService() {
        applicationContext.getBean("hubService")
    }
}
