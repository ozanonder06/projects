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
        <link href="stylesheet/writeareview2.css" rel="stylesheet">
    </head>
    <body>
        <?php
        error_reporting(0);
        include("template/header.php");
        if (isset($_GET["id"]))
        $item_id = $_GET["id"];
        session_start();
        $_SESSION['url'] = $_SERVER['REQUEST_URI'];
        RequireLoggedIn();
        ?>
        
        <div class="container">
            <!--body -->
            <div class="jumbotron">
                <div class="row">
                <div class="headline">
                    <p><b>Complete your review for 
                        <?php $con = mysqli_connect("sfsuswe.com", "f13g02", "team2pass", "student_f13g02");
                              $result = mysqli_query($con,"SELECT Name FROM Item WHERE Id = '$item_id'");

                            while($row = mysqli_fetch_array($result))
                            {
                                echo "" . $row['Name'] . "";
                            }
                        ?>
                        </b></p>
                    <p>Please provide the following information:</p>
                </div>
                </div>
                
                <form action="writeareview3.php" method="post" class="form-inline row" role="form" enctype="multipart/form-data">
                    <div class="row">
                    <div class="form-group col-lg-2">Rating: 
                        </div> 
                    <div class="rating col-lg-2">

                            <span id='star_1' class='glyphicon glyphicon-star-empty'></span>
                            <span id='star_2' class='glyphicon glyphicon-star-empty'></span>
                            <span id='star_3' class='glyphicon glyphicon-star-empty'></span>
                            <span id='star_4' class='glyphicon glyphicon-star-empty'></span>
                            <span id='star_5' class='glyphicon glyphicon-star-empty'></span>
                            <input id="star_input" type ="hidden" name="rating" value=""/>
                        </div>
                    </div>
                    <div class="row">
                    <div class="form-group col-lg-2">Comments: 
                        </div>
                    <div class="form-group col-lg-8">
                       <textarea class="form-control" name="comments" id="comments" placeholder="Your review helps others learn about great local businesses."></textarea>
                        </div>  
                    </div> 
                    <input type="hidden" name="Venueid" value="<?php echo $item_id?>" />
                    
                    <div class="row">
                    <div class="form-group col-lg-2">Recommend? 
                    </div>    
                    <div class="form-group col-lg-4">
                        <select class="form-control" name="recommend">
                            <option>Yes</option>
                            <option>No</option>
                        </select>
                    </div>
                    <br>
                    
                    <div class="form-group col-lg-2">
                        <input type="submit" value="Submit Review" class="form-control btn btn-info" />
                        </div>
                    </div>
                    
                </form>
                </div>

            </div>
        <?php
        include("template/footer.php");
        ?>
        <!-- Bootstrap core JavaScript -->
        <script src="script/jquery.js"></script>
        <script src="script/bootstrap.min.js"></script>
        <script src="script/writereview.js"></script>
    </body>
</html>