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
				<h1><g:message code="publish.entry.type.ask"/></h1>
				
				<!-- Entry type chooser form -->
				<div style="padding-top: 50px; padding-bottom: 50px">
					<form name="chooseEntryTypeForm" action="${request.contextPath}/publish/entry" method="get">
						
						<input type="hidden" name="hub" value="${hubId}"/>


                           <!--   CONSIDER THIS (FOR MOBILE DEVICES)
                        <div style="margin-top: 30px; margin-bottom: 30px;">

                            <div>
                                <span style="padding-bottom: 10px;">
                                    <g:render template="entryTypeChoose" model="[entryType: 'post', icon: 'mono-icons/paperpencil32.png']"/>
                                </span style="padding-bottom: 10px;">
                                <span style="padding-bottom: 10px;">
                                    <g:render template="entryTypeChoose" model="[entryType: 'image', icon: 'mono-icons/paperphoto32.png']"/>
                                </span style="padding-bottom: 10px;">
                                <span style="padding-bottom: 10px;">
                                    <g:render template="entryTypeChoose" model="[entryType: 'link', icon: 'mono-icons/paperphoto32.png']"/>
                                </span style="padding-bottom: 10px;">
                                <span style="padding-bottom: 10px;">
                                    <g:render template="entryTypeChoose" model="[entryType: 'video', icon: 'mono-icons/video32.png']"/>
                                </span style="padding-bottom: 10px;">
                            </div>

                            <div style="float: right;">
                                <div class="continueButton" onclick="submit()">
                                    <img alt="continue" src="${resource(dir: 'img', file: 'mono-icons/arrowright38.png')}"
                                         style="padding-right: 20px;"/>
                                    <br> Continue
                                </div>
                            </div>
                        </div>
                               -->


						<table class="entryMenuTable">
							<tr> 
								<td>
									<!-- POST ENTRY -->
									<g:render template="entryTypeChoose" model="[entryType: 'post', icon: 'mono-icons/paperpencil32.png']"/>
								<td>
									<!-- PHOTO ENTRY -->
									<g:render template="entryTypeChoose" model="[entryType: 'image', icon: 'mono-icons/paperphoto32.png']"/>
								</td>
								<td>
									<!-- Link entry -->
									<g:render template="entryTypeChoose" model="[entryType: 'link', icon: 'mono-icons/paperphoto32.png']"/>
								</td>
								<td>
									<!-- Video entry -->
									<g:render template="entryTypeChoose" model="[entryType: 'video', icon: 'mono-icons/video32.png']"/>
								</td>
							</tr>
							<tr>
								<th colspan style="padding-bottom: 10px;"="4" style="text-align: right;">
									<div class="continueButton" onclick="submit()">
										<img alt="continue" src="${resource(dir: 'img', file: 'mono-icons/arrowright38.png')}" 
											style="padding-right: 20px;"/>
										<br> Continue
									</div>
				        		</th>
				        	</tr>
		        		</table>
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