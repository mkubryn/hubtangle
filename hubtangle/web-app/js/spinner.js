	function showSpinner(elementId) {
		$("#" + elementId).html("<img src='/img/spinner.gif'/>");
	}

    function hideSpinner(elementId) {
        $("#" + elementId).empty();
    }