<html>
<head>
	<title>Create post</title>
    <meta name="layout" content="main"/>
    <r:require modules="application"/>
	<r:layoutResources />
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
			
			<!-- 
				WALIDACJA NIE DZIALA - HUJ, COS TRZEBA Z TYM ZROBIC
			 -->
			 
				<script type="text/javascript" src="${resource(dir: 'js', file: 'form-validation.js')}"></script>
			
			
				<h1>Create post entry</h1>
				<div style="padding-top: 50px; padding-bottom: 50px">
					<g:formRemote url="[controller: 'publish', action: 'renderEntryCreateForm/0']" update="page-content" name="chooseEntryTypeForm">
	
						<input type="radio" name="type" value="postEntry" checked="checked" id="postEntry" />
						<label for="postEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperpencil32.png')}" alt="Simpler" /> </label>
						
						<input type="radio" name="type" value="linkEntry" checked="checked" id="linkEntry" />
						<label for="linkEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperphoto32.png')}" alt="Simpler" /> </label>
						
		            	<g:submitButton name="Choose"/>
		        	</g:formRemote>
	        	</div>
        	
        	
        	<div id="page-content" class="cf "></div>
			</div><!-- ENDS WRAPPER -->
		</div>
		<!-- ENDS MAIN -->
		
	</body>
</html>