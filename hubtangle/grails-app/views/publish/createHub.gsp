<html>
<head>
    <title>Create post</title>
    <meta name="layout" content="main"/>
    <g:javascript/>
    <r:require modules="application"/>
    <r:layoutResources />
</head>
<body class="blog">

<header>
    <div class="wrapper cf">

        <layout:include template="logo" />
        <layout:include template="navigation" />

    </div>
</header>

<!-- MAIN -->
<div id="main">
    <div class="wrapper cf">
        <div class="tightWrapper">
            <h1><g:message code="publish.hub.title"/></h1>

            <div id="ajaxResponse" class="cf "></div>

            <g:formRemote id="publishForm" name="publishForm" url="[controller: 'publish', action:'saveHub']"
                          update="ajaxResponse" before="showSpinner('ajaxResponse')">

                <p>
                    <label for="name" ><g:message code="publish.hub.title"/></label>
                    <input name="name"  id="publish-image-title" type="text" class="form-poshytip" title="Enter hub name" />
                </p>

                <p>
                    <label for="description"><g:message code="publish.entry.image.description"/> </label>
                    <textarea name="description" id="publish-image-description" rows="3" cols="10" class="form-poshytip" title="Enter image description"></textarea>
                </p>


                <input type="submit" value="Create"/>
            </g:formRemote>

        </div>
    </div>
</div>
<!-- ENDS MAIN -->
</body>
</html>