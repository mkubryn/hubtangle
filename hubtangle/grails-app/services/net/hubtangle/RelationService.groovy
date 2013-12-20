package net.hubtangle

import grails.transaction.Transactional
import net.hubtangle.api.security.acl.Relations

@Transactional
class RelationService {

    def redisService

    /**
     * Creates relation between nodes
     * @param relationType
     * @param from
     * @param to
     * @return tuple of ???
     */
    def createRelation(Relations relationType, from, to, boolean bidirectional=true) {
        def forward = redisService.sadd(createKey(relationType, from), from as String)
        def reverse = null

        if(bidirectional) {
            reverse = redisService.sadd(createKey(relationType, to), from as String)
        }

        return [from, reverse]
    }

    def deleteRelation(Relations relation, from, to) {
        redisService.srem(createKey(relation, from), to)
        redisService.srem(createKey(relation, to), from)
    }

    def boolean relationExists(Relations relation, from, to) {
        boolean forward = redisService.sismember(createKey(relation, from), to as String)
        boolean reverse = redisService.sismember(createKey(relation, to), from as String)

        forward || reverse
    }

    def getRelations(Relations relation, from) {
        def relations = redisService.smembers(createKey(relation, from))
        asLongList(relations)
    }

    private String createKey(Relations relation, nodeId) {
        "${relation}::${nodeId}"
    }

    private asLongList(Collection list) {
        list.collect { item ->
            item as Long
        }
    }
}