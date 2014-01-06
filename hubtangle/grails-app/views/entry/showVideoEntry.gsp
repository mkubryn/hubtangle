<%@ page import="net.hubtangle.utils.GspUtils" %>
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
                    <div class="video-container">
                        <iframe src="http://www.youtube.com/embed/${entry.getYoutubeVideoId()}"></iframe>
                    </div>
					<div class="box cf">
						<div class="entry-date"><div class="number">${entry.getDayOfMonth()}</div><div class="month">${entry.getMonthName()}</div></div>
						
						<div class="excerpt">
							<div class="entry-content">
								${GspUtils.asHtml(entry.description)}
							</div>
						</div>
						
						<div class="meta">
							<span class="format">Video</span>
							<span class="user"><a href="#">By ${entry.author.username}, </a></span>
							<span class="comments">${commentsCount} comments</span>
						</div>
					</div>
				</article>
				<!-- ENDS  Standard -->

                <layout:include template="comments/entry_comments_ajax" model="${[entry: entry, commentsCount: commentsCount]}"/>

    		</div>
    		<!-- ENDS posts list -->

			<!-- sidebar -->
        	<aside id="sidebar">
        		
        		<ul>
	        		<li class="block">
                        <layout:include template="entry/entryActions" model="[entry: entry, type: type]"/>
					</li>

	        		<li class="block">
		        		<h4>Tags</h4>
						<ul>
							<li class="cat-item"><a href="#" title="title">#red<span class="post-counter"></span></a></li>
							<li class="cat-item"><a href="#" title="title">#blue<span class="post-counter"></span></a></li>
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