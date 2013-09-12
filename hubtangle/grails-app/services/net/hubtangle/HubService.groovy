package net.hubtangle

import net.hubtangle.entry.Hub;

class HubService {

	/**
	 * Checks weather currently authenticated user has right to post on {@link Hub} of given id
	 * @param hubId hub id
	 * @return true if user has right to post on hub, false otherwise
	 */
    def canPostOnHub(hubId) {
		if(!Hub.exists(hubId)) {
			return false
		}
		
		// FIXME: check right to post on hub
		true
	}
}
