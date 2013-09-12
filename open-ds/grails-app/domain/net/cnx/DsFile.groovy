package net.cnx

class DsFile {

	String location
	String name
	String contentType
	Long size
	
    static constraints = {
    }

	@Override
	public String toString() {
		return "DSFile [location=" + location + ", name=" + name
				+ ", contentType=" + contentType + ", size=" + size + "]";
	}	
}
