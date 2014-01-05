<%@ page import="net.hubtangle.utils.GspUtils" %>
<html>
<head>
    <title>Hubtangle - your entry point</title>
    <meta name="layout" content="main"/>
    <r:require modules="application, tabs, social"/>
    <r:layoutResources/>
</head>

<body class="home">
<header>
    <div class="wrapper cf">

        <layout:include template="logo"/>
        <layout:include template="navigation"/>
        %{--<layout:include template="homepage/slider"/>--}%

    </div>
</header>

<!-- MAIN -->
<div id="main">
    <div class="wrapper cf homepageWrapper">

        <h3><g:message code="homepage.recentActivity"/></h3>

        <!-- recent activity -->
        <div class="home-featured">
            <ul id="filter-buttons">
                <li><a href="#" data-filter="*" class="selected">Show all</a></li>

                <g:each in="${hubMap.keySet()}" var="signature">
                    <li>
                        <a class="hubTitleFeatureSelect" href="#"
                           data-filter=".${signature}">${hubMap[signature].title}
                        </a>
                    </li>
                </g:each>
            </ul>

            <!-- Filter container -->
            <div id="filter-container" class="cf">

                <g:each in="${lastEntries}" var="entry">
                    <figure class="${entry.hub.getSignature()}">
                        <a href="hub/${entry.hub.id}#highlight-${entry.id}" class="thumb">

                            <g:if test="${entry.metaClass.getMetaProperty('dsFileId')}">
                                <g:if test="${entry.dsFileId}">
                                    <ds:img id="${entry.dsFileId}"/>
                                </g:if>
                            </g:if>

                            <g:if test="${entry instanceof net.hubtangle.entry.VideoEntry}">
                                <misc:youtubeThumbnail videoId="${entry.getYoutubeVideoId()}"/>
                            </g:if>
                        </a>

                        <figcaption>
                            <a href="hub/${entry.hub.id}#highlight-${entry.id}">

                                <h3 class="heading">${entry.title}</h3>
                            </a>
                            ${GspUtils.asHtml(entry.description)}

                        </figcaption>

                        <div class="homepageTags">
                            <span class="hubTag">
                                <misc:link loaction="hub/${entry.hub.id}">${entry.hub.title}</misc:link>
                            </span>
                            <g:each in="${tagMap[entry.id]}" var="tag">
                                <span>
                                    #<misc:link loaction="search/search?query=%23${tag}">${tag}</misc:link>
                                </span>
                            </g:each>
                        </div>
                    </figure>
                </g:each>
            </div>
            <!-- ENDS Filter container -->
        </div>
        <!-- ENDS featured -->
    </div>
    <!-- ENDS WRAPPER -->
</div>
<!-- ENDS MAIN -->
</body>
</html>