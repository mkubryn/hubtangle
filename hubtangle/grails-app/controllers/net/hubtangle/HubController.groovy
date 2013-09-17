package net.hubtangle

import grails.plugins.springsecurity.Secured;

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
	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
	def showHub() {
		def hub = Hub.get(params.hubId)
		
		if(!hub) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
			return
		}
		
		[hub: hub]
	}
	
	def saveHub() {
		
	}
	
	def deleteHub() {
		
	}
	
	def noSuchHub() {
		
	}
}
