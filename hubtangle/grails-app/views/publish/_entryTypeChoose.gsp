
<input  type="radio" name="type" value="${entryType}" id="${entryType}Entry" onchange="${remoteFunction(
										            action:'axEntryDescription',
										            update:'page-content', 
										            params:'jQuery(this).serialize()',
													before: 'showSpinner(\'page-content\')' )}"/>            
<label for="${entryType}Entry"><img src="${resource(dir: 'img', file: icon)}" alt="${entryType} entry" /> 
	&nbsp;<g:message code="publish.entry.${entryType}"/>
</label>