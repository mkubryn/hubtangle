<html>
<head>
    <title>Hubtangle - your entry point</title>
    <meta name="layout" content="main"/>
    <r:require modules="application, tabs, social"/>
	<r:layoutResources/>
</head>

<body class="home">
	<header>
		<div class="wrapper cf">
			
			<layout:include template="logo" />
			<layout:include template="navigation" />
			<layout:include template="homepage/slider"/>

		</div>
	</header>
	
	<!-- MAIN -->
	<div id="main">
		<div class="wrapper cf">
		
		<div id="recentActivityLabel">
			<g:message code="homepage.recentActivity"/>
		</div>

		<!-- recent activity -->
		<div class="home-featured">
			<ul id="filter-buttons">
				<li><a href="#" data-filter="*" class="selected">Show all</a></li>
				
				<g:each in="${hubMap.keySet()}" var="signature">
					<li><a href="#" data-filter=".${signature}">${hubMap[signature].name}</a></li>
				</g:each>
				
			</ul>

			<!-- Filter container -->
			<div id="filter-container" class="cf">
				
				<g:each in="${lastEntries}" var="entry"> 
					<figure class="${entry.hub.getSignature()}">
						<a href="hub/${entry.hub.id}" class="thumb">

                            <g:if test="${entry instanceof net.hubtangle.entry.ImageEntry}" >
                                <ds:img id="${entry.dsFileId}"/>
                            </g:if>


						</a>
						
						<figcaption>
							<a href="hub/${entry.hub.id}#highlight-${entry.id}"><h3 class="heading">${entry.title}</h3></a>
							${entry.description}
						</figcaption>
					</figure>
				</g:each>
			</div>
			<!-- ENDS Filter container -->
		</div>
		<!-- ENDS featured -->
		</div>
		<!-- ENDS WRAPPER -->
	</div>       _GrailsBootstrap
	<!-- ENDS MAIN -->
</body>
</html>