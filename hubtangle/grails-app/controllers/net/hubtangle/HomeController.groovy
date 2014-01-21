package net.hubtangle

import grails.plugins.springsecurity.Secured
import net.hubtable.*
import net.hubtangle.api.security.acl.Relations
import net.hubtangle.entry.Entry
import org.slf4j.LoggerFactory;

/**
 * Controller managing home pages
 *
 * @author mkubryn
 */
class HomeController {

    def private static log = LoggerFactory.getLogger(this)

    def relationService
    def springSecurityService
    def tagService

    @Secured('ROLE_USER')
    def index() {
        if (!springSecurityService.isLoggedIn()) {
            render(view: "homepage", model: [hubMap: [:], lastEntries: []])
            return
        }

        def hubIds = relationService.getRelationsAsLongList(Relations.SUBSCRIPTION,
                springSecurityService.getCurrentUser().id)

        def entrs = Entry.createCriteria().list(max: grailsApplication.config.ht.homepage.last_entries.limit) {
            'in'('hub.id', hubIds)
            order('dateCreated', 'desc')
        }

        // mapping: hubSignature - hub
        def hubMap = entrs.collectEntries { Entry entry ->
            [(entry.hub.getSignature()): entry.hub]
        }

        // mapping entryId - tags
        def tagMap = entrs.collectEntries { Entry entry ->
            [(entry.id): tagService.getTagsForModel(entry)]
        }

        render(view: "homepage", model: [hubMap: hubMap, lastEntries: entrs, tagMap: tagMap])
    }

    def welcome() {
        render(view: 'welcome')
    }
}