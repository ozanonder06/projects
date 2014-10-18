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
        <link href="stylesheet/additem.css" rel="stylesheet">

    </head>
    <body>
        <?php
        include("template/header.php");
        ?>
        <div class="container">
            <!--body -->
            <div class="jumbotron">
                <h1>Thank you!</h1>

                <?php
                error_reporting(0);
                include "template/upload.php";
                // Insert into database 
                $name = $_POST['name'];

                $con = mysqli_connect("sfsuswe.com", "f13g02", "team2pass", "student_f13g02");
                // Check connection
                if (mysqli_connect_errno($con)) {
                    echo "Failed to connect to MySQL: " . mysqli_connect_error();
                }
                if (!$imgurl){
                    $imgurl = 'nophoto.jpg';
                }
                $direction = $_POST['address'] . " " . $_POST['city'] . ", " . $_POST['zip'];
                
                // Geo Id
                $result5 = mysqli_query($con, "SELECT Id FROM Geography WHERE City = '$_POST[city]'");
                while ($row3 = mysqli_fetch_array($result5)) {
                                        $id = $row3['Id'];
                                }
                
                $sql = "INSERT INTO Item (Name, Type, GeographyId, Description, PriceRange, Phone, Address, WebUrl, ImageUrl) 
                VALUES ('$_POST[name]','$_POST[type]','$id' ,'$_POST[description]','$_POST[price]','$_POST[phone]','$direction','$_POST[url]', '$imgurl')";
                
                if (!mysqli_query($con, $sql)) {
                    die('Error: ' . mysqli_error($con));
                }
                $last_insert_id = mysqli_insert_id($con);
                $sql ="SELECT Id FROM Media WHERE MediaUrl = '$imgurl'";
                $media = mysqli_query($con, $sql);
                $row = mysqli_fetch_row($media);
                $media_id = $row[0];
                $sql ="UPDATE  Media SET  ItemId =  '$last_insert_id' WHERE  Media.Id = '$media_id'";
                if (!mysqli_query($con, $sql)) {
                    die('Error: ' . mysqli_error($con));
                }
                mysqli_close($con);                
                header("Refresh: 2; URL= http://sfsuswe.com/~f13g02/item.php?id=" . $last_insert_id);                
                ?>



        </div>
            </div>
                <?php
                include("template/footer.php");
                ?>
    </body>
</html>
