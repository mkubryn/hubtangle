package net.hubtangle.utils

class ControllerUtils {
	
	/**
	 * Creates script redirecting client using JS
	 * @param target redirect target
	 * @return
	 */
	static def createJsRedirector(target) {
		""" <script type="text/javascript">
			<!--
			window.location = "${target}"
			//-->
			</script> """
	}
}
