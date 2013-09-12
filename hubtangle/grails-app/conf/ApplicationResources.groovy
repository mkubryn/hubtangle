// TODO better categorization on resources
modules = {
	base {
		resource url: 'js/jquery-1.7.1.min.js', disposition: 'head'
		resource url: 'js/custom.js', disposition: 'head'
		resource url: 'js/application.js', disposition: 'head'
		resource url: 'css/style.css', disposition: 'head'
		
		resource url: 'css/skin.css', disposition: 'head'
		resource url: 'js/modernizr.js', disposition: 'head'
		resource url: 'css/lessframework.css', disposition: 'head'
		
		resource url: 'js/poshytip-1.1/src/tip-twitter/tip-twitter.css', disposition: 'head'
		resource url: 'js/poshytip-1.1/src/tip-yellowsimple/tip-yellowsimple.css', disposition: 'head'
		resource url: 'js/poshytip-1.1/src/jquery.poshytip.min.js', disposition: 'head'
		
		resource url: 'js/prettyPhoto/js/jquery.prettyPhoto.js', disposition: 'head'
		resource url: 'js/prettyPhoto/css/prettyPhoto.css', disposition: 'head'
		
		resource url: 'css/superfish.css', disposition: 'head'
		resource url: 'js/superfish-1.4.8/js/hoverIntent.js', disposition: 'head'
	}
	
	tabs {
		dependsOn 'base'
		resource url: 'js/tabs.js', disposition: 'head'
		resource url: 'js/css3-mediaqueries.js', disposition: 'head'
		resource url: 'js/jquery.columnizer.min.js', disposition: 'head'
		resource url: 'js/jquery.isotope.min.js', disposition: 'head'
		resource url: 'js/jquery.easing.js', disposition: 'head'
	}
	
	social {
		resource url: 'css/jquery.tweet.css', disposition: 'head'
		resource url: 'js/tweet/jquery.tweet.js', disposition: 'head'
	}
	
    application {
		dependsOn 'tabs', 'social'
		resource url: 'js/lof-slider.js', disposition: 'head'
		resource url: 'js/superfish-1.4.8/js/superfish.js', disposition: 'head'
		resource url: 'js/superfish-1.4.8/js/supersubs.js', disposition: 'head'
		resource url: 'js/jquery.jcarousel.min.js', disposition: 'head'
		resource url: 'css/carousel.css', disposition: 'head'
		resource url: 'css/lof-slider.css', disposition: 'head'

		resource url: 'css/flexslider.css', disposition: 'head'
		resource url: 'js/jquery.flexslider.js', disposition: 'head'
    }
}