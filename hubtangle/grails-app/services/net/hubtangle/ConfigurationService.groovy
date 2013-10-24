package net.hubtangle

/**
 * Configuration source for Hubtangle. It can provide 
 * configuration from different config sources
 * @author mkubryn
 *
 */
class ConfigurationService {

	/** DI of grailsApplication config source. This source gets it's config from 
	 * Config.groovy default Grails file*/	
	def grailsApplication
	
	def getProp (String key, Object defaultValue = null) {
		def val = readConfig(key)
		if(!val) {
			return defaultValue
		}
		val
	}

    def readConfig(String configProperty) {
        def confMap = grailsApplication.getFlatConfig()
        return confMap["${configProperty}"]
    }
}
