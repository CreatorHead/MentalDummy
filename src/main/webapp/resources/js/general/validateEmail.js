function validateEmail(sEmail) {
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (filter.test(sEmail)) {
		return true;
	}
	else {
		return false;
	}
}

function emailError(){
	$("#emailSpan").remove();
	$('#emailPara').append("<span id='emailSpan'>Please provide a valid email<span>");
	$("#btnValidate").attr('disabled','disabled');
}

function emailCheck(){
	var sEmail = $('#txtEmail').val();
	if ($.trim(sEmail).length == 0) {
		emailError();
	}
	if (!(validateEmail(sEmail))){
		emailError();
	}else{
		$("#emailSpan").remove();
		$("#btnValidate").removeAttr('disabled');
	}
}

$(document).ready(function() {
	$('#txtEmail').on("blur",emailCheck);
});

$(document).ready(function(){
	$("#txtEmail").mouseleave(emailCheck);
})

