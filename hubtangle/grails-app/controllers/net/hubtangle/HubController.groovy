package net.hubtangle

import grails.plugins.springsecurity.Secured;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.transport.http.client.HttpResponseProperties;
import static net.hubtangle.helpers.ParamsHelper.*;

import net.hubtangle.entry.Hub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HubController {

	Logger log = LoggerFactory.getLogger(HubController.class)
		
    def index() { }
	
	/**
	 * Handles show particular {@link Hub} requests
	 */
	@Secured(['ROLE_USER'])
	def hub() {
		def hub = Hub.get(params.hubId)
		
		if(!hub) {
			log.info("[SEC] Attempt to obtain not existing hub with id={}", params.hubId)
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
			return
		}
		
		[hub: hub]
	}
}
