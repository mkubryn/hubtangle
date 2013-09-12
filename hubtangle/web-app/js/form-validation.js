jQuery(document).ready(function($){

	// hide messages 
	$("#error").hide();
	$("#sent-form-msg").hide();
	
	// on submit...
	$("#publishForm #submit").click(function() {
		$("#error").hide();
		
		//required:
		
		//title
		var title = $("input#title").val();
		if(title == ""){
			$("#error").fadeIn().text("Name required.");
			$("input#title").focus();
			return false;
		}

		// content
		var content = $("#content").val();
		
		// data string
		var dataString = 'title='+ title
						+ '&content=' + content
		// ajax
		$.ajax({
			type:"POST",
			url: sendMailUrl,
			data: dataString,
			success: success()
		});
	});  
		
		
	// on success...
	 function success(){
	 	$("#sent-form-msg").fadeIn();
	 	$("#publishForm").fadeOut();
	 }
	
    return false;
});

