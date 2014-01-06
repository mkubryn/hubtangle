<!doctype html>
<html class="no-js">

<head>
    <title>${entry.title} - Hubtangle</title>
    <meta charset="utf-8"/>
    <meta name="layout" content="main"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <r:require modules="application, tabs, social"/>
    <r:layoutResources/>

    <!-- reply move form -->
    <script src="js/moveform.js"></script>
</head>

<body>

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

        <!-- portfolio content-->
        <div id="portfolio-content" class="cf">

            <!-- project box -->
            <div id="project-box" class="cf">

                %{-- article image--}%
                <div class="projectImage">
                    <g:if test="${entry.dsFileId}">

                        <div class="projectImageInner">
                            <ds:img id="${entry.dsFileId}"/>
                        </div>
                    </g:if>

                </div>


                <div class="info">
                    <p><strong>Author</strong>${entry.author.username}</p>

                    <p><strong>Date</strong> ${entry.dateCreated.format("yyyy/MM/dd HH:mm")}</p>
                </div>

                <!-- entry-content -->
                <div class="entry-content">

                    <h1 class="heading">${entry.title}</h1>

                    <div class="content">
                        ${entry.content}
                    </div>
                </div>
                <!-- ENDS entry content -->

            </div>
            <!-- ENDS project box -->

            <div>
                <layout:include template="entry/entryActions" model="[entry: entry, type: type]"/>
            </div>


            <layout:include template="comments/entry_comments_ajax"
                            model="${[entry: entry, commentsCount: commentsCount]}"/>

        </div>
        <!-- ENDS portfolio content-->

    </div><!-- ENDS WRAPPER -->
</div>
<!-- ENDS MAIN -->

</body>
</html>