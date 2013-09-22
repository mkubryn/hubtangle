package net.hubtnagle

class ErrorController {

    def index() { }
	
	def error403() {
		render (view: 'error403')
	}
}
