<ul id="nav" class="sf-menu">
	<li class="current-menu-item"><a href="${request.contextPath}">HOME</a></li>
    <li><a href="settings.html">SETTINGS</a></li>

    <sec:ifLoggedIn>
        <li> <misc:link loaction="publish/hub">CREATE NEW HUB</misc:link> </li>
        <li> <misc:link loaction="publish/hub">MY HUBS</misc:link> </li>
    </sec:ifLoggedIn>

    <li class="loginButton">

        <sec:ifNotLoggedIn>
                <g:link controller="login" action="auth"><img style="vertical-align: text-bottom" alt="createpost" src="${resource(dir: 'img', file: 'mono-icons/plug32-inverted.png')}"/>LOGIN</g:link>
            </sec:ifNotLoggedIn> <sec:ifAllGranted roles="ROLE_USER">
                <g:link controller="logout">

                    <img style="vertical-align: text-bottom" alt="createpost" src="${resource(dir: 'img', file: 'mono-icons/plug32-inverted.png')}"/>
                    ${sec.loggedInUserInfo(field:'username')} - LOGOUT

                </g:link>
        </sec:ifAllGranted>

    </li>
</ul>

<div id="combo-holder"></div>

<layout:include template="search" />