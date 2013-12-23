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
            <h1><g:message code="publish.hub.header"/></h1>

            <div id="ajaxResponse" class="cf "></div>

            <g:formRemote id="publishForm" name="publishForm" url="[controller: 'publish', action:'saveHub']"
                          update="ajaxResponse" before="showSpinner('ajaxResponse')">

                <p>
                    <label for="title" ><g:message code="publish.hub.title"/></label>
                    <input name="title"  id="publish-image-title" type="text" class="form-poshytip" title="Enter hub title" />
                </p>

                <p>
                    <label for="description"><g:message code="publish.hub.description"/> </label>
                    <textarea name="description" id="publish-image-description" rows="3" cols="10" class="form-poshytip" title="Enter hub description"></textarea>
                </p>


                <input type="submit" value="Create"/>
            </g:formRemote>

        </div>
    </div>
</div>
<!-- ENDS MAIN -->
</body>
</html>