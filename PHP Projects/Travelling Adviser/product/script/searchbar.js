 $(function() {
 
    $( ".searchbar" ).autocomplete({
      source: function( request, response ) {
              $.getJSON( "autocomplete.php", {
            term: request.term
          }, response );
      }
    });
  });