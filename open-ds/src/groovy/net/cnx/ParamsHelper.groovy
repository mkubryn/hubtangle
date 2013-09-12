package net.cnx

class ParamsHelper {
	
	static def asLong(value) {
		try {
			Long.valueOf(value)
		} catch (Exception e) {
			null
		}
	}
}
