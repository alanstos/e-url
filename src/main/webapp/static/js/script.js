var copyTextareaBtn = document.querySelector('.copiar');

copyTextareaBtn.addEventListener('click', function(event) {

    console.log('click click');

    var copyTextarea = document.querySelector('.link-short');
    copyTextarea.select();

    document.execCommand('copy');
});

$("#form-short").submit(function(event){
    event.preventDefault();
    
    var dado = {
    		url: document.querySelector('#url').value,
    		customAlias: document.querySelector('#key').value
    		};
    
    var uri = '/encurtar/criar';
    //var uri = '/hire.me/encurtar/criar';
    
    $.ajax( {
    	url: uri,
    	data: dado,
    	method :'POST'
    })
    .done(function(obj) {
      console.log( "success" );
      console.log(obj);
      
      if (obj.hasOwnProperty('err_code')) { 
    	  alert(obj.description);
    	  return;
      }
      
      $("#result-link").show();
      
      document.querySelector('#link-gerado').value = obj.url;
      
    })
    .fail(function(e) {
      console.log( "error", e );
    })
    .always(function() {
      console.log( "complete" );
    });
    
});



