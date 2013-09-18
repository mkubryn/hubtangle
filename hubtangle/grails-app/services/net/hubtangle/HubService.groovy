package net.hubtangle

import net.hubtangle.entry.Hub;

class HubService {

	def springSecurityService
	
	/**
	 * Checks weather currently authenticated user has right to post on {@link Hub} of given id
	 * @param hubId hub id
	 * @return true if user has right to post on hub, false otherwise
	 */
    def canPostOnHub(hubId) {
		def currentUser = springSecurityService.getCurrentUser()
		if(!currentUser) {
			return false
		}
		
		def hub = Hub.get(hubId)
		if(hub == null) {
			return false
		}
		
		return hub.creator.id.equals(currentUser.id)
	}
}
