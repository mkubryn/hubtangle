<html>
<head>
    <title>${hub.title}</title>
    <meta name="layout" content="main"/>
    <r:require modules="application"/>
    <r:layoutResources/>
</head>

<body class="blog">
<!-- HEADER -->
<header>
    <div class="wrapper cf">

        <layout:include template="logo"/>
        <layout:include template="navigation"/>

    </div>
</header>
<!-- ENDS HEADER -->

<!-- MAIN -->
<div id="main">
    <div class="wrapper cf">

        <!-- posts list -->
    <div id="posts-list" class="cf">

        <g:if test="${entries.size() == 0}">
            <div id="emptyHubBox">
                <h3>This hub is empty.</h3>
            </div>
        </g:if>

    <!-- Render each entry -->
        <g:each in="${entries}" var="e">
            <div id="highlight-${e.id}"
            <g:render template="${e.getRenderTemplateName()}" bean="${e}" var="entry"/>
            </div>
        </g:each>

    <!-- page-navigation -->
        <div class="page-navigation cf">
            <g:if test="${previousPage}">
                <div class="nav-previous"><a href="?page=${previousPage}">Newer Entries &#8594;</a></div>
            </g:if>

            <g:if test="${nextPage}">
                <div class="nav-next"><a href="?page=${nextPage}">&#8592; Older Entries</a></div>
            </g:if>
        </div>
        <!--ENDS page-navigation -->

    </div>
    <!-- ENDS posts list -->

    <!-- sidebar -->
    <aside id="sidebar">
        <ul>
            <li class="block">
                <h4>${hub.title}</h4>
                ${hub.description}
            </li>

            <li class="block">
                <h4>Actions</h4>
                <hub:ifUserCanPostOnHub hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <misc:link loaction="publish/entry?hub=${hub.id}">
                            <img alt="createpost"
                                 src="${resource(dir: 'img', file: 'mono-icons/linedpaperplus32.png')}"/>
                            Add something
                        </misc:link>
                    </div>
                </hub:ifUserCanPostOnHub>

                <hub:ifUserIsNotSubscribingHub hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <a href="subscribe/${hub.id}/">
                            <img alt="subscribe this hub" src="${resource(dir: 'img', file: 'mono-icons/plus32.png')}"/>
                            Subscribe this hub
                        </a>
                    </div>
                </hub:ifUserIsNotSubscribingHub>

                <hub:ifUserIsSubscribingHub hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <a href="unsubscribe/${hub.id}/">
                            <img alt="unsubscribe this hub" src="${resource(dir: 'img', file: 'mono-icons/minus32.png')}"/>
                            Unsubscribe
                        </a>
                    </div>
                </hub:ifUserIsSubscribingHub>

                <hub:ifUserIsHubModerator hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <a href="moderate/${hub.id}">
                            <img alt="manage permissions"
                                 src="${resource(dir: 'img', file: 'mono-icons/users32.png')}"/>
                            Manage
                        </a>
                    </div>
                </hub:ifUserIsHubModerator>

                <hub:ifUserIsHubAdministrator hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <misc:link loaction="publish/hub/${hub.id}">
                            <misc:img file="mono-icons/notepencil32.png" alt="edit"/>
                            Edit
                        </misc:link>
                    </div>
                </hub:ifUserIsHubAdministrator>
            </li>

            <li class="block">
                <h4>Moderators</h4>
                <ul>
                    <li class="cat-item"><a href="#" title="title">Marcin Kubryn</a></li>
                    <li class="cat-item"><a href="#" title="title">Agnieszka S.</a></li>
                </ul>
            </li>

        </ul>

    </aside>
    <!-- ENDS sidebar -->

</div><!-- ENDS WRAPPER -->
</div>
<!-- ENDS MAIN -->
</body>
</html>