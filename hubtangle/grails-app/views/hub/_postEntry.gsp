<%@ page import="net.hubtangle.utils.GspUtils" %>

<!-- Standard -->
<article class="format-standard">

    <g:if test="${entry.dsFileId}">
        <div class="feature-image">
            <ds:prettyImg id="${entry.dsFileId}"/>
        </div>
    </g:if>

    <div class="box cf">
        <div class="entry-date">
            <div class="number">${entry.getDayOfMonth()}</div>

            <div class="month">${entry.getMonthName()}</div>
        </div>

        <div class="excerpt">
            <a href="${request.contextPath}/article/${entry.id}" class="post-heading">${entry.title}</a>

            <p>${GspUtils.asHtml(entry.description)}</p>

            <p>
                <a href="single.html" class="learnmore">Read more..</a>
            </p>
        </div>

        <div class="meta">
            <span class="format">Post</span> <span class="user"><a
                href="#">By ${entry.author.username},</a></span>
            <span class="comments">16 comments</span>

            <span class="tags">
                <g:each in="${tagMap[entry.id]}" var="tag">
                    #<misc:link loaction="search/search?query=%23${tag}">${tag}</misc:link>
                </g:each>
            </span>
        </div>

    </div>
</article>
<!-- ENDS  Standard -->