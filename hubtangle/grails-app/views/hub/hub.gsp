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
                <sec:ifUserCanPostOnHub hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <a href="${request.contextPath}/publish/entry?hub=${hub.id}">
                            <img alt="createpost"
                                 src="${resource(dir: 'img', file: 'mono-icons/linedpaperplus32.png')}"/>
                            Add something
                        </a>
                    </div>
                </sec:ifUserCanPostOnHub>

                <sec:ifUserIsNotSubscribingHub hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <a href="subscribe/${hub.id}/">
                            <img alt="subscribe this hub" src="${resource(dir: 'img', file: 'mono-icons/plus32.png')}"/>
                            Subscribe this hub
                        </a>
                    </div>
                </sec:ifUserIsNotSubscribingHub>

                <sec:ifUserIsSubscribingHub hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <a href="unsubscribe/${hub.id}/">
                            <img alt="unsubscribe this hub" src="${resource(dir: 'img', file: 'mono-icons/minus32.png')}"/>
                            Unsubscribe
                        </a>
                    </div>
                </sec:ifUserIsSubscribingHub>

                <sec:ifUserIsHubModerator hubId="${hub.id}">
                    <div class="hubFeatureButton">
                        <a href="moderate/${hub.id}">
                            <img alt="manage permissions"
                                 src="${resource(dir: 'img', file: 'mono-icons/users32.png')}"/>
                            Manage
                        </a>
                    </div>
                </sec:ifUserIsHubModerator>
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