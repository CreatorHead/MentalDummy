
$('#signUpId').submit(function() {
    if (!($('input:radio', this).is(':checked'))) {
    	radioBtnError();
        return false;
    }
});



function radioBtnError(){
	$("#msgPara").remove();
	$('#radioPara').append("<p id='msgPara'><span>Please select a gender<span></p>");
}

$("#genderId").on('click',function(){
	$("#msgPara").remove();
});

$('#pwdInput').tooltip({'trigger':'focus', 'title': 'Minimum 8 Chars with a Special Char'});

$('#repPwdInput').on('input',function(){
	var repPwd = $('#repPwdInput').val();
	var pwd = $('#pwdInput').val();
	if(!(repPwd === pwd)){
		$("#repPwdParaTemp").remove();
		$('#repPwdPara').append("<p id='repPwdParaTemp'><span>Password mismatch<span></p>");
		$("#btnValidate").attr('disabled','disabled');
	}else {
		$("#repPwdParaTemp").remove();
		$("#btnValidate").removeAttr('disabled');
	}
});