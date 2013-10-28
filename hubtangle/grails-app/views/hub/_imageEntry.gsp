<!-- Image -->
<article class="format-image">
	<div class="feature-image">
		<a href="#"
			data-rel="prettyPhoto"> <ds:img id="${entry.dsFileId}" />
		</a>
	</div>
	<div class="box cf">
		<div class="entry-date">
			<div class="number"> ${entry.getDayOfMonth()} </div>
			<div class="month"> ${entry.getMonthName()} </div>
		</div>
		<div class="excerpt">
			<a href="${request.contextPath}/image/${entry.id}" class="post-heading">
				${entry.title}
			</a>
			<p>
				${entry.description}
			</p>
		</div>

		<div class="meta">
			<span class="format">Image</span> <span class="user"><a
				href="#">By ${entry.author.username}, </a></span> <span class="comments">28
				comments</span> <span class="tags"><a href="#">tag1</a>, <a
				href="#">tag2</a></span>
		</div>
	</div>
</article>
<!-- ENDS Image -->