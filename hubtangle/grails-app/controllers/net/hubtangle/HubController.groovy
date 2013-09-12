package net.hubtangle

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.transport.http.client.HttpResponseProperties;
import static net.hubtangle.helpers.ParamsHelper.*;

import net.hubtangle.entry.Hub;

class HubController {

    def index() { }
	
	/**
	 * Handles show particular {@link Hub} requests
	 */
	def showHub() {
		def hub = Hub.get(params.hubId)
		
		[hub: hub]
	}
	
	def createPostEntry() {
		println "Create post entry in hub with id " + params.hubId
	}
	
	def saveHub() {
		
	}
	
	def deleteHub() {
		
	}
	
	def noSuchHub() {
		
	}
}
