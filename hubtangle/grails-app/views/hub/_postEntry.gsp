
<!-- Standard -->
<article class="format-standard">
	<div class="feature-image">
		<a href="single.html"> <img
			src="${resource(dir: 'img', file: 'test/hubble.jpg')}" alt="Alt text" />
		</a>
	</div>
	<div class="box cf">
		<div class="entry-date">
			<div class="number"> ${entry.getDayOfMonth()} </div>
			<div class="month"> ${entry.getMonthName()} </div>
		</div>

		<div class="excerpt">
			<a href="single.html" class="post-heading">${entry.title }</a>
			<p>${entry.description }</p>

			<p>
				<a href="single.html" class="learnmore">Read more..</a>
			</p>
		</div>

		<div class="meta">
			<span class="format">Post</span> <span class="user"><a
				href="#">By ${entry.author.nick }, </a></span> <span class="comments">16
				comments</span> <span class="tags"><a href="#">red</a>, <a href="#">cyan</a>,
				<a href="#">white</a>, <a href="#">blue</a></span>
		</div>

	</div>
</article>
<!-- ENDS  Standard -->