package net.hubtangle.api.search

import net.hubtangle.entry.Comment
import net.hubtangle.entry.Hub
import net.hubtangle.user.HUser

/**
 * Holds groups for serachable objects
 * Created by mkubryn on 21.12.13.
 */
class SearchGroupResolver {

    static def getSearchGroupForClassname(String clazz) {
        if(clazz.endsWith('Entry')) {
            return SearchGroup.ENTRIES
        }

        if(clazz == Hub.class.name) {
            return SearchGroup.HUBS
        }

        if(clazz == HUser.class.name) {
            return SearchGroup.HUSERS
        }
    }
}
