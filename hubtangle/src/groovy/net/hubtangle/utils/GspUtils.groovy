package net.hubtangle.utils

import net.hubtangle.entry.Hub

/**
 * Created by mkubryn on 22.12.13.
 */
class GspUtils {
    static def asHtml(String plainText) {
        plainText?.replaceAll('\n', '<br/>')
    }
}
