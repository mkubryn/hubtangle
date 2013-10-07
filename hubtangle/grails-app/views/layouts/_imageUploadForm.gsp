
<div id="image-upload-box">

	<div id="tst" >
	</div>
	
	<g:formRemote 
		name="image-upload-form" 
		url="[controller: Upload, action: srv]" 
		update="${elementToUpdateWithDsId}" 
		method="post" 
		before="showSpinner('tst')" >
		
		<input type="file" name="myFile" />
		<input type="submit" />
		
	</g:formRemote>
</div>

