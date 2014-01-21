package net.hubtangle

class ErrorController {

    def index() { }
	
	def error403() {
		render (view: 'error403')
	}

    def error404() {
        render (view: 'error404')
    }

    def error500() {
        render (view: 'error500')
    }
}
