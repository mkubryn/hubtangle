package net.hubtangle.api.command

import grails.validation.Validateable

/**
 * Created by mkubryn on 06.01.14.
 */
@Validateable
class CreateLinkEntryCommand extends CreateEntryCommand {
    String url
}
