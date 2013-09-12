<html>
<head>
    <meta name="layout" content="main"/>
    
    <!-- JS -->
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-1.7.1.min.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'custom.js')}"></script>
		
		<script type="text/javascript" src="${resource(dir: 'js', file: 'tabs.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'css3-mediaqueries.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.columnizer.min.js')}"></script>
		
		<!-- Isotope -->
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.isotope.min.js')}"></script>

		<!-- Lof slider -->
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.easing.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'lof-slider.js')}"></script>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'lof-slider.css')}" media="all"  /> 
		<!-- ENDS slider -->
		
		<!-- Tweet -->
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.tweet.css')}" media="all"  /> 
		<script type="text/javascript" src="${resource(dir: 'js', file: 'tweet/jquery.tweet.js')}"></script>
		<!-- ENDS Tweet -->

		<!-- superfish -->
		<link rel="stylesheet" media="screen" href="${resource(dir: 'css', file: 'superfish.css')}" />
		<script type="text/javascript" src="${resource(dir: 'js', file: 'superfish-1.4.8/js/hoverIntent.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'superfish-1.4.8/js/superfish.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'superfish-1.4.8/js/supersubs.js')}"></script>
		<!-- ENDS superfish -->

		<!-- prettyPhoto -->
		<script type="text/javascript" src="${resource(dir: 'js', file: 'prettyPhoto/js/jquery.prettyPhoto.js')}"></script>
		<link rel="stylesheet" href="${resource(dir: 'js', file: 'prettyPhoto/css/prettyPhoto.css')}"  media="screen" />
		<!-- ENDS prettyPhoto -->

		<!-- poshytip -->
		<link rel="stylesheet" href="${resource(dir: 'js', file: 'poshytip-1.1/src/tip-twitter/tip-twitter.css')}"  />
		<link rel="stylesheet" href="${resource(dir: 'js', file: 'poshytip-1.1/src/tip-yellowsimple/tip-yellowsimple.css')}" />
		<script type="text/javascript" src="${resource(dir: 'js', file: 'poshytip-1.1/src/jquery.poshytip.min.js')}"></script>
		<!-- ENDS poshytip -->

		<!-- GOOGLE FONTS -->
		<link href='http://fonts.googleapis.com/css?family=Voltaire' rel='stylesheet' type='text/css'>

		<!-- modernizr -->
		<script src="${resource(dir: 'js', file: 'modernizr.js')}"></script>
		
		<!-- SKIN -->
		<link rel="stylesheet" media="all" href="${resource(dir: 'css', file: 'skin.css')}"/>
		
		<!-- Less framework -->
		<link rel="stylesheet" media="all" href="${resource(dir: 'css', file: 'lessframework.css')}"/>

		<!-- flexslider -->
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'flexslider.css')}" >
		<script src="${resource(dir: 'js', file: 'jquery.flexslider.js')}"></script>
		
		<!-- jplayer -->
		<link href="${resource(dir: 'player-skin', file: 'jplayer-black-and-yellow.css')}" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.jplayer.min.js')}"></script>
		
 
		<r:layoutResources />
		
		<title>Create new post entry</title>

</head>
	<body class="blog">
	
		<!-- HEADER -->
		<header>
			<div class="wrapper cf">
				
				<div id="logo">
					<a href="../home"><img  src="${resource(dir: 'img', file: 'logo.png')}" alt="Simpler"></a>
				</div>
				
				<!-- nav -->
				<ul id="nav" class="sf-menu">
					<li><a href="/hubtangle/home"><span>HOME</span></a></li>
					<li class="current-menu-item"><a href="blog.html">HUB</a></li>
					<li><a href="page.html">ABOUT</a>
						<ul>
							<li><a href="page-elements.html">Elements</a></li>
							<li><a href="page-icons.html">Icons</a></li>
							<li><a href="page-typography.html">Typography</a></li>
						</ul>
					</li>
					<li><a href="portfolio.html">WORK</a></li>
					<li><a href="contact.html">CONTACT</a></li>
				</ul>
				<div id="combo-holder"></div>
				<!-- ends nav -->

			</div>
		</header>
		<!-- ENDS HEADER -->
		
		<!-- MAIN -->
		<div id="main">
			<div class="wrapper cf">
			
			
			<h1>Create post entry</h1>
			<div style="padding-top: 50px; padding-bottom: 50px">
				<g:formRemote url="[controller: 'publish', action: 'renderEntryCreateForm/0']" update="panell" name="chooseEntryTypeForm">

					<input type="radio" name="type" value="postEntry" checked="checked" id="postEntry" />
					<label for="postEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperpencil32.png')}" alt="Simpler" /> </label>
					
					<input type="radio" name="type" value="linkEntry" checked="checked" id="linkEntry" />
					<label for="linkEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperphoto32.png')}" alt="Simpler" /> </label>
					
	            	<g:submitButton name="Choose"/>
	        	</g:formRemote>
        	</div>
        	
        	
        	<div id="panell"></div>
			
						
						
			</div><!-- ENDS WRAPPER -->
		</div>
		<!-- ENDS MAIN -->
		
	</body>
</html>