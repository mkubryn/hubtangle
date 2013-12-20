package net.hubtangle.search

import grails.converters.JSON
import grails.transaction.Transactional
import net.hubtangle.RestClient
import net.hubtangle.api.Indexed
import net.hubtangle.utils.AnnotationUtils
import net.hubtangle.utils.SearchUtils
import org.apache.http.HttpResponse
import org.springframework.beans.factory.InitializingBean

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletResponse
import java.lang.annotation.Annotation
import java.lang.reflect.Field

@Transactional
class SearchService implements InitializingBean {

    static rabbitQueue = 'searchIndexQueue'

    def configurationService
    def restClient

    @Override
    void afterPropertiesSet() throws Exception {
        restClient = new RestClient(getSolrUri())
    }

    def index(model) {
        log.debug("Indexing: ${model}")

        def documentJson = prepareSolrDocumentJson(model)
        if(!documentJson) {
            // nothing to index
            return
        }

        log.debug("Sending document to Solr: $documentJson")

        // index is very fast (~1ms)
        def indexResponse = restClient.postJson('/update', "[$documentJson]")

        // commit is veery expensive (~70ms)
        def commitResponse = restClient.get('/update', [commit: 'true'])

        log.debug("Solr commit response: ${commitResponse}")

        /**
         * TODO consider not commiting after each index attempt
         * maybe job triggering commit in every 5 min would be better?
         * Think about autocommit etc.
         */

        indexResponse.getStatus() == HttpServletResponse.SC_OK
    }

    private prepareSolrDocumentJson(model) {
        def indexedFields = SearchUtils.getSeachableProperties(model)
        if(indexedFields.size() == 0) {
            return null
        }

        indexedFields['id'] = model.id as String
        indexedFields['clazz'] = model.class.name

        def json = indexedFields as JSON
        json as String
    }

    /**
     * Handles message from 'searchIndexQueue' queue and indexes it's payload
     * @param message
     */
    void handleMessage(message) {
        index(message)
    }

    private getSolrUri() {
        configurationService.getProp('ht.cluster.solr.uri')
    }
}