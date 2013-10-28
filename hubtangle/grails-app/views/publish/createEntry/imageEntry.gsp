<html>
<head>
	<title>Create post</title>
    <meta name="layout" content="main"/>
    <g:javascript/>
    <r:require modules="application,modernizr,uploadr"/>
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
				<h2><g:message code="publish.entry.image.prepare"/></h2>
				
				<div id="publish-box">
					<div id="ajaxResponse">
						<!-- Dynamic content -->
					</div>
					
					<g:formRemote id="publishForm" name="publishForm" url="[controller: 'publish', action:'saveEntry', id: hubId]" 
						update="ajaxResponse">
					
						<!-- Target entry type -->
						<input type="hidden" name="type" value="image" />

                        <layout:include template="imageUploadBox" />
					
						<p>
							<label for="title" ><g:message code="publish.entry.image.title"/></label>
							<input name="title"  id="publish-image-title" type="text" class="form-poshytip" title="Enter title" />
						</p>
						
						<p>
							<label for="description"><g:message code="publish.entry.image.description"/> </label>
							<textarea name="description" id="publish-image-description" rows="5" cols="20" class="form-poshytip" title="Enter image description"></textarea>
						</p>
				
						<input type="submit" value="Create"/>
					</g:formRemote>
					</div>
				</div>
		</div>
		<!-- ENDS MAIN -->
	</body>
</html>