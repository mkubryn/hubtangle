<html>
<head>
	<title>${hub.name }</title>
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
			
				<!-- posts list -->
	        	<div id="posts-list" class="cf">        	
	        		
	        		<!-- Render each entry -->
	        		<g:each in="${hub.entries}" var="e">
		        		<g:render template="${e.getRenderTemplateName()}" bean="${e}" var="entry" />
	        		</g:each>
	        		
	        		<%--
	        			<g:render template="linkEntry" collection="${hub.entries}" var="entry"></g:render>
	        			<g:render template="quoteEntry" collection="${hub.entries}" var="entry"></g:render>
		        		<g:render template="imageEntry" collection="${hub.entries}" var="entry"></g:render>
		        		<g:render template="videoEntry" collection="${hub.entries}" var="entry"></g:render>
	        			<g:render template="postEntry" collection="${hub.entries}" var="entry"></g:render>
	        		--%>
					
					<!-- page-navigation -->
					<div class="page-navigation cf">
						<div class="nav-next"><a href="#">&#8592; Older Entries </a></div>
						<div class="nav-previous"><a href="#">Newer Entries &#8594;</a></div>
					</div>
					<!--ENDS page-navigation -->
					
				
        		</div>
        		<!-- ENDS posts list -->
			
				<!-- sidebar -->
        	<aside id="sidebar">
        		
        		<ul>
	        		<li class="block">
	        		
	        			<a href="/publish/entry?hub=${hub.id}">
	        				<h4><img alt="createpost" src="${resource(dir: 'img', file: 'mono-icons/linedpaperplus32.png')}">  Post new entry</h4>
	        			</a>
	        			
		        		<h4>${hub.name }</h4>
						${hub.description }
	        		</li>
	        		
	        		<li class="block">
		        		<h4>Categories</h4>
						<ul>
							<li class="cat-item"><a href="#" title="title">Film and video<span class="post-counter"> (2)</span></a></li>
							<li class="cat-item"><a href="#" title="title">Print<span class="post-counter"> (2)</span></a></li>
							<li class="cat-item"><a href="#" title="title">Photo and lomo<span class="post-counter"> (2)</span></a></li>
							<li class="cat-item"><a href="#" title="title">Habitant morbi<span class="post-counter"> (2)</span></a></li>
							<li class="cat-item"><a href="#" title="title">Film and video<span class="post-counter"> (2)</span></a></li>
							<li class="cat-item"><a href="#" title="title">Print<span class="post-counter"> (2)</span></a></li>
							<li class="cat-item"><a href="#" title="title">Photo and lomo<span class="post-counter"> (2)</span></a></li>
							<li class="cat-item"><a href="#" title="title">Habitant morbi<span class="post-counter"> (2)</span></a></li>
						</ul>
	        		</li>
	        		
	        		<li class="block">
		        		<h4>Hub moderators</h4>
						<ul>
							<li class="cat-item"><a href="#" title="title">Marcin Kubryn</a></li>
							<li class="cat-item"><a href="#" title="title">Agnieszka S.</a></li>
						</ul>
	        		</li>
        		
        		</ul>
        		
        	</aside>
			<!-- ENDS sidebar -->
			
			</div><!-- ENDS WRAPPER -->
		</div>
		<!-- ENDS MAIN -->
	</body>
</html>