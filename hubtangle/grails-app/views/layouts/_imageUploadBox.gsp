<%--

    Box for uploading images using Uploadr plugin.

    Requires resource modules:
        - moderniz
        - uploadr
        - application

 --%>

<div id="imageUploadBox">

    <div id="imageUploadPreviewBox">

                %{--if editing--}%
               <g:if test="${dsFileId}">
                    <span>
                           <ds:img id="${dsFileId}"/>
                            <input type="hidden" name="dsFileId" value="${dsFileId}"/>
                        </span>
                </g:if>


        <p/>
    </div>

    <uploadr:add controller="upload" action="upload" name="imageUploadr" fileselect=" - Click here to choose an image or drag - "
                 direction="up" maxVisible="${maxVisible ? maxVisible : 1}" unsupported="${request.contextPath}/upload/warning" downloadable="false" viewable="false"
                 model="[]" allowedExtensions="png,jpg,gif,jpeg">

        <uploadr:onSuccess>
            // add preview and hidden input with dsId
            $('#imageUploadPreviewBox').html('<span id="'+ 'uploadrimg-'+ response.fileName + '" > <img src="'+ response.location +'" />  <input type="hidden" name="dsFileId" value="' + response.dsId + '" /> </span>');

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
