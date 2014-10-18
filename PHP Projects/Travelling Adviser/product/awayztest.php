<?php require_once("include/session.php"); ?>
<?php require_once("include/function.php"); ?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Awayz</title>

        <!-- Bootstrap core CSS -->
        <link href="stylesheet/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="stylesheet/index.css" rel="stylesheet">
        
        <link href="stylesheet/jquery-ui-1.10.3.custom.min.css" rel="stylesheet">

    </head>

    <body>
        <?php
        error_reporting(0);
        include("template/header.php");
        session_start();
        $_SESSION['url'] = $_SERVER['REQUEST_URI'];
        ?>

        <div class="jumbotron" id="contents">

            <div id ="q" class="container">

                <span class="welcome_phrase"><h1>Plan your perfect trip</h1></span>

                <form id="searchForm" name="sForm" class="form-search" method ="get" action="search.php">
                    <div class=""> 
                        <div class="form-inline" role="form">
                            <div class="form-group col-xs-6">
                                <input type="txt"  name="q" class="form-control searchbar" placeholder="City or venue name">
                            </div>
                            <!-- "City" Dropdown -->      <div class="form-group">                    
                                <select id="cityName" class="form-control" name="city" onchange="">
                                    <option value="">Select City (optional)</option>
                                    <option >San Francisco</option>
                                    <option >San Jose</option>
                                    <option >Los Gatos</option>              
                                </select>                             
                            </div>
                            <!-- "Type" Dropdown -->        <div class="form-group">
                                <select class="form-control" id="typeName" name="type" onchange="">
                                    <option value="">Select Type (optional)</option>
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

            <div class="container">

                <div class="recomend"><h3> Recommended Cities to Visit: </h3></div>
                <div class="container">

                    <!--City One Box--><div class="col-lg-4 col-md-4 col-xs-4">
                        <div class="thumbnail">
                            <a href="search.php?q=&city=Los+Gatos" class="thumbnail">  
                                <img src="image/losGatos.jpg" alt="..." width="300" height="150">
                            </a>

                            <div class="caption">
                                <h3 align="center"><strong> Los Gatos, California</strong></h3>
                                <p class="details" align="center">Los Gatos translated in spanish, means 
                                    "The Cats", ironically Los Gatos houses 3x more dogs than cats. 
                                    If your looking for a nice time, Los Gatos is the place to visit
                                </p>
                            </div>
                        </div>
                    </div>    

                    <!--City two Box--><div class="col-lg-4 col-md-4 col-xs-4">
                        <div class="thumbnail">
                            <a href="search.php?q=&city=San+Francisco&type=All+Type" class="thumbnail">
                                <img src="image/sf.jpg" alt="..." class="" width="300" height="150">
                            </a>

                            <div class="caption" >
                                <h3 align="center"> <strong>San Francisco, California</strong></h3>
                                <p class="details" align="center">San Francisco is home of the golden 
                                    gate bridge, a world attraction. With so much diversity, SF has 
                                    some of the best foods, shops, and attractions one can hope for.
                                </p>
                            </div>
                        </div>
                    </div>

                    <!--City 3 Box--> <div class="col-lg-4 col-md-4 col-xs-4">
                        <div class="thumbnail">
                            <a href="search.php?q=&city=San+Jose&type=All+Type" class="thumbnail">
                                <img src="image/sanJose.jpg" alt="..." class="" width="300" height="150">
                            </a>

                            <div class="caption">
                                <h3 align="center"><strong> San Jose, California</strong></h3>
                                <p class="details" align="center">Home of the famous SJ Fries. If your looking for some sun, grub, and fun.
                                    Then come to San Jose where the sun and fun last forever. And where the food
                                    is nothing but "Fantastico!". 
                                </p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>  
            
        </div>

    <?php
    include("template/footer.php");
    ?>
</body>
</html>
