<html>
<head>
    <title>Hubtangle - your entry point</title>
    <meta name="layout" content="main"/>
    <r:require modules="application, tabs, social"/>
	<r:layoutResources/>
</head>

<body class="home">
		<!-- HEADER -->
		<header>
			<div class="wrapper cf">
				
				<div id="logo">
					<a href="home"><img  src="img/logo.png" alt="Simpler"></a>
				</div>
				
				<!-- nav -->
				<ul id="nav" class="sf-menu">
					<li class="current-menu-item"><a href="index.html">HOME</a></li>
					<li><a href="page.html">ABOUT</a>
						<ul>
							<li><a href="page-elements.html">Elements</a></li>
							<li><a href="page-icons.html">Icons</a></li>
							<li><a href="page-typography.html">Typography</a></li>
						</ul>
					</li>
					<li><a href="portfolio.html">WORK</a></li>
					<li><a href="contact.html">CONTACT</a></li>
				</ul>
				<div id="combo-holder"></div>
				<!-- ends nav -->

				<!-- SLIDER -->				
				<div id="home-slider" class="lof-slidecontent">
					
					<div class="preload"><div></div></div>
					
					<!-- slider content --> 
					<div class="main-slider-content" >
					<ul class="sliders-wrap-inner">
					    <li>
					          <img src="img/test/c-planet.jpg" title="" alt="alt" />           
					          <div class="slider-description">
					            <h4>Where we come from</h4>
					            <p>The Earth is a tiny planet in a vast universe. The universe is made up of billions of stars and planets and enormous clouds of gas, all separated by huge empty spaces
					            <a class="link" href="#">Read more on Space Hub</a>
					            </p>
					         </div>
					    </li>
					    
					    <li>
					          <iframe width="940" height="367" src="http://www.youtube.com/embed/9pmPa_KxsAM" frameborder="0" allowfullscreen></iframe>           
					          <div class="slider-description">
					            <h4>Lorem ipsum dolor</h4>
					            <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est...
					            <a class="link" href="#">Read more </a>
					            </p>
					         </div>
					    </li>
					    
					    <li>
					          <img src="img/test/pie.jpg" title="" alt="alt" />           
					          <div class="slider-description">
					            <h4>Recent activity</h4> <h5>"Pieniny 2013 - Marcin i Aga"</h5>
					            <p>Go to hub to see recent updates..
					            <a class="link" href="#">Read more </a>
					            </p>
					         </div>
					    </li>
					    
					   
					    
					  </ul>  	
					</div>
					<!-- ENDS slider content --> 
				           
					<!-- slider nav -->
					<div class="navigator-content">
					  <div class="navigator-wrapper">
					        <ul class="navigator-wrap-inner">
					           <li><img src="img/test/c-planet_thumb.jpg" alt="alt" /></li>
					           <li><img src="img/test/gio_thumb.png" alt="alt" /></li>
					           <li><img src="img/test/pie_thumb.jpg" alt="alt" /></li>
					        </ul>
					  </div>
					  <div class="button-next">Next</div>
					  <div  class="button-previous">Previous</div>
					  <!-- REMOVED TILL FIXED <div class="button-control"><span>STOP</span></div> -->
					</div> 
					<!-- slider nav -->
					
					
				          
				 </div> 
				<!-- ENDS SLIDER -->

			</div>
		</header>
		<!-- ENDS HEADER -->
		
		<!-- MAIN -->
		<div id="main">
			<div class="wrapper cf">
			
			<div>
				<h3>Recent activity on your hubs ${hubMap.size()} + e: ${lastEntries.size()}</h3>
				<p/>
			</div>
				
			<!-- featured -->
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
							<a href="hub/${entry.hub.id}" class="thumb"><ds:img id=".test_photo.png"/></a>
							
							<figcaption>
								<a href="hub/${entry.hub.id}"><h3 class="heading">${entry.title}</h3></a>
								${entry.description}</figcaption>
						</figure>
					</g:each>
					
					
				</div><!-- ENDS Filter container -->
				
			</div>
			<!-- ENDS featured -->
			
			</div><!-- ENDS WRAPPER -->
		</div>
		<!-- ENDS MAIN -->
</body>
</html>