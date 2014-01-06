package net.hubtangle.api.command

import groovy.transform.ToString

/**
 * Created by mkubryn on 06.01.14.
 */
@ToString
abstract class CreateEntryCommand {
    Long entryId
    String description
    String title

}
