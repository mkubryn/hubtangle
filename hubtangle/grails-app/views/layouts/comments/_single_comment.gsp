

    <g:each in="${comments}" var="comment">

        <li class="comment even thread-even depth-1" id="li-comment-1">

            <div id="comment-1" class="comment-body cf">
                <img alt='' src='http://0.gravatar.com/avatar/4f64c9f81bb0d4ee969aaf7b4a5a6f40?s=35&amp;d=&amp;r=G' class='avatar avatar-35 photo' height='35' width='35' />
                <div class="comment-author vcard">${comment.author.username}</div>
                <div class="comment-meta commentmetadata">
                    <span class="comment-date">3 days ago  </span>
                    <span class="comment-reply-link-wrap"><a class='comment-reply-link' href='replytocom=23#respond' onclick='return addComment.moveForm("comment-1", "1", "respond", "432")'>Reply</a></span>

                </div>
                <div class="comment-inner">
                    <p>${comment.content}</p>
                </div>
            </div>
        </li>

    </g:each>

    <div class="offsetHolder" style="display: none">${offset}</div>