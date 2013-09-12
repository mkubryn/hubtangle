<h3><p><g:message code="publish.entry.post.prepare"/></p></h3>
<!-- form -->

<div id="createPostRespone">
</div>

<g:formRemote id="publishForm" name="publishForm" url="[controller: 'publish', action:'createPostEntry', id: hubId]" 
	update="createPostRespone">
		<p>
			<label for="title" ><g:message code="publish.entry.post.title"/></label>
			<input name="title"  id="title" type="text" class="form-poshytip" title="Enter title" />
		</p>
		<p>
			<label for="description"><g:message code="publish.entry.post.description"/> </label>
			<textarea  name="description" id="description" rows="5" cols="20" class="form-poshytip" title="Enter description"></textarea>
		</p>

		<input type="submit" value="Submit">
</g:formRemote>


<!-- ENDS form -->