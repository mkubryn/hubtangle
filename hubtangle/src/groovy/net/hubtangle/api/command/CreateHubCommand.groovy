package net.hubtangle.api.command

import grails.validation.Validateable
import net.hubtangle.entry.HubAccessibility

/**
 * Created by mkubryn on 13.01.14.
 */
@Validateable
class CreateHubCommand {
    Long entityId
    String title
    String description
    HubAccessibility accessibility
}
