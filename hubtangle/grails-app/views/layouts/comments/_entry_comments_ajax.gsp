%{--
    Requires model:
        commentsCount - count of comments
        comment - Comment object
--}%


%{--comments list--}%
<div id="comments-wrap">
    <h4 class="heading"><span id="commentsCount">${commentsCount}</span> comments</h4>

    <sec:ifLoggedIn>
    %{--send new comment form--}%
        <g:formRemote
                id="sendCommentForm"
                name="sendCommentForm"
                url="[controller: 'entry', action: 'createComment']"
                onSuccess="onCommentSent(data); bumpCommentsCount()"
                onLoaded="clearCommentArea();"
                before="showSpinner('commentsLoadingSpinner');">

            <g:textArea name="text" escapeHtml="true" rows="5" id="commentArea" class="commentArea"></g:textArea>
            <input type="hidden" name="entryId" value="${entry.id}"/>

            <g:submitButton class="stylishButton" name="sendCommentFormSubmit" value="Send"/>
        </g:formRemote>
    </sec:ifLoggedIn>

    <sec:ifNotLoggedIn>
        You have to be logged in to create comments.
    </sec:ifNotLoggedIn>

    <ol class="commentlist">

        %{--initial comment search offset--}%
        <div class="offsetHolder" class="offsetHolder" style="display: none">0</div>

        %{--here we put comments added by user recently--}%
        <div id="myComments"></div>

        %{--here we put ajax rendered comments--}%
        <div id="commentsBox"></div>

        %{--spinner--}%
        <div id="commentsLoadingSpinner"></div>

    %{--function for retrieving comments using ajax--}%
        <g:javascript>
            var loadMoreComments = function() {
                ${remoteFunction(controller: 'entry', action: "getComments_ajax", id: "${entry.id}",
                params: "  'offset=' +\$('.offsetHolder:last').text()  ",
                onSuccess: "onCommentsLoaded(data)",
                before: "showSpinner('commentsLoadingSpinner')")}
            };
        </g:javascript>

        <g:if test="${commentsCount > 0}">
            <div id="loadMoreComments">
                <a href="javascript:void(0)" onclick="loadMoreComments();
                return false;">Load more comments</a>
            </div>
        </g:if>
    </ol>
</div>
<!-- ENDS comments list -->

<script>

    loadMoreComments();

    function bumpOffset() {
        var offsetHolder = $('.offsetHolder:last');
        var offsetVal = offsetHolder.text();
        var offset = parseInt(offsetVal) + 1;

        offsetHolder.html(offset);
    }

    function bumpCommentsCount() {
        var offsetHolder = $('#commentsCount');
        var offsetVal = offsetHolder.text();
        var offset = parseInt(offsetVal) + 1;

        offsetHolder.html(offset);
    }

    function onCommentSent(data) {
        $('#myComments').prepend(data);
        hideSpinner('commentsLoadingSpinner');
        bumpOffset();

    }

    function onCommentsLoaded(data) {
        $('#commentsBox').append(data);
        hideSpinner('commentsLoadingSpinner');
    }

    function clearCommentArea() {
        $('#commentArea').val('')
    }
</script>