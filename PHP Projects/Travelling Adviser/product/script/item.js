
var placement;
$(".helpful").click(function(){
    placement = $(this);
  $.post("helpful.php",{ help: ""+$(this).attr("id")},
    function(data){
        placement.text('Helpful: '+data);
  });
});

$(document).ready(function() {
  $('#media').carousel({
    pause: true,
    interval: false,
  });
});