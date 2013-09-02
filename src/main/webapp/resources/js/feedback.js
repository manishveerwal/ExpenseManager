$(document).ready(function() {
	$("#feedback").submit(function() {
		var name = $("#name").val();
		var email = $("#email").val();
		var comment = $("#textarea").val();
		
		if (name == "" || email == "" || comment == "") {
			$("#error").remove();
			$("#feedbackDiv").prepend("<div id=\"error\" style=\"color: red;\">Please fill all the fields.</div>");
			return false;
		} else {
			$("#feedback").submit();
		}
		
	});
});
