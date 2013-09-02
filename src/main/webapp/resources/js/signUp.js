$(document).ready(function() {
	$("#accountForm").submit(function() {
		var goAhead = true;
		var password =  $('#password').val();
		var password1 =  $('#password1').val();
		$('div.errorField').remove();
		if ($('#firstName').val() == "" || $('#lastName').val() == "") {
			$('#lastName').after("<div class=\"errorField\" style=\"color: red;\">Please enter first name and last name</div>");
			goAhead = false;
		}
		if ($('#email').val() == "") {
			$('#email').after("<div id=\"emailValidationError\" class=\"errorField\" style=\"color: red;\">Enter your email address</div>");
			$("#emailError").text("");
			goAhead = false;
		}
		if (password == "") {
			$('#password').after("<div class=\"errorField\" style=\"color: red;\">Choose your password</div>");
			goAhead = false;
		}
		if (password1 == "") {
			$('#password1').after("<div class=\"errorField\" style=\"color: red;\">Confirm your password here.</div>");
			goAhead = false;
		}
		if (!$("#genderM").is(":checked") && !$("#genderF").is(":checked")) {
			$('#gender').append("<div class=\"errorField\" style=\"color: red;\">Please select your gender</div>");
			goAhead = false;
		}
		if ($('#country').val() == "Your Country") {
			$('#country').after("<div class=\"errorField\" style=\"color: red;\">Please select your country</div>");
			goAhead = false;
		}
		
		if ((password != "" && password1 != "") && password != password1) {
			$('#password').after("<div class=\"errorField\" style=\"color: red;\">Password and Confirm Password does not match</div>");
			$('#password').val('');
			$('#password1').val('');
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
		} else {
			$.getJSON("/ExpenseManager/isUserExist?email="+$("#email").val(), function(emailMsg){
				if (emailMsg.error) {
					$("#emailError").text(emailMsg.msg);
					$("#email").val('');
				}
			});
		}
	});
	
	$("#email").focus(function() {
		$("#emailError").text("");
		$("#emailValidationError").remove();
	});
	
	$("#gender").change(function() {
		if ($("#gender").val() == "Your Gender") {
			$("#gender").css('color', '#999');
		} else {
			$("#gender").css('color', '#000');
		}
	});
	
	$("#country").change(function() {
		if ($("#country").val() == "Your Country") {
			$("#country").css('color', '#999');
		} else {
			$("#country").css('color', '#000');
		}
	});
});