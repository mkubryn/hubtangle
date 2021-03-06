<html>
<head>
    <title>Create post</title>
    <meta name="layout" content="main"/>
    <g:javascript/>
    <r:require modules="application,modernizr,uploadr"/>
    <r:layoutResources/>
</head>

<body class="blog">

<header>
    <div class="wrapper cf">
        <layout:include template="logo"/>
        <layout:include template="navigation"/>
    </div>
</header>

<!-- MAIN -->
<div id="main">
    <div class="wrapper cf">
        <div class="tightWrapper">
            <h2><g:message code="publish.entry.image.prepare"/></h2>

            <div id="publish-box">
                <div id="ajaxResponse">
                    <!-- Dynamic content -->
                </div>

                <g:formRemote id="publishForm" name="publishForm"
                              url="[controller: 'publish', action: 'saveImageEntry', id: hubId]"
                              update="ajaxResponse">

                    <layout:include template="imageUploadBox" model="[dsFileId: command.dsFileId]"/>

                    <input type="hidden" name="entityId" value="${command.entityId}" />

                    <p>
                        <label for="title">
                            <g:message code="publish.entry.image.title"/>
                        </label>

                        <input name="title" id="publish-image-title" type="text" class="form-poshytip"
                               title="Enter title" value="${command.title}"/>
                    </p>

                    <p>
                        <label for="description">
                            <g:message code="publish.entry.image.description"/>
                        </label>

                        <textarea name="description" id="publish-image-description" rows="5" cols="20"
                                  class="form-poshytip" title="Enter image description" >${command.description}</textarea>
                    </p>

                    <input type="submit" value="Create"/>
                </g:formRemote>
            </div>
        </div>
    </div>
</div>
<!-- ENDS MAIN -->
</body>
</html>