package net.hubtangle.api.command

import groovy.transform.ToString

/**
 * Created by mkubryn on 31.12.13.
 */
@ToString
class SearchCommand {
    int page
    Long start
    Long offset
    Long limit
}
