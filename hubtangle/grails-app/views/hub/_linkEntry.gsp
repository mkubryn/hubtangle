<!-- Link -->
<article class="format-link">
    <div class="box cf">
        <div class="entry-date">
            <div class="number">${entry.getDayOfMonth()}</div>

            <div class="month">${entry.getMonthName()}</div>
        </div>

        <div class="excerpt">
            <a href="http://${entry.url}" class="post-heading" target="_blank">${entry.url}</a>

            <div>${entry.description}</div>
        </div>

        <div class="meta">
            <span class="format">Link</span> <span class="user"><a
                href="#">By ${entry.author.username},</a></span> <span class="comments">16
        comments</span>

            <span class="tags">
                <g:each in="${tagMap[entry.id]}" var="tag">
                    #<misc:link loaction="search/search?query=%23${tag}">${tag}</misc:link>
                </g:each>
            </span>
        </div>
    </div>
</article>
<!-- ENDS Link -->