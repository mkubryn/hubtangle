package net.hubtangle.helpers

class ParamsHelper {
	
	static def asLong(value) {
		try {
			Long.valueOf(value)
		} catch (Exception e) {
			null
		}
	}
}
