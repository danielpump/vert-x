function gerarRemessa() {
	alert($('#formRemessa').serializeArray());
	$.ajax({
		url : window.location.href,
		type : 'POST',
		data : JSON.stringify($('#formRemessa').serializeArray()),
		success : function() {
			alert('POST completed');
		}
	});
}