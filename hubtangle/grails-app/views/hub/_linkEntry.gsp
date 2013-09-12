<!-- Link -->
<article class="format-link">
	<div class="box cf">
		<div class="entry-date">
			<div class="number">${entry.getDayOfMonth()}</div>
			<div class="month">${entry.getMonthName()}</div>
		</div>

		<div class="excerpt">
			<a href="${entry.url}" class="post-heading">${entry.url}</a>
			<div>${entry.description}</div>
		</div>

		<div class="meta">
			<span class="format">Link</span> <span class="user"><a
				href="#">By ${entry.author.nick}, </a></span> <span class="comments">16
				comments</span> <span class="tags"><a href="#">red</a>, <a href="#">cyan</a>,
				<a href="#">white</a>, <a href="#">blue</a></span>
		</div>
	</div>
</article>
<!-- ENDS Link -->