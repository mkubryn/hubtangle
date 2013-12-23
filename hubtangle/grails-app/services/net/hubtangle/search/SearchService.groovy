package net.hubtangle.search

import grails.converters.JSON
import grails.transaction.Transactional
import net.hubtangle.RestClient
import net.hubtangle.utils.SearchUtils
import net.sf.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean

import javax.servlet.http.HttpServletResponse

@Transactional
class SearchService implements InitializingBean {
    private static log = LoggerFactory.getLogger(this)

    static rabbitQueue = 'searchIndexQueue'

    def configurationService
    RestClient restClient

    @Override
    void afterPropertiesSet() throws Exception {
        restClient = new RestClient(getSolrUri())
    }

    def search(String query, int maxResults=10, int offset=0) {

        def result = restClient.get('/select', [q: query, wt: 'json', rows: maxResults, start: offset, indent: true])
        def json = JSONObject.fromObject(result)
        def docs = json.response.docs

        log.debug "Solr response: '$result'"
        log.debug "Solr serach query: '$query' docs: '$docs'"

        docs
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