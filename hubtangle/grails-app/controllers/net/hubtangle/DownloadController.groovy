package net.hubtangle

import javax.servlet.RequestDispatcher;

import org.codehaus.groovy.grails.web.util.WebUtils;

class DownloadController {

    def service() { 
		//response.sendRedirect("http://localhost:8090/open-ds/service/upload")
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("http://localhost:8090/open-ds/service/upload")
		
		dispatcher.forward(request, response)
		
	}
}
