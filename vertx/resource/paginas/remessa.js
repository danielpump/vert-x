function gerarRemessa() {
	$('#formRemessa').submit(function(event) {		
		$.ajax({
			url : window.location.href,
			dateType : 'json',
			type : 'POST',
			data : JSON.stringify($('#formRemessa').serializeArray()),
			success : function() {
				//Tem essa parada
			}
		});
		return false;
	});
}