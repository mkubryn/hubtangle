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
				<h1>Create entry for hub ${params.hub}.</h1>
				
				<!-- Entry type chooser form -->
				<div style="padding-top: 50px; padding-bottom: 50px">
					<form name="chooseEntryTypeForm">
					
						<!-- POST ENTRY -->
						<input type="radio" name="type" value="postEntry" id="postEntry" onchange="${remoteFunction(
																            action:'renderEntryCreateForm/' + params.hub,
																            update:'page-content', 
																            params:'jQuery(this).serialize()' )}"/>            
						<label for="postEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperpencil32.png')}" alt="Post entry" /> </label>
						
						<!-- PHOTO ENTRY -->
						<input type="radio" name="type" value="photoEntry" id="photoEntry" onchange="${remoteFunction(
																            action:'renderEntryCreateForm/' + params.hub,
																            update:'page-content', 
																            params:'jQuery(this).serialize()' )}"/>
						<label for="photoEntry"> <img src="${resource(dir: 'img', file: 'mono-icons/paperphoto32.png')}" alt="Photo entry" /> </label>
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