

$("#tb-dez-mais").ready(function(){
    console.log('start load');
    
    //$('#tb-dez-mais tr:last').after('<td>001</td><td>Honda 1</td><td>Accord 1 </td><td>20091</td><td>Honda1</td>');
    
    //var uri = '/hire.me/encurtar/listar';
    var uri = '/encurtar/listar';
    
    $.ajax( {
    	url: uri,
    	method :'GET'
    })
    .done(function(obj) {
      console.log( "success" );
      console.log(obj);
      
      $.each(obj, function(index, value) {
    	    console.log(value);
    	    $('#tb-dez-mais tr:last').after('<tr class="pure-table-odd"><td>'+(index +1) +'</td><td>'+value.alias+'</td><td>'+value.urlFinal +'</td><td>'+value.qtdAcesso +'</td><td>'+value.urlOriginal+'</td></tr>');    
      }); 
      
    })
    .fail(function(e) {
      console.log( "error", e );
    })
    .always(function() {
      console.log( "complete" );
    });    
    
});  


