package net.hubtangle.utils
/**
 * Created by mkubryn on 22.12.13.
 */
class GspUtils {
    static def asHtml(String plainText) {
        plainText?.replaceAll('\n', '<br/>')
    }
}
