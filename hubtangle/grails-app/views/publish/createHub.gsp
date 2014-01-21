<html xmlns="http://www.w3.org/1999/html">
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
        <div class="tightWrapper createHubForm">
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

                <h3>Accessibility</h3>

                <div id="accessOpen" title="Anyone can read and post.">
                    <input id="inputAccessOpen" name="accessibility" title="Anyone can read and create entries." type="radio" value="OPEN"/>
                    <label for="inputAccessOpen"><misc:img file="mono-icons/users32.png"/>Open</label>
                </div>

                <div id="accessPublic" title="Anyone can read but only choosen people can post.">
                    <input id="inputAccessPublic" name="accessibility" title="Anyone can read and create entries." type="radio" checked="true" value="PUBLIC"/>
                    <label for="inputAccessPublic"><misc:img file="mono-icons/usersplus32.png"/>Public</label>
                </div>

                <div id="accessPrivate" title="Only choosen people can read and create entries.">
                    <input id="inputAccessPrivate" name="accessibility" title="Anyone can read and create entries." type="radio" value="PRIVATE"/>
                    <label for="inputAccessPrivate"><misc:img file="mono-icons/userplus32.png"/>Private</label>
                </div>


                <input type="submit" value="Create"/>

            </g:formRemote>

            <script>
                $('#accessOpen').poshytip({ className: 'tip-twitter' });
                $('#accessPublic').poshytip({ className: 'tip-twitter' });
                $('#accessPrivate').poshytip({ className: 'tip-twitter' });
            </script>

        </div>
    </div>
</div>
<!-- ENDS MAIN -->
</body>
</html>