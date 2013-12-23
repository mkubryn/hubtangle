package net.hubtangle

import grails.transaction.Transactional
import net.hubtangle.api.security.acl.Relations
import org.slf4j.LoggerFactory

@Transactional
class RelationService {
    private static final log = LoggerFactory.getLogger(this)

    def redisService

    /**
     * Creates relation between nodes
     * @param relationType
     * @param from
     * @param to
     * @return tuple of ???
     */
    def createRelation(Relations relationType, from, to, boolean bidirectional = true) {

        def forwardKey = createKey(relationType, from)
        def reverseKey = createKey(relationType, to)

        def forward = redisService.sadd(forwardKey, to as String)
        def reverse = null

        if (bidirectional) {
            reverse = redisService.sadd(reverseKey, from as String)
        }

        log.info "Realation created [${relationType}:  $from <-> $to]. Forward key: $forwardKey | reverse key: $reverseKey"

        return [from, reverse]
    }

    def deleteRelation(Relations relation, from, to) {
        redisService.srem(createKey(relation, from), to as String)
        redisService.srem(createKey(relation, to), from as String)
    }

    def boolean relationExists(Relations relation, from, to) {
        boolean forward = redisService.sismember(createKey(relation, from), to as String)
        boolean reverse = redisService.sismember(createKey(relation, to), from as String)

        forward || reverse
    }

    def getRelations(Relations relation, from) {
        redisService.smembers(createKey(relation, from))
    }

    def getRelationsAsLongList(Relations relation, from) {
        def rels = getRelations(relation, from)
        rels.collect {
            it as Long
        }
    }

    private String createKey(Relations relation, nodeId) {
        "${relation}::${nodeId}"
    }
}