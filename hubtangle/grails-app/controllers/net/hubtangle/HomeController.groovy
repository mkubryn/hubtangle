package net.hubtangle

import net.hubtable.*;
import net.hubtangle.entry.Hub;

class HomeController {

    def index() {
		def hubMap = Hub.getAll().collectEntries { hub ->
			[hub.getSignature(), hub]
		}
		
		def lastEntries = getRecentEntries(hubMap)
		
		[hubMap: hubMap, lastEntries: lastEntries]
	}
	
	private getRecentEntries(hubMap) {
		def entriesList = new TreeSet()
		
		hubMap.each { signature, hub ->
			
			if(hub.entries.size()) {
				hub.entries.each {
					entriesList.add(it)
				}
			}
		}
		
		entriesList
	}
}
