$(document).ready(function() {
	$("#accountForm").submit(function() {
		var goAhead = true;
		var password =  $('#password').val();
		var password1 =  $('#password1').val();
		$('div.errorField').remove();
		if ($('#firstName').val() == "") {
			$('#firstName').before("<div class=\"errorField\" style=\"color: red;\">Field Can not be empty!</div>");
			goAhead = false;
		}
		if ($('#lastName').val() == "") {
			$('#lastName').before("<div class=\"errorField\" style=\"color: red;\">Field Can not be empty!</div>");
			goAhead = false;
		}
		if ($('#email').val() == "") {
			$('#email').before("<div class=\"errorField\" style=\"color: red;\">Field Can not be empty!</div>");
			goAhead = false;
		}
		if (password == "") {
			$('#password').before("<div class=\"errorField\" style=\"color: red;\">Field Can not be empty!</div>");
			goAhead = false;
		}
		if (password1 == "") {
			$('#password1').before("<div class=\"errorField\" style=\"color: red;\">Field Can not be empty!</div>");
			goAhead = false;
		}
		if ($('#gender').val() == "Select") {
			$('#gender').before("<div class=\"errorField\" style=\"color: red;\">Please select your gender!</div>");
			goAhead = false;
		}
		if ($('#country').val() == "Select") {
			$('#country').before("<div class=\"errorField\" style=\"color: red;\">Please select your country!</div>");
			goAhead = false;
		}
		
		if ((password != "" && password1 != "") && password != password1) {
			$('#password').before("<div class=\"errorField\" style=\"color: red;\">Password and Confirm Password does not match!</div>");
			goAhead = false;
		}
		
		if (goAhead) {
			$("#accountForm").submit();
		} else {
			return false;
		}
	});
	
	$("#email").blur(function(){
		if($("#email").val() == ""){
			$("#emailError").text("Please enter your email.");
			$("#emailError").css('color', 'red');
		} else {
			$.getJSON("/ExpenseManager/isUserExist?email="+$("#email").val(), function(emailMsg){
				if (emailMsg.error) {
					$("#emailError").text(emailMsg.msg);
					$("#emailError").css('color', 'red');
				}
			});
		}
	});
	
	$("#email").focus(function() {
		$("#emailError").text("");
	});
});