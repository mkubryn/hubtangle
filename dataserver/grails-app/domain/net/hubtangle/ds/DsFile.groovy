package net.hubtangle.ds

/**
 * Represents a file kept in dataserver
 *
 * @author mkubryn
 */
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
