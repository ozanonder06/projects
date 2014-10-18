<?php require_once("include/session.php"); ?>
<?php require_once("include/function.php"); ?>
<!DOCTYPE html>
<html>
    <head>
        <?php include "template/head.php" ?>

        <!-- Custom styles for this page -->
        <link href="stylesheet/item.css" rel="stylesheet">
        <link href="stylesheet/comments.css" rel="stylesheet">


    </head>
    <body>
        <?php
        include("template/header.php");
        ?>

        <div class="container">
            <div class="jumbotron">
                <?php
                $con = mysqli_connect("sfsuswe.com", "f13g02", "team2pass", "student_f13g02");
                if (mysqli_connect_errno()) {
                    echo "Failed to connect to MySQL: " . mysqli_connect_error();
                }
                $item_id = -1;
                if (isset($_GET["id"]))
                    $item_id = $_GET["id"];
                $sql = "SELECT Item.*, Review.* FROM Review RIGHT JOIN Item On Item.Id = Review.ItemId WHERE Item.Id = '$item_id'";
                $result = mysqli_query($con, $sql);
                $row = NULL;
                if ($result) {
                    $row = mysqli_fetch_array($result);
                }
                $item_name = $row["Name"];

                // Convert the star nr from the Database to graphic stars
                function star($star_val) {
                    $tmp = '';
                    if ($star_val) {
                        for ($t = 0; $t < $star_val; $t++)
                            $tmp .= '<span class="glyphicon glyphicon-star"></span>';
                        for ($t = 0; $t < 5 - $star_val; $t++)
                            $tmp .= '<span class="glyphicon glyphicon-star-empty"></span>';
                    }
                    return $tmp;
                }

                // Number of Recommend = Yes:
                $result3 = mysqli_query($con, "SELECT * FROM Review WHERE ItemId = '$item_id' AND Recommend = 1");
                $reccounts = mysqli_num_rows($result3);

                // Average star rating
                $result4 = mysqli_query($con, "SELECT AVG(Rate) FROM Review WHERE ItemId = '$item_id'");
                $row4 = mysqli_fetch_array($result4);
                $starav = $row4['AVG(Rate)'];

                // Number of reviews
                $result5 = mysqli_query($con, "SELECT * FROM Review WHERE ItemId = '$item_id'");
                $revcounts = mysqli_num_rows($result5);
                ?>

                <h2><?php echo $item_name; ?></h2>
                <div class="row">                    
                    <div class ="col-md-4">
                        <?php include "template/carousel.php" ?>


                    </div>

                    <div class = "col-md-5">
                        <p> <b> Address: </b><span class="address"><?php echo $row["Address"]; ?></span></p>
                        <p> <b> Phone: </b><?php echo $row["Phone"]; ?></p>
                        <p> <b>Type: </b><?php echo $row["Type"]; ?></p>
                        <p> <b>Description: </b><?php echo $row["Description"]; ?></p>
                        <p> <b>Price Range: </b><?php echo $row["PriceRange"]; ?></p>
                        <p> <b>Website: </b><?php echo '<a href="http://' . $row["WebUrl"] . '">' . $row["WebUrl"] . '</a>'; ?></p>

                        <p><b> Rating: <?php
                                $val = star(ceil($starav));
                                echo $val;
                                ?></b> (<?php echo $revcounts . " reviews" ?> ) 
                        </p>
                        <p> <b> Total Recommendations: </b> <?php echo $reccounts ?> <span class="glyphicon glyphicon-thumbs-up"></span></p>
                    </div> 


                    <div class="col-md-3">

                        <div class="col-md-12">
                            <div class ="row">
                                <form method="get" action="writeareview2.php">
                                    <input type="hidden" name="id" value="<?php echo $item_id ?>" />
                                    <button type="submit" class="form-control btn  btn-sm btn-info">Write a Review    <span class="glyphicon glyphicon-pencil"></span></button>
                                </form>
                            </div>

                            <div class="row">
                                <form method="get" action="addmedia.php">
                                    <input type="hidden" name="id" value="<?php echo $item_id ?>" />
                                    <button type="submit" class="form-control btn btn-sm btn-default">Add Photo    <span class="glyphicon glyphicon-camera"></span></button>
                                </form>
                            </div>

                            <div class="row">
                                <form method="get" action="addmedia.php">
                                    <input type="hidden" name="id" value="<?php echo $item_id ?>" />
                                    <button type="submit" class="form-control btn btn-sm btn-default">Add Video    <span class="glyphicon glyphicon-film"></span></button>
                                </form>
                            </div>

                            <div class="row">
                                <input type="button" class="form-control btn btn-sm btn-success" value="Book now!" onclick="location.href = '<?php echo "http://" . $row["WebUrl"] ?>';"></input>
                            </div>
                            <br>
                        </div>
                        <div>
                            <div id ="map-canvas" class="well"></div>
                        </div>



                    </div>

                </div> 


                <br>

                <?php
                $result = mysqli_query($con, "SELECT * FROM Item i, Review r WHERE i.Id = '$item_id' AND i.Id = r.ItemId");
                $get_count = mysqli_query($con, "SELECT COUNT(*) FROM Item i, Review r WHERE i.Id = '$item_id' AND i.Id = r.ItemId");
                $count_reviews = mysqli_fetch_array($get_count);
                ?>

            </div>
            <div class="col-sm-12">
                <div class="row">
                    <div class="panel panel-default widget">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-comment"></span>
                            <h3 class="panel-title">
                                Recent Comments</h3>
                            <span class="label label-info">
                                <?php echo $count_reviews[0] ?></span>
                        </div>
                        <div class="panel-body">
                            <ul class="list-group">



                                <?php
                                while ($row = mysqli_fetch_array($result)) {
                                    if ($row['Recommend'] == "1") {
                                        $rec = "Yes";
                                    } else {
                                        $rec = "No";
                                    }
                                    $result2 = mysqli_query($con, "SELECT Name FROM User WHERE Id = '" . $row['UserId'] . "'");
                                    $row2 = mysqli_fetch_array($result2);
                                    $name = $row2['Name'];
                                    if (!$name) {
                                        $name = "Anonymous";
                                    }
                                    $result3 = mysqli_query($con, "SELECT ImageUrl FROM User WHERE Id = '" . $row['UserId'] . "'");
                                    $row3 = mysqli_fetch_array($result3);
                                    $userurl = $row3['ImageUrl'];
                                    if (!$userurl) {
                                        $userurl = "nophoto.jpg";
                                    }


                                    $value = star($row['Rate']);


                                    echo '<li class="list-group-item"><div class="row"><div class="col-xs-2 col-md-1">';
                                    echo '<img src="image/' . $userurl . '" class="img-circle img-responsive" alt="member image" />';
                                    echo '</div>';
                                    echo '<div class="col-xs-10 col-md-11">';
                                    echo '<div>';
                                    echo 'Rating: ' . $value . ' Recommend: ' . $rec;
                                    echo '<div class="mic-info">By: <a href="#">' . $name . '</a> on ' . date('M. d, Y', strtotime($row['date'])) . '</div></div>';
                                    echo '<br><div class="comment-text">' . $row['Review'] . '</div>';
                                    echo "<br><div class='comment-text'><button id =\"" . $row["Id"] . "\" class='helpful btn btn-info btn-xs'>Helpful: " . $row['Nrhelp'] . "</button></div>";
                                    echo '</div></div></li>';
                                }
                                ?>  
                            </ul>

                        </div>
                    </div>
                </div>

            </div>
        </div>


        <?php
        include("template/footer.php");
        ?>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script src="script/mapItem.js"></script>
        <script src="script/item.js"></script>
        <script src="script/gallery.js"></script>
        <script src="script/carousel.js"></script>


    </body>
</html>

