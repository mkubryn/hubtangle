<html>
<head>
	<title>Create post</title>
    <meta name="layout" content="main"/>
    <g:javascript/>
    <r:require modules="application"/>
	<r:layoutResources />
</head>
	<body class="blog">
	
		<header>
			<div class="wrapper cf">

				<layout:include template="logo" />
				<layout:include template="navigation" />

			</div>
		</header>
		
		<!-- MAIN -->
		<div id="main">
			<div class="wrapper cf">
				<h1>Select type of post you want to create</h1>
				
				<!-- Entry type chooser form -->
				<div style="padding-top: 50px; padding-bottom: 50px">
					<form name="chooseEntryTypeForm" action="${request.contextPath}/publish/entry" method="get">
					
						<input type="hidden" name="hub" value="${hubId}"/>
					
						<!-- POST ENTRY -->
						<input type="radio" name="type" value="post" id="postEntry" onchange="${remoteFunction(
																            action:'axPostEntryDescription/' + params.hub,
																            update:'page-content', 
																            params:'jQuery(this).serialize()' )}"/>            
						<label for="postEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperpencil32.png')}" alt="Post entry" /> </label>
						
						<!-- PHOTO ENTRY -->
						<input type="radio" name="type" value="photo" id="photoEntry" onchange="${remoteFunction(
																            action:'axPhotoEntryDescription/' + params.hub,
																            update:'page-content', 
																            params:'jQuery(this).serialize()' )}"/>
						<label for="photoEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperphoto32.png')}" alt="Photo entry" /> </label>
						
						<input type="submit" value="Process">
		        	</form>
	        	</div>
	        	
	        	<!-- In this div we put entry specific creator -->
        		<div id="page-content" class="cf "></div>
					<!-- Dynamic content -->
				</div>
		</div>
		<!-- ENDS MAIN -->
	</body>
</html>