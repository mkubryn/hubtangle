
<g:if test='${flash.message}'>
	<div id="flash-popup" style="color: red;">
		ERRORS: ${flash.message}
	</div>
</g:if>

<div>
	<p>Upload Form:</p>
	<g:uploadForm action="upload">
		<input type="file" name="myFile" />
		<input type="submit" />
	</g:uploadForm>
</div>