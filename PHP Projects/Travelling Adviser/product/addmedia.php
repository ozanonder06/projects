<?php require_once("include/session.php"); ?>
<?php require_once("include/function.php"); ?>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Awayz</title>

        <!-- Bootstrap core CSS -->
        <link href="stylesheet/bootstrap.css" rel="stylesheet">

        <!-- Styles for template -->
        <link href="stylesheet/template.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="stylesheet/addmedia.css" rel="stylesheet">

    </head>
    <body>
        <div id="wrapper">
            <?php
            error_reporting(0);
            include("template/header.php");
            if (isset($_GET["id"]))
            $item_id = $_GET["id"];
            
            ?>
            <div id="main">
                <div class="container">
            <?php
            include("template/upload.php");
            ?>
                    <div class="jumbotron">

                    <form class="form-horizontal row" role="form" action="addmedia.php" method="post" enctype="multipart/form-data">
                        <div class="headline">
                        <p><b>Upload your photo or video for <?php 
                              $con = mysqli_connect("sfsuswe.com", "f13g02", "team2pass", "student_f13g02");
                              $result = mysqli_query($con,"SELECT Name FROM Item WHERE Id = '$item_id'");

                            while($row = mysqli_fetch_array($result))
                            {
                                echo "" . $row['Name'] . "";
                            }
                        ?>
                            </b></p>
                        </div>
                        
                            <br>
                            
                        <div class="form-group col-xs-2">
                            <label for="exampleInputFile">File Input: </label>
                        </div>  
                            <div class="form-group col-xs-10">
                                <input type= "hidden" name="upload" value="1">
                                <input type="hidden" name="id" value="<?php echo $itemId?>" />
                                <input type="file" name="file" id="exampleInputFile">
                                
                           
                        </div>
                        <div class="form-group col-xs-10">
                            
                                <div class="checkbox">
                                    <label>
                                        
                                        <input type="checkbox" name="terms"/>
                                        I agree to <a href="privacy.php">terms of service & privacy policy</a>.
                                    </label>
                                </div>
                            
                        </div>

                        <div class="form-group col-xs-10">
                            
                                <input type="submit" class="btn btn-primary" name="submit" value="Submit">
                                <button type="button" class="btn btn-default" onclick="window.location.href = 'index.php'">
                                    Cancel</button>
                            
                        </div>
                    </form>



                </div>
              </div>
            </div>
        </div>
            </div>
        <?php
        include("template/footer.php");
        
        ?>
        <!-- Bootstrap core JavaScript -->
        <script src="script/jquery.js"></script>
        <script src="script/bootstrap.min.js"></script>
    </body>
</html>
