package net.hubtangle.entry

/**
 * Represents a quote entry.
 * @author mkubryn
 *
 */
class QuoteEntry extends Entry {
	
	/**
	 * Quote text
	 */
	String text
	
	/**
	 * Quote author
	 */
	String quoteAuthor
	
    static constraints = {
    }
	
	
	def getRenderTemplateName() {
		"quoteEntry"
	}
}
