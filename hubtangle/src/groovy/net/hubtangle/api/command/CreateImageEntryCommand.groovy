package net.hubtangle.api.command

import grails.validation.Validateable
import groovy.transform.ToString

/**
 * Created by mkubryn on 06.01.14.
 */
@ToString
@Validateable
class CreateImageEntryCommand extends CreateEntryCommand {
    Long dsFileId
}
