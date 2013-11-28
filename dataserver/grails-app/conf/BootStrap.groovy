import net.hubtangle.ds.DsFile

class BootStrap {

    def init = { servletContext ->
        if(DsFile.getAll().size() > 0) {
            return
        }

        (1..13).each { i ->
            def file = new DsFile(size: 1000, contentType: "image/jpg", location: "bootstrap/${i}.jpg", name: i.toString())
            file.save(failOnError: true)
        }
    }
    def destroy = {
    }
}
