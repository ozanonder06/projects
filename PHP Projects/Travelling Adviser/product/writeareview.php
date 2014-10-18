<?php require_once("include/session.php"); ?>
<?php require_once("include/function.php"); ?>
<!DOCTYPE html>
<html>
    <head>
<?php include "template/head.php" ?>
        <link href="stylesheet/writeareview.css" rel="stylesheet">

    </head>
    <body>
        <?php
        include("template/header.php");
        ?>
        <!--body -->
        <div class="container">
        <div class="jumbotron">
            
            <div class="headline">
                    <p><b>1) Search for venue <img src="image/lupe.jpg" width="20" height="20"> &nbsp &nbsp 2) Select the venue <img src="image/map.jpg" width="20" height="20"> &nbsp &nbsp 3) Write your review! <img src="image/review.jpg" width="20" height="20"></b></p>
                    <br>
                    <p>Which Venue Would You Like to Review?</p>
                </div>

            <!--search bar -->
                <form id="searchForm" name="sForm" class="form-search" method ="get" action="search.php">
                    <div class=""> 
                        <div class="form-inline" role="form">
                            <div class="col-xs-2">
                            </div>
                            <div class="form-group col-xs-4">
                                <input type="txt"  name="q" class="form-control searchbar" placeholder="City or venue Name">
                            </div>
                            <div class="form-group">
                                <select id="cityName" class="form-control" name="city" onchange="">
                                    <option value="">Select City</option>
                                    <option >San Francisco</option>
                                    <option >San Jose</option>
                                    <option >Los Gatos</option>
                                                  
                                </select>                             
                            </div>
                            <div class="form-group">
                                <select class="form-control" id="typeName" name="type" onchange="">
                                    <option value="">Select Type</option>
                                    <option >Restaurant</option>
                                    <option >Hotel</option>
                                    <option >Attraction</option>  
                                    <option >Shopping Center</option>
                                    <option >Coffee Shop</option>
                                    <option >Bar</option>
                                    <option >Other</option>
                                </select>
                            </div>
                            <button class="btn btn-info" type="submit"> Search </button>

                        </div>
                    </div>     
                </form>

        </div>
        </div>




    <?php
    include("template/footer.php");
    ?>
</body>
</html>
