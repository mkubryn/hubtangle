package net.hubtangle

class ErrorController {

    def index() { }
	
	def error403() {
		render (view: 'error403')
	}
}
