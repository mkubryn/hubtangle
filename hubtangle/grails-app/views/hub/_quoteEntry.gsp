
<!-- Quote -->
<article class="format-quote">
	<div class="box cf">
		<div class="entry-date">
			<div class="number">${entry.getDayOfMonth()}</div>
			<div class="month">${entry.getMonthName()}</div>
		</div>

		<div class="excerpt">
			<a href="#" class="post-heading">${entry.text}</a>
			<p>
				<span class="q-author"> - ${entry.quoteAuthor}</span>
			</p>
			<div>${entry.description}</div>
		</div>

		<div class="meta">
			<span class="format">Quote</span> <span class="user"><a
				href="#">By ${entry.author.nick}, </a></span> <span class="comments">999+
				comments</span> <span class="tags"><a href="#">red</a>, <a
				href="#">blue</a></span>
		</div>

	</div>
</article>
<!-- ENDS Quote -->