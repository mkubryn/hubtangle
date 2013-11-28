<html>
<head>
	<title>${hub.name}</title>
    <meta name="layout" content="main"/>
    <r:require modules="application"/>
	<r:layoutResources />
</head>
	<body class="blog">
		<!-- HEADER -->
		<header>
			<div class="wrapper cf">
				
				<layout:include template="logo" />
				<layout:include template="navigation" />

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
                        <div id="highlight-${e.id}"
		        		    <g:render template="${e.getRenderTemplateName()}" bean="${e}" var="entry" />
                        </div>
	        		</g:each>
					
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
		        		<h4>${hub.name }</h4>
						${hub.description }
	        		</li>
	        		
	        		<li class="block">
	        			<h4>Actions</h4>
						<sec:ifUserCanPostOnHub hubId="${hub.id}">
	        				<div class="hubFeatureButton">
			        			<a href="/hubtangle/publish/entry?hub=${hub.id}">
				        			<img alt="createpost" src="${resource(dir: 'img', file: 'mono-icons/linedpaperplus32.png')}"/> 
									Add new entry
			        			</a>
	        				</div>
						</sec:ifUserCanPostOnHub>
						<div class="hubFeatureButton">
			        		<a href="#">
				        		<img alt="subscribeRss" src="${resource(dir: 'img', file: 'mono-icons/rss32.png')}"/> 
								Subscribe RSS
			        		</a>
	        			</div>
	        			<h4></h4>
	        		</li>

	        		<li class="block">
		        		<h4>Moderators</h4>
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