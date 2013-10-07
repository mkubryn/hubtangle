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
	 * Includes specified layout template.
	 */
	def include = { attrs ->
		def template = attrs.template
		
		out << render (template: "/layouts/${template}")
	}
	
	protected assertAttribute(String name, attrs, String tag) {
		if (!attrs.containsKey(name)) {
			throwTagError "Tag [$tag] is missing required attribute [$name]"
		}
		attrs.remove name
	}
}
