package net.hubtangle.helper

import net.hubtangle.entry.Hub
import net.hubtangle.entry.ImageEntry
import net.hubtangle.entry.LinkEntry
import net.hubtangle.entry.PostEntry
import net.hubtangle.entry.QuoteEntry
import net.hubtangle.entry.VideoEntry
import net.hubtangle.user.HUser

/**
 * Business logic for creating links
 *
 * Created by mkubryn on 22.12.13.
 */
class LinkHelper {
    static def createLink(String entityClassName, entityId) {
        switch(entityClassName) {
            case LinkEntry.class.name:
                return "link/$entityId"
            case PostEntry.class.name:
                return "article/$entityId"
            case ImageEntry.class.name:
                return "image/$entityId"
            case VideoEntry.class.name:
                return "video/$entityId"
            case Hub.class.name:
                return "hub/$entityId"
            case HUser.class.name:
                return "user/$entityId"
        }

        return null
    }

    static def createLink(searchableDoc) {
        createLink(searchableDoc.clazz[0], searchableDoc.id)
    }
}