package net.hubtangle

class MiscTagLib {
	static namespace = "misc"
	
	/**
	 * Redirects to homepage
	 */
	def goToHome = {
		goToPath "/home"
	}

	/**
	 * Redirects to passed path
	 */
	def goToPath = { url ->
		response.sendRedirect("${request.contextPath}" + url)
	}
}
