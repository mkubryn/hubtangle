package net.hubtangle

import net.hubtangle.api.command.SearchCommand

class UserController {

    def userService

    def memberships(SearchCommand criteria) {

        println criteria

        //def hubs = userService.getHubMemberships()
    }
}
