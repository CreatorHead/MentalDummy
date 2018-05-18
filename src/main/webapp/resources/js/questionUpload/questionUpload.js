$('#uploadQuestion').on('submit', function(){
	if($('input[name=diffLvl]:checked').length<=0)
	{
		radioBtnError();
		return false;
	}else{
		return true;
	}
});

function radioBtnError(){
	$("#difflvlmsgPara").remove();
	$('#diffradioPara').append("<p class='redColor' id='difflvlmsgPara'><span>Please select a gender<span></p>");
}

$("#diffId").on('click',function(){
	$("#difflvlmsgPara").remove();
});

$('#fileUploadInput').on('input',function(){
	var ext = $('#fileUploadInput').val().split('.').pop().toLowerCase();
	if($.inArray(ext, ['xlsx','xlsm','xltx','xltm']) == -1) {
		$("#difflvlUpld").remove();
		$('#fileUploadPara').append("<p class='redColor' id='difflvlUpld'><span>Please select a proper excel sheet<span></p>");
		$('#fileUploadInput').val('');
		$("#btnValidate").attr('disabled','disabled');
	}else{
		$("#difflvlUpld").remove();
		$("#btnValidate").removeAttr('disabled');
	}
})
