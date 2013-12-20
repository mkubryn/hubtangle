package net.hubtangle.helpers

import org.codehaus.groovy.grails.web.pages.discovery.GrailsConventionGroovyPageLocator;

class RequestHelper {
	
	static def asLong(value) {
		try {
			Long.valueOf(value)
		} catch (Exception e) {
			null
		}
	}

    static def asInteger(value) {
        try {
            Integer.valueOf(value)
        } catch (Exception e) {
            null
        }
    }
}
