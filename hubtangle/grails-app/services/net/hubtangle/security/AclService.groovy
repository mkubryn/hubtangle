package net.hubtangle.security

import groovy.transform.CompileStatic
import net.hubtangle.utils.AceKeys

class AclService {

    final def UNKNOWN_USER_ID = -10001

    def redisService
    def springSecurityService

    def addAce(AceKeys key, Long entityId, Long userId=getCurrentUserId()) {
        redisService.sadd(key.getAceDiscriminator(entityId), userId as String)
    }

    def boolean hasAce(AceKeys key, Long entityId, Long userId=getCurrentUserId()) {
        redisService.sismember(key.getAceDiscriminator(entityId), userId as String)
    }

    def Long getCurrentUserId() {
        def currentUser = springSecurityService.getCurrentUser()
        if(!currentUser) {
            return UNKNOWN_USER_ID
        }

        currentUser.id
    }
}
