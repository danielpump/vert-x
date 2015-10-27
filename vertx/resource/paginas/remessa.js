(function($) {
$.fn.serializeFormJSON = function() {

   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};
})(jQuery);

function gerarRemessa() {
	$('#formRemessa').submit(function(event) {		
		alert($('#formRemessa').serializeFormJSON());
		$.ajax({
			url : window.location.href,
			dateType : 'json',
			type : 'POST',
			data : $('#formRemessa').serializeFormJSON(),
			success : function() {
				//Tem essa parada
			}
		});
		return false;
	});
}