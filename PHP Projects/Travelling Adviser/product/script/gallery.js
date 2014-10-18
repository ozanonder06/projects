$(function() {
    var countlist = $(".jCarouselLite li").length;
    if (countlist > 3){
        countlist = 3;
    } else {
        $(".carousel-btn").remove();
    }
    $(".jCarouselLite").jCarouselLite({
        btnNext: ".next",
        btnPrev: ".prev",
        visible: countlist,
        speed: 200,
        easing: "easeinout"
    });    
    
    $(".jCarouselLite img").click(function(){
        $(".gal-img").attr("src", $(this).attr("src"));
        
    });
});
