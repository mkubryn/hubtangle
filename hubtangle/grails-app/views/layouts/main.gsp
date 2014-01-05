<!doctype html>
<html class="no-js">
	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		
		<g:layoutHead/>
		 
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		
		<!-- Google fonts -->	
		<link href='http://fonts.googleapis.com/css?family=Voltaire' rel='stylesheet' type='text/css'>
		
		<!-- not required here so far
			<r:require modules="application"/> 
		-->
		
		<r:layoutResources />
        <title>Hubtangle.</title>
	</head>

		<body class="${pageProperty( name:'body.class' )}">
			<!-- Flash -->
            <g:if test="${flash.message}">
                <div id="flashBarWrapper">
                    <div id="flashBar">
                        <span>${flash.message}</span>
                        <span id="closeHint"><a href="#">close</a></span>
                    </div>
                </div>
            </g:if>

            <script>
                $('#flashBar').click(function() {
                    $('#flashBar').css("display","none");
                });
            </script>

			<!-- Here will be put the main content of subsites -->
			<g:layoutBody />
			<r:layoutResources />
		</body>


		<!-- FOOTER -->
		<footer>
			<div class="wrapper cf">

				<!-- widgets -->
				<ul  class="widget-cols cf">
					<li class="first-col">
						
						<div class="widget-block">
							<h4>Recommnded to You</h4>

						</div>
					</li>
					
					<li class="second-col">

						<div class="widget-block">
							<h4>About us</h4>
							<p>Folder it's completely free this means you don't have to pay anything <a href="http://luiszuno.com/blog/license" tar >read license</a>.</p>
							<p>Visit <a href="http://templatecreme.com/" >Template Creme</a> and find the most beautiful free templates up to date.</p>
                            <a href="#" class="load">load message</a>
						</div>

					</li>

					<li class="third-col">
						
						<div class="widget-block">
							<div id="recent-hubs" class="footer-col">
                                <h4>Recently updated hubs</h4>
                                <ul>
                                    <li class="cat-item"><a href="#" >Cats</a></li>
                                    <li class="cat-item"><a href="#" >Improve your english</a></li>
                                </ul>		         			</div>
		         		</div>
		         		
					</li>
					
					<li class="fourth-col">
						
						<div id="popular-hubs" class="widget-block">
							<h4>Popular hubs</h4>
							<ul>
								<li class="cat-item"><a href="#" >Java</a></li>
								<li class="cat-item"><a href="#" >Photography</a></li>
							</ul>
						</div>
		         		
					</li>
				</ul>
				<!-- ENDS widgets -->	
				
				<!-- bottom -->
				<div class="footer-bottom">
					<div class="left">2014 Hubtangle, all rights reserved. Template design: <a href="http://luiszuno.com" >luiszuno.com</a></div>
						<ul id="social-bar" class="cf sb">
							<li><a href="http://www.facebook.com"  title="Become a fan" class="facebook">Facebbok</a></li>
							<li><a href="http://www.twitter.com" title="Follow my tweets" class="twitter"></a></li>
							<li><a href="http://plus.google.com" title="Enter my circles" class="plus"></a></li>
						</ul>
					<div>Version ${BootStrap.version}</div>
				</div>
				<!-- ENDS bottom -->
			
			</div>
		</footer>
		<!-- ENDS FOOTER -->
</html>