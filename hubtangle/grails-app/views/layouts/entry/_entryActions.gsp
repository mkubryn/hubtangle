%{--
    Requires model:
        entry - an Entry
        type - entry type
--}%

<h4>Actions</h4>
<div class="actionButtons">

    <sec:ifUserCanEditEntry entryId="${entry.id}">
        <div class="hubFeatureButton">
            <misc:link loaction="publish/entry?hub=${entry.hub.id}&type=${type}&entry=${entry.id}">
                <img alt="edit" src="${resource(dir: 'img', file: 'mono-icons/notepencil32.png')}"/>
                Edit
            </misc:link>
        </div>

        <div class="hubFeatureButton">
            <misc:link loaction="entry/deleteEntry/${entry.id}">
                <img alt="edit" src="${resource(dir: 'img', file: 'mono-icons/block32.png')}"/>
                Delete
            </misc:link>
        </div>

    </sec:ifUserCanEditEntry>

    <img alt="favourite" src="${resource(dir: 'img', file: 'mono-icons/usersblock32.png')}"/>
    <img alt="vote" src="${resource(dir: 'img', file: 'mono-icons/heart32.png')}"/>
</div>