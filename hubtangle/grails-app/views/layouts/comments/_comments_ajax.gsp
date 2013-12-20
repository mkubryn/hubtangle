<!-- comments list -->
<div id="comments-wrap">
    <h4 class="heading">5 Comments</h4>



    <ol class="commentlist">

        %{--initial comment search offset--}%
        <div class="offsetHolder" style="display: none">0</div>

        %{--here we put ajax rendered comments--}%
        <div id="commentsBox"></div>

        <div id="commentsLoadingSpinner"></div>

        %{--function for retreiving comments using ajax--}%
        <g:javascript>
            var loadMoreComments = function() {
                ${ remoteFunction (controller: 'entry', action:"getComments_ajax",
                    params:"  'offset=' +\$('.offsetHolder:last').text()  ",
                    onSuccess: "\$('#commentsBox').append(data);hideSpinner('commentsLoadingSpinner')",
                    before: "showSpinner('commentsLoadingSpinner')" ) }
            };
        </g:javascript>

        <a href="javascript:void(0)" onclick="loadMoreComments();return false;">Load more comments</a>

    </ol>
</div>
<!-- ENDS comments list -->