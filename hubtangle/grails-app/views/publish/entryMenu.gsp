<html>
<head>
    <title>Create post</title>
    <meta name="layout" content="main"/>
    <g:javascript/>
    <r:require modules="application"/>
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
            <h1><g:message code="publish.entry.type.ask"/></h1>

            <!-- Entry type chooser form -->
            <div class="entryTypeChooser" style="padding-top: 50px; padding-bottom: 50px">
                <form id="chooseEntryTypeForm" name="chooseEntryTypeForm" action="${request.contextPath}/publish/entry" method="get">

                    <input type="hidden" name="hub" value="${hubId}"/>


                    <div style="margin-top: 30px; margin-bottom: 30px;">

                        <div>
                            <span >
                                <g:render template="entryTypeChoose"
                                          model="[entryType: 'post', icon: 'mono-icons/paperpencil32.png']"/>
                            </span >
                            <span >
                                <g:render template="entryTypeChoose"
                                          model="[entryType: 'image', icon: 'mono-icons/paperphoto32.png']"/>
                            </span >
                            <span >
                                <g:render template="entryTypeChoose"
                                          model="[entryType: 'link', icon: 'mono-icons/paperphoto32.png']"/>
                            </span >
                            <span >
                                <g:render template="entryTypeChoose"
                                          model="[entryType: 'video', icon: 'mono-icons/video32.png']"/>
                            </span >
                        </div>

                        <div style="float: right;">
                            <div class="continueButton" onclick="submitForm()">
                                <img alt="continue" src="${resource(dir: 'img', file: 'mono-icons/arrowright38.png')}"
                                     style="padding-right: 20px;"/>
                                <br> Continue
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <!-- In this div we put entry specific creator -->
            <div id="page-content" class="cf entryTypeChooserPreviewBox"></div>
            <!-- Dynamic content -->
        </div>
    </div>
</div>
<script>
    function submitForm() {
        $('#chooseEntryTypeForm').submit()
    }
</script>
<!-- ENDS MAIN -->
</body>
</html>