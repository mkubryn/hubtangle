	function showSpinner(elementId) {
		$("#" + elementId).html("<img src='/hubtangle/img/spinner.gif'/>");
	}

    function hideSpinner(elementId) {
        $("#" + elementId).empty();
    }