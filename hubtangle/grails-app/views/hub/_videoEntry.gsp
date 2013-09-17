<!-- Video -->
<article class="format-video">
	<div class="video-container">
		<iframe src="${entry.url}"></iframe>
	</div>
	<div class="box cf">
		<div class="entry-date">
			<div class="number"> ${entry.getDayOfMonth()} </div>
			<div class="month"> ${entry.getMonthName()} </div>
		</div>
		<div class="excerpt">
			<a href="#" class="post-heading">${entry.title}</a>
			<p>${entry.description}</p>
		</div>

		<div class="meta">
			<span class="format">Video</span> <span class="user"><a
				href="#">By ${entry.author.username}, </a></span> <span class="comments">16 comments</span>
			<span class="tags"><a href="#">red</a>, <a href="#">cyan</a>,
				<a href="#">white</a>, <a href="#">blue</a></span>
		</div>
	</div>
</article>
<!-- ENDS Video -->