package net.hubtangle.api.command

import grails.validation.Validateable
import groovy.transform.ToString
import net.hubtangle.entry.Entry

/**
 * Created by mkubryn on 06.01.14.
 */
@ToString
@Validateable
class CreateEntryCommand {
    Long entityId
    String description
    String title
    Long dsFileId
    String content
    String url


}
