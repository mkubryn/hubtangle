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

            <h2>1. Choose images</h2>

			<div id="imageUploadBox">
			
                <div id="imageUploadPreviewBox">
                    <p/>
                </div>

				<uploadr:add controller="upload" action="upload" name="imageUploadr" fileselect="Click to choose or drag.."
					direction="up" maxVisible="3" unsupported="${request.contextPath}/upload/warning" downloadable="false" viewable="false"
					model="[]" allowedExtensions="png,jpg,gif,jpeg">

                    <uploadr:onSuccess>
                        // add preview and hidden input with dsId
                        $('#imageUploadPreviewBox').append('<span id="'+ 'uploadrimg-'+ response.fileName + '" > <img src="'+ response.location +'" />  <input type="hidden" name="dsId" value="' + response.dsId + '" /> </span>');

                        // callback when done
                        callback();

                    </uploadr:onSuccess>

                    <uploadr:onDelete>
                        var elementId = 'uploadrimg-' + file.fileName;
                        console.log('you clicked delete for: ' + elementId);

                        var element = document.getElementById(elementId);
                        element.parentNode.removeChild(element);

                        return true;
                    </uploadr:onDelete>

				</uploadr:add>
			</div>

            <h2>2. Type your description</h2>

		</div>
		<!-- ENDS WRAPPER -->
	</div>
	<!-- ENDS MAIN -->
</body>
</html>