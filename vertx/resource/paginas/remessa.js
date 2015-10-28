function gerarRemessa() {	
	$('#formRemessa').submit(function(event) {		
		$.ajax({
			url : window.location.href,
			dateType : 'json',
			type : 'POST',
			data : formParaJSON(),
			success : function() {
				alert("Arquivo será gerado por processos assíncronos.");
			}
		});
		location.reload();
		return false;
	});	
}

function formParaJSON() {
	var jsonObj = {
		"codigoBeneficiario" : document.getElementById("codigoBeneficiario").value.trim(),
		"nomeBeneficiario" : document.getElementById("nomeBeneficiario").value.trim(),
		"codigoAgencia" : document.getElementById("codigoAgencia").value.trim(),
		"codigoBanco" : document.getElementById("codigoBanco").value.trim(),
		"nomeBanco" : document.getElementById("nomeBanco").value.trim()
	};
	return JSON.stringify(jsonObj);
}