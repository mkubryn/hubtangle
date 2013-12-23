package net.hubtangle

class MiscTagLib {
	static namespace = "misc"

    /**
     * Creates link to a resource in current context path
     */
    def link = { attrs, body ->
        def location = attrs.loaction
        def alt = attrs.alt

        out << "<a href='${request.contextPath}/${location}' alt='${alt}' />"
        out << body()
        out << "</a>"
    }

	/**
	 * Redirects to homepage
	 */
	def goToHome = {
		goToPath "/"
	}

	/**
	 * Redirects to passed path
	 */
	def goToPath = { url ->
		response.sendRedirect("${request.contextPath}" + url)
	}

    def youtubeThumbnail = { attrs ->
        def videoId = attrs.videoId

        out << """
                <div style="position: relative; left: 0; top: 0;">
                  <img src="http://img.youtube.com/vi/${videoId}/mqdefault.jpg" style="position: relative; top: 0; left: 0;"/>
                  <img src="${request.contextPath}/img/yt-play64.png" style="position: absolute; top: 60px; left: 115px;"/>
                </div>
"""
    }
}
