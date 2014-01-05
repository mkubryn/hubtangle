package net.hubtangle

import grails.converters.JSON
import grails.transaction.Transactional
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry

@Transactional
class TagService {

    static rabbitQueue = 'tagQueue'
    static relationService

    def processTagging(model) {
        def modelJson = model as JSON
        def tags = getTagsFromText(modelJson as String)

        def modelKey = getModelKey(model)

        tags.each { tag ->
            relationService.createRelation(Relations.TAG, modelKey, tag)
        }
    }

    def getTagsForModel(model) {
        def modelKey = getModelKey(model)

        relationService.getRelations(Relations.TAG, modelKey)
    }

    def getModelsForTag(tag) {
        relationService.getRelations(Relations.TAG, tag)
    }


    private getModelKey(model) {
        "${model.id}::${model.class.name}"
    }

    private getTagsFromText(String text) {
        def tags = []
        def tagsWithHashes = text.findAll('#(\\w+)')
        tagsWithHashes.each { tag ->
            tags << tag.replaceAll('#', '')
        }

        tags
    }

    /**
     * Handles message from 'searchIndexQueue' queue and indexes it's payload
     * @param message
     */
    void handleMessage(message) {
        processTagging(message)
    }
}
