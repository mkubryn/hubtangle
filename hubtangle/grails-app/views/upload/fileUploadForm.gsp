<html>
<head>
    <title>Hubtangle - your entry point</title>
    <meta name="layout" content="main"/>
    <r:require modules="application, uploadr"/>
	<r:layoutResources/>
</head>

<body class="home">
	<header>
		<div class="wrapper cf">
			
			<layout:include template="logo" />
			<layout:include template="navigation" />

		</div>
	</header>
	<!-- MAIN -->
	<div id="main">
		<div class="wrapper cf">
		
			<div id="image-upload-box">
			
				<div id="spinnerBox" >
					<p/>
				</div>


                <g:uploadForm action="upload">
                    <input type="file" name="file" />
                    <input type="submit" />
                </g:uploadForm>

				
				<uploadr:add name="mySecondUploadr" path="/home/mkubryn/devel/hubtangle-eclipse-ws/ds_filestore" allowedExtensions="jpg,png,gif" 
					direction="up" maxVisible="5" unsupported="/hubtangle/upload/warning" 
					model="[]">
 
				</uploadr:add>
			
			</div>

		</div>
		<!-- ENDS WRAPPER -->
	</div>
	<!-- ENDS MAIN -->
</body>
</html>