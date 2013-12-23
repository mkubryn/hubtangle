%{--comments--}%
<g:each in="${comments}" var="comment">
    <li class="comment even thread-even depth-1" id="li-comment-1">
        <g:render template="/layouts/comments/single_comment" model="${[comment: comment]}"/>
    </li>

</g:each>

%{--offset marker--}%
<div class="offsetHolder" style="display: none">${offset}</div>