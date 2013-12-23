
<div id="openSearchBox">
    <a id="openSearchBoxLink" href="#">
        <img id="openSearchBoxLink-image" style="vertical-align: text-bottom" alt="showSearch" src="${resource(dir: 'img', file: 'mono-icons/search32-negative.png')}"/>
    </a>
</div>

<form id="searchBox" class="form-wrapper cf" style="display: none" action="${request.contextPath}/search/search">
    <input id="searchBoxInput" name="query" type="text" placeholder="Search here..." required>
    <button type="submit">Search</button>
</form>

<script>
    $('#openSearchBoxLink').click(function(e) {
        var disp = $('#searchBox').css('display')
        if(disp == 'none') {
            $('#searchBox').css('display', 'block');
            $('#searchBoxInput').focus();
        } else {
            $('#searchBox').css('display', 'none');
        }

    })
</script>