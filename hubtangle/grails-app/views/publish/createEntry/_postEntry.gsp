				
				<h3><g:message code="publish.entry.post.prepare"/></h3>
				<!-- form -->

				<form id="publishForm" action="#" method="post">
					<fieldset>
						<p>
							<label for="name" ><g:message code="publish.entry.post.title"/></label>
							<input name="name"  id="name" type="text" class="form-poshytip" title="Enter title" />
						</p>
						
						<p>
							<label for="content"><g:message code="publish.entry.post.content"/> </label>
							<textarea  name="content"  id="content" rows="5" cols="20" class="form-poshytip" title="Enter content"></textarea>
						</p>
						
						<p><input type="button" value="Create" name="submit" id="submit" /> <span id="error" class="warning">Message</span></p>
					</fieldset>
					
				</form>
				<p id="sent-form-msg" class="success">Form data sent. Thanks for your comments.</p>
				<!-- ENDS form -->				