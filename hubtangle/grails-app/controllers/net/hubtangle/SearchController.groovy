package net.hubtangle

import net.hubtangle.api.search.SearchGroup
import net.hubtangle.api.search.SearchGroupResolver
import net.hubtangle.helper.LinkHelper

class SearchController {

    def searchService

    /**
     * Handles search requests
     */
    def search() {
        def query = params.query

        def docs = searchService.search(query);
        def types = docs?.groupBy { doc -> SearchGroupResolver.getSearchGroupForClassname(doc.clazz[0]) }

        render (view: 'searchResults', model: [docs: docs, query: query, types: types])
    }
}
