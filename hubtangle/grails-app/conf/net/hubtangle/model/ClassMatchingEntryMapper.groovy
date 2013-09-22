
package net.hubtangle.model

import org.codehaus.groovy.util.StringUtil;

/*
 * TODO One-to-one mapping of input params to model class could be very dangerous. Think about it.
 */
/**
 * Maps properties to properties to a physical hub entry object. This mapper tries to determine class from the "type" parameter.
 * @author mkubryn
 *
 */
class ClassMatchingEntryMapper {

	def map(Map props) {
		// check type
		if(!props.type) {
			throw new IllegalArgumentException('No type property provided to mapper.')
		}
		
		def clazz = matchClass(props.type)
		def instance = clazz.newInstance()

		props.each { property, value ->
			if(instance.hasProperty(property)) {
 				instance."$property" = value
			}
		}
		
		return instance
	}
	
	private Class matchClass(String type) {
		def className = "net.hubtangle.entry." + capitalizeFirstLetter(type) + "Entry"

		try {
			Class.forName(className, true, Thread.currentThread().contextClassLoader)
		} catch (e) {
			e.printStackTrace(); // TODO rm me
			throw new IllegalArgumentException("Unable to load domain class: $className ", e)
		}
	}
	
	private String capitalizeFirstLetter(String original){
		if(original.length() == 0)
			return original;
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
}
