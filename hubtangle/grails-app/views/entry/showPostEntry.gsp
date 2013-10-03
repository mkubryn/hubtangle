<!doctype html> 
<html class="no-js">

	<head>
		<title>${entry.title} - Hubtangle</title>
		<meta charset="utf-8"/>
	    <meta name="layout" content="main"/>
		<meta name="viewport" content="width=device-width, initial-scale=1"/>

   		<r:require modules="application, tabs, social"/>
		<r:layoutResources/>
	
		<!-- reply move form -->
		<script src="js/moveform.js"></script>
	</head>
	
	
	<body>
	
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
			
			
				
			<!-- portfolio content-->
        	<div id="portfolio-content" class="cf">        	
				
				
					
					<!-- project box -->
				<div id="project-box" class="cf">
				
					
				
					<!-- slider -->
					<div class="project-slider">
					</div>
		        	<!-- ENDS slider -->


					<div class="info">
	        			<p><strong>Author </strong>${entry.author.username}</p>
	        			<p><strong>Date </strong> ${entry.dateCreated.format("yyyy/MM/dd HH:mm")}</p>
	        		</div>
	        		
	        		<!-- entry-content -->
	        		<div class="entry-content">
	        		
	        			<h1 class="heading">${entry.title}</h1>
	        			
	        			<div class="content">
	        				${entry.content}
	        			</div>
	        		</div>
	        		<!-- ENDS entry content -->
	        		
	        	</div>
		    	<!-- ENDS project box -->
		    	
		    	<!-- project pager -->
				<div class="project-pager cf">
					<a class="previous-project" href="#">&#8592; Previous page</a>
					<a class="next-project" href="#">Next page &#8594;</a>
				</div>
				<!-- ENDS project pager -->
					
					
				<!-- related -->
				<div class="related-projects">
					<h4 class="related-heading">Related projects</h4>
					<div class="related-list cf">
						<figure>
							<a href="#" class="thumb"><img src="img/dummies/slides/01.jpg" alt="Alt text" /></a>
							<a href="single.html" class="heading">Pellentesque habitant morbi</a>
						</figure>
						
						<figure>
							<a href="#" class="thumb"><img src="img/dummies/slides/02.jpg" alt="Alt text" /></a>
							<a href="single.html" class="heading">Pellentesque habitant morbi</a>
						</figure>
						
						<figure> 
							<a href="#" class="thumb"><img src="img/dummies/slides/03.jpg" alt="Alt text" /></a>
							<a href="single.html" class="heading">Pellentesque habitant morbi</a>
						</figure>
						
						<figure>
							<a href="#" class="thumb"><img src="img/dummies/slides/04.jpg" alt="Alt text" /></a>
							<a href="single.html" class="heading">Pellentesque habitant morbi</a>
						</figure>
						
					</div>
				</div>
				<!-- ENDS related -->
									
				
    		</div>
    		<!-- ENDS portfolio content-->
			
			
			
			</div><!-- ENDS WRAPPER -->
		</div>
		<!-- ENDS MAIN -->
		
	</body>
</html>