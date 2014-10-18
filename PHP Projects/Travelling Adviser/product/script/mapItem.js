var geocoder;
var map;
var bound = new google.maps.LatLngBounds();

function initialize() {
    geocoder = new google.maps.Geocoder();
    map = new google.maps.Map(document.getElementById('map-canvas'));

    loc = $(".address").map(function(){return $(this).text();}).get();
    codeAddress(loc);
}

function codeAddress(address) {
    var counter = 1;
    for (var i = 0; i < address.length; i++) {
        
        geocoder.geocode({'address': address[i]}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var marker = new google.maps.Marker({
                    position: results[0].geometry.location,
                    map: map
                });
                bound.extend(new google.maps.LatLng(results[0].geometry.location.lat(), results[0].geometry.location.lng()));
                map.setCenter(bound.getCenter());
                map.setZoom(10);
            }
        });
        
    }


}

function getBoundsZoomLevel(bounds) {
    var WORLD_DIM = {height: 256, width: 256};
    var ZOOM_MAX = 21;

    function latRad(lat) {
        var sin = Math.sin(lat * Math.PI / 180);
        var radX2 = Math.log((1 + sin) / (1 - sin)) / 2;
        return Math.max(Math.min(radX2, Math.PI), -Math.PI) / 2;
    }

    function zoom(mapPx, worldPx, fraction) {
        return Math.floor(Math.log(mapPx / worldPx / fraction) / Math.LN2);
    }

    var ne = bounds.getNorthEast();
    var sw = bounds.getSouthWest();

    var latFraction = (latRad(ne.lat()) - latRad(sw.lat())) / Math.PI;

    var lngDiff = ne.lng() - sw.lng();
    var lngFraction = ((lngDiff < 0) ? (lngDiff + 360) : lngDiff) / 360;

    var latZoom = zoom(300, WORLD_DIM.height, latFraction);
    var lngZoom = zoom($("#map-canvas").width(), WORLD_DIM.width, lngFraction);

    return Math.min(latZoom, lngZoom, ZOOM_MAX);
}

google.maps.event.addDomListener(window, 'load', initialize);

