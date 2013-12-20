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
	
	<body class="single">
		<header>
			<div class="wrapper cf">

				<layout:include template="logo" />
				<layout:include template="navigation" />
	
			</div>
		</header>
		
		<!-- MAIN -->
		<div id="main">
			<div class="wrapper cf">

			<!-- masthead -->
			<div class="masthead cf">
				${entry.title}
			</div>
			<!-- ENDS masthead -->
			
			<!-- posts list -->
        	<div id="posts-list" class="cf">        	

				<!-- Standard -->
				<article class="format-standard">
					<div class="feature-image">
						<ds:img id="${entry.dsFileId}"/>
					</div>
					<div class="box cf">
						<div class="entry-date"><div class="number">${entry.getDayOfMonth()}</div><div class="month">${entry.getMonthName()}</div></div>
						
						<div class="excerpt">
							<div class="entry-content">
								${entry.description}
							</div>
						</div>
						
						<div class="meta">
							<span class="format">Post</span>
							<span class="user"><a href="#">By ${entry.author.username}, </a></span>
							<span class="comments">16 comments</span>
							<span class="tags"><a href="#">red</a>, <a href="#">cyan</a>, <a href="#">white</a>, <a href="#">blue</a></span>
						</div>
					</div>
				</article>
				<!-- ENDS  Standard -->
					
				<layout:include template="comments/comments_ajax"/>


    		</div>
    		<!-- ENDS posts list -->

			<!-- sidebar -->
        	<aside id="sidebar">
        		
        		<ul>
	        		<li class="block">
		        		<h4>Image details:</h4>
							${entry.description}
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
        		</ul>
        		
        	</aside>
			<!-- ENDS sidebar -->

			</div><!-- ENDS WRAPPER -->
		</div>
		<!-- ENDS MAIN -->
		
	</body>
	
	
	
</html>