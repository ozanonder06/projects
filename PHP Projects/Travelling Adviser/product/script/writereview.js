
var stars = ["#star_1","#star_2","#star_3","#star_4","#star_5"];

$(".glyphicon").click(function() {
                                switch ($(this).attr("id")) {
                                case "star_1":
                                    select(1);
                               //     $("#star_1").attr('class', 'glyphicon glyphicon-star');
                                    $("#star_input").val("1");
                                    break;
                                case "star_2":
                                    select(2);
                                //    $("#star_2").attr('class', 'glyphicon glyphicon-star');
                                    $("#star_input").val("2");
                                    break;
                                case "star_3":
                                    select(3);
                                //    $("#star_3").attr('class', 'glyphicon glyphicon-star');
                                    $("#star_input").val("3");
                                    break;
                                case "star_4":
                                    select(4);
                                //    $("#star_4").attr('class', 'glyphicon glyphicon-star');
                                    $("#star_input").val("4");
                                    break;
                                case "star_5":
                                    select(5);
                               //     $("#star_5").attr('class', 'glyphicon glyphicon-star');
                                    $("#star_input").val("5");
                                    break;
                            }    
                             
});

function select(star){
    for (var i = 0; i<star; i++){
        $(stars[i]).attr('class', 'glyphicon glyphicon-star');
    }
    for (var j = star; j<5; j++){
        $(stars[j]).attr('class', 'glyphicon glyphicon-star-empty');
    }
}
