package net.hubtangle

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.transport.http.client.HttpResponseProperties;

import net.hubtangle.entry.Hub;

class HubController {

    def index() { }
	
	/**
	 * Handles show particular {@link Hub} requests
	 */
	def showHub() {
		def hubId = params["hubId"]
		def hub = Hub.get(hubId)
		
		[hub: hub]
	}
	
	def createPostEntry() {
		println "Create post entry in hub with id " + params["hubId"]
	}
	
	def saveHub() {
		
	}
	
	def deleteHub() {
		
	}
	
	def noSuchHub() {
		
	}
}
