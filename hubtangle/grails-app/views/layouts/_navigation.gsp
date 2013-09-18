
<!-- nav -->
<ul id="nav" class="sf-menu">
	<li class="current-menu-item"><a href="index.html">HOME</a></li>
	<li><a href="page.html">ABOUT</a>
		<ul>
			<li><a href="page-elements.html">Elements</a></li>
			<li><a href="page-icons.html">Icons</a></li>
			<li><a href="page-typography.html">Typography</a></li>
		</ul></li>
	<li><a href="portfolio.html">WORK</a></li>
	<li><a href="contact.html">CONTACT</a></li>
	<li class="loginButton">
	
	<sec:ifNotLoggedIn>
			<g:link controller="login" action="auth">Login</g:link>
		</sec:ifNotLoggedIn> <sec:ifAllGranted roles="ROLE_USER">
			<g:link controller="logout">Logout</g:link>
	</sec:ifAllGranted></li>
</ul>
<div id="combo-holder"></div>
<!-- ends nav -->