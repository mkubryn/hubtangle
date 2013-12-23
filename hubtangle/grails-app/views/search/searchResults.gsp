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
        <h1><g:message code="search.results.title"/> ${query}</h1>

        <g:each in="${types.entrySet()}" var="docEntry">
            <ul>
               <li>
                   <div class="searchReslutsCategory">
                       <h3>${docEntry.key} (${docEntry.value.size()})</h3>
                           <ul>
                               <g:each in="${docEntry.value}" var="result">
                                   <li>
                                       <div class="searchReslutsResult-title">
                                           <a href="${request.contextPath}/${net.hubtangle.helper.LinkHelper.createLink(result)}">
                                               ${result.title[0]}
                                            </a>
                                       </div>
                                       <div class="searchReslutsResult-text">
                                            ${result.description[0]}
                                       </div>
                                   </li>
                               </g:each>
                           </ul>
                   </div>

                   <div id="searchResultsSeparator"></div>
                </li>
            </ul>
        </g:each>



        <!-- In this div we put entry specific creator -->
        <div id="page-content" class="cf "></div>
        <!-- Dynamic content -->
    </div>
</div>
<!-- ENDS MAIN -->
</body>
</html>