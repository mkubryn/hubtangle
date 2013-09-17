<html>
<head>
    <title>Hubtangle - your entry point</title>
    <meta name="layout" content="main"/>
    <r:require modules="application"/>
	<r:layoutResources/>
</head>

<body class="home">
	<!-- HEADER -->
	<header>
		<div class="wrapper cf">
			
			<div id="logo">
				<a href="../home"><img  src="${resource(dir: 'img', file: 'logo.png')}" alt="Simpler"></a>
			</div>
			
			<!-- nav -->
			<ul id="nav" class="sf-menu">
				<li class="current-menu-item"><a href="index.html">HOME</a></li>
				<li><a href="page.html">ABOUT</a>
					<ul>
						<li><a href="page-elements.html">Elements</a></li>
						<li><a href="page-icons.html">Icons</a></li>
						<li><a href="page-typography.html">Typography</a></li>
					</ul>
				</li>
				<li><a href="portfolio.html">WORK</a></li>
				<li><a href="contact.html">CONTACT</a></li>
				<li class="loginButton">
					<sec:ifNotLoggedIn>
						<g:link controller="login" action="auth">Login</g:link>
					</sec:ifNotLoggedIn> <sec:ifAllGranted roles="ROLE_USER">
						<g:link controller="logout">Logout</g:link>
					</sec:ifAllGranted>
				</li>
			</ul>
		</div>
	</header>
	<!-- ENDS HEADER -->
	
	<!-- MAIN -->
	<div id="main">
		<div id='login'>
			<div class='inner'>
				<div class='fheader'><g:message code="springSecurity.login.header"/></div>
		
				<g:if test='${flash.message}'>
					<div class='login_message'>${flash.message}</div>
				</g:if>
		
				<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
					<p>
						<label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
						<input type='text' class='text_' name='j_username' id='username'/>
					</p>
		
					<p>
						<label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
						<input type='password' class='text_' name='j_password' id='password'/>
					</p>
		
					<p id="remember_me_holder">
						<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
						<label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
					</p>
		
					<p>
						<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
					</p>
				</form>
			</div>
		</div>
	</div>
	<!-- ENDS MAIN -->
		
	<script type='text/javascript'>
		<!--
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
		// -->
	</script>
</body>
</html>
