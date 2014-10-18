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
        RequireLoggedIn();
        ?>
        <div class="container">
            <!--body -->
            <div class="jumbotron">
                <h1>Thank you!</h1>

                <?php
                // Insert into database 
                $UserId = LoggedInUserId();                
                //Recommend into integer
                $recommend = filter_input(INPUT_POST, 'recommend');
                if($recommend == "Yes"){
                    $rec = 1;
                }
                else {
                    $rec = 0;
                }

                $con = mysqli_connect("sfsuswe.com", "f13g02", "team2pass", "student_f13g02");
                // Check connection
                if (mysqli_connect_errno($con)) {
                    echo "Failed to connect to MySQL: " . mysqli_connect_error();
                }
                
                $Venueid = filter_input(INPUT_POST, 'Venueid');
                $rating = filter_input(INPUT_POST, 'rating');
                $comments = filter_input(INPUT_POST, 'comments');
                $currentDate = date("Y-m-d");
                $sql = "INSERT INTO Review (ItemId, UserId, Rate, Review, Recommend, date) 
                VALUES 
                ('$Venueid', '$UserId' , '$rating' ,'$comments', '$rec', '$currentDate' )";

                if (!mysqli_query($con, $sql)) {
                    die('Error: ' . mysqli_error($con));
                }
                
                $result = mysqli_query($con,"SELECT Name FROM Item WHERE Id = '$Venueid'");

                $row = mysqli_fetch_array($result);

                    echo "<div class='alert alert-success'>Review for " . $row['Name'] . " successfully added.</div>";
                header("Refresh: 3; URL = http://sfsuswe.com/~f13g02/item.php?id=" . $Venueid); 
                ?>




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
