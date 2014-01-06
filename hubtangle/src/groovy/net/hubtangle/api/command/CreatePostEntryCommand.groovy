package net.hubtangle.api.command

import grails.validation.Validateable

/**
 * Created by mkubryn on 06.01.14.
 */
@Validateable
class CreatePostEntryCommand extends CreateEntryCommand {
    String content
    Long dsFileId
}
