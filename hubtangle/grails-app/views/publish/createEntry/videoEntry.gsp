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
				<h2><g:message code="publish.entry.video.prepare"/></h2>
				
				<div id="publish-box">
					<div id="createVideoRespone">
						<!-- Dynamic content -->
					</div>
					
					<g:formRemote id="publishForm" name="publishForm" url="[controller: 'publish', action:'saveVideoEntry', id: hubId]"
						update="createVideoRespone" >

                        <input type="hidden" name="entityId" value="${command.entityId}" />

						<p>
							<label for="publish-video-title" ><g:message code="publish.entry.video.title"/></label>
							<input name="title"  id="publish-video-title" type="text" class="form-poshytip"
                                   title="Enter title" value="${command.title}"/>
						</p>

						<p>
							<label for="publish-video-description"><g:message code="publish.video.description"/> </label>
							<input name="description" id="publish-video-description" type="text" class="form-poshytip"
                                   title="Enter video description" value="${command.description}"/>
						</p>

                        <p>
                            <label for="publish-video-link" ><g:message code="publish.video.link"/></label>
                            <input name="url"  id="publish-video-link" type="text" class="form-poshytip"
                                   title="Enter youtube link" value="${command.url}"/>
                        </p>

						<input type="submit" value="Create"/>
					</g:formRemote>
				</div>
			</div>
		</div>
		<!-- ENDS MAIN -->
	</body>
</html>