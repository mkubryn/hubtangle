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
				<layout:include template="tinymce" />

			</div>
		</header>
		
		<!-- MAIN -->
		<div id="main">
			<div class="wrapper cf">
				<h2><g:message code="publish.entry.post.prepare"/></h2>
				
				<div id="publish-box">
					<div id="createPostRespone">
						<!-- Dynamic content -->
					</div>
					
					<g:formRemote id="publishForm" name="publishForm" url="[controller: 'publish', action:'saveEntry', id: hubId]" 
						update="createPostRespone" >
					
						<!-- Target entry type -->
						<input type="hidden" name="type" value="link" />
					
						<p>
							<label for="title" ><g:message code="publish.entry.post.title"/></label>
							<input name="title"  id="publish-post-title" type="text" class="form-poshytip" title="Enter title" />
						</p>

						<p>
							<label for="description"><g:message code="publish.entry.post.description"/> </label>
							<input name="description" id="publish-post-description" type="text" class="form-poshytip" title="Enter description"></input>
						</p>

                        <p>
                            <label for="link" ><g:message code="publish.entry.link"/></label>
                            <input name="url"  id="publish-post-link" type="text" class="form-poshytip" title="Enter link" />
                        </p>

						<input type="submit" value="Create" onclick="submitForm()"/>
					</g:formRemote>
				</div>
			</div>
		</div>
		<!-- ENDS MAIN -->
	</body>
</html>