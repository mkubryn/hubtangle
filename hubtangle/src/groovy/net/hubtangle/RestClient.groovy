package net.hubtangle

import grails.converters.JSON
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.Method
import net.sf.json.JSONObject
import org.apache.commons.io.IOUtils

/**
 * Client for communicating with restful services
 */
class RestClient {

    final String baseUrl

    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    def postJson(String uri, Object requestBody, Map uriParameters=[:]) {
        def http = new HTTPBuilder(enrichUrl(uri, uriParameters))

        def httpResponse = http.request(Method.POST, ContentType.JSON) { req ->
            body = requestBody

            response.success = { HttpResponseDecorator resp ->
                return resp
            }
        }

        httpResponse
    }

    def getJson(String uri, Class clazz, Map query = [:], Map uriParameters=[:]) {
        def html = get(uri, query, uriParameters)
        def json = JSONObject.fromObject(html)

        println "JSON " + json

        json.toBean(json, clazz)
    }

    def get(String uri, Map query = [:], Map uriParameters=[:]) {
        def http = new HTTPBuilder(enrichUrl(uri, uriParameters))

        StringReader responseReader = http.get(query: query, contentType: ContentType.TEXT)
        IOUtils.toString(responseReader)
    }

    private def enrichUrl(String uri, Map parameters) {
        if(!parameters) {
            parameters = Collections.EMPTY_MAP
        }

        def finalUri = baseUrl + uri

        parameters.each { String k,v ->
            finalUri = uri.replaceAll("{$k}", v)
        }

        finalUri
    }

}
