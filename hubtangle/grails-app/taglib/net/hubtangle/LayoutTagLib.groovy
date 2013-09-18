package net.hubtangle

/**
 * Various tags for layout assembling
 * 
 * @author mkubryn
 *
 */
class LayoutTagLib {
	static namespace = "layout"
	
	/**
	 * Includes specified layout template
	 */
	def include = { attrs ->
		def template = attrs.template
		
		out << render (template: "/layouts/${template}")
	}
}
