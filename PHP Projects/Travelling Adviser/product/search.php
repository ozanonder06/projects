<!DOCTYPE html>
<html>
    <head>
        <?php include("template/head.php"); ?>

        <!-- Custom styles for this page -->
        <link href="stylesheet/search.css" rel="stylesheet">
    </head>
    <body>
        <?php require_once("include/session.php"); ?>
        <?php require_once("include/function.php"); ?>
        <?php include("template/header.php"); ?>
<div class="container">
        <div class="awayz_body">
            <div class="jumbotron">
                <div class="row">
                    <?php include("template/searchbar.php"); ?>
                </div>
            </div>
            <div class="row">
                <div class="left_pane col-sm-7">
                    <?php
                    $con = mysqli_connect("sfsuswe.com", "f13g02", "team2pass", "student_f13g02");
                    if (mysqli_connect_errno()) {
                        echo "Failed to connect to MySQL: " . mysqli_connect_error();
                    }

                    $uri_query = parse_url($_SERVER['REQUEST_URI'], PHP_URL_QUERY);

                    $pagination = '';
                    $rec_limit = 3;
                    $sql_count = '';
                    $query = '';
                    $q = '';
                    $city = '';
                    $type = '';
                    $sort = '';
                    if (isset($_GET['query']) && !empty($_GET['query']))
                        $query = $_GET["query"];
                    if (isset($_GET['q']) && !empty($_GET['q']))
                        $q = $_GET["q"];
                    if (isset($_GET['city']) && $_GET['city'] != 'All City')
                        $city = $_GET["city"];
                    if (isset($_GET['type']) && $_GET['type'] != 'All Type')
                        $type = $_GET["type"];
                    if (isset($_GET['sort']) && !empty($_GET['sort']))
                        $sort = $_GET["sort"];

                    if (empty($uri_query))
                        $city = "San Francisco";

                    $limit_review = " SELECT Review.ItemId FROM Review, Item WHERE Item.id = Review.ItemId LIMIT 1 ";
                    //$limit_review = "Review.ItemId";
                    

                    if ($city && $q && $type)
                        $sql = " FROM Geography, Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE Geography.City = '$city' AND Geography.Id = Item.GeographyId AND Item.Name LIKE '%$q%' AND Item.Type = '$type'";
                    elseif ($city && $q)
                        $sql = " FROM Geography, Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE Geography.City = '$city' AND Geography.Id = Item.GeographyId AND Item.Name LIKE '%$q%'";
                    elseif ($city && $type)
                        $sql = " FROM Geography, Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE Geography.City = '$city' AND Geography.Id = Item.GeographyId AND Item.Type = '$type'";
                    elseif ($city && $type)
                        $sql = " FROM Geography, Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE AND Item.Name LIKE '%$q%' AND Item.Type = '$type'";
                    elseif ($city)
                        $sql = " FROM Geography, Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE Geography.City = '$city' AND Geography.Id = Item.GeographyId";
                    elseif ($q)
                        $sql = " FROM Geography, Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE (Item.Name LIKE '%$q%' OR '$q' = '') OR (Geography.City LIKE '%$q%' AND Geography.Id = Item.GeographyId)";
                    elseif ($type)
                        $sql = " FROM Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE Item.Type = '$type'";
                    else
                        $sql = " FROM Item LEFT JOIN Review On Item.Id = Review.ItemId";

                    

                    
                    if ($query){
                        $temp = explode(" ", $query);
                        $partial = $temp[0];
                        $sql = " FROM Geography, Item LEFT JOIN Review On Item.Id = Review.ItemId WHERE CONCAT(Item.Name, ' ', Item.Type, ' ', Geography.City) LIKE '%$partial%' AND Geography.Id = Item.GeographyId";
                       
                        $max = sizeof($temp);
                        for ($i=1; $i<$max; $i++){
                            $partial .= $temp[$i];
                            $sql .= " AND CONCAT(Item.Name, ' ', Item.Type, ' ', Geography.City) LIKE '%$partial%' AND Geography.Id = Item.GeographyId";
                        }
                        
                    }
                    
                    $sql .=" GROUP BY Item.id ";
                    
                    if ($sort == 'City')
                        $sql .= " ORDER BY Item.GeographyId";
                    elseif ($sort == 'Price')
                        $sql .= " ORDER BY Item.PriceRange";
                    elseif ($sort == 'Reviews')
                        $sql .= " ORDER BY Review.Rate DESC";
                    elseif ($sort == 'Rating')
                        $sql .= " ORDER BY Review.Rate DESC";
                    else
                        $sql .= " ORDER BY Item.Name";


                    $sql_count .= "SELECT COUNT(*)" . $sql;


                    $retval = mysqli_query($con, $sql_count);
                    if (!$retval) {
                        die('Could not get data: ' . mysqli_error());
                    }
                    $counter = 0;
                    while ($row = mysqli_fetch_array($retval)) {
                        $counter++;
                    }
                    $rec_count = $counter;

                    if (isset($_GET{'page'})) {
                        $page = $_GET{'page'};
                        $offset = $rec_limit * ($page - 1);
                    } else {
                        $page = 1;
                        $offset = 0;
                    }


                    $left_rec = $rec_count - (($page - 1) * $rec_limit);

                    $pagination = '<ul class="pagination">';


                    $last = $page - 1;
                    $next = $page + 1;
                    if ($page > 1 && $left_rec > $rec_limit) {
                        $pagination .= "<li><a href=\"?q=$q&city=$city&type=$type&sort=$sort&page=$last\">Prev</a></li>";
                        for ($pg = 1; $pg <= ceil($rec_count / $rec_limit); $pg++) {
                            if ($pg == $page)
                                $pagination .= "<li class = \"active\"><a>$pg</a></li>";
                            else
                                $pagination .= "<li><a href=\"?q=$q&city=$city&type=$type&sort=$sort&page=$pg\">$pg</a></li>";
                        }
                        $pagination .= "<li><a href=\"?q=$q&city=$city&type=$type&sort=$sort&page=$next\">Next</a></li>";
                    } else if ($page == 1 && $left_rec <= $rec_limit) {
                        $pagination .= '<li class="disabled"><a>Prev</a></li>';
                        $pagination .= '<li class="disabled"><a>Next</a></li>';
                    } else if ($page == 1) {
                        $pagination .= '<li class="disabled"><a>Prev</a></li>';
                        for ($pg = 1; $pg <= ceil($rec_count / $rec_limit); $pg++) {
                            if ($pg == $page)
                                $pagination .= "<li class = \"active\"><a>$pg</a></li>";
                            else
                                $pagination .= "<li><a href=\"?q=$q&city=$city&type=$type&sort=$sort&page=$pg\">$pg</a></li>";
                        }
                        $pagination .= "<li><a href=\"?q=$q&city=$city&type=$type&sort=$sort&page=$next\">Next</a></li>";
                    } else {
                        $pagination .= "<li><a href=\"?q=$q&city=$city&type=$type&sort=$sort&page=$last\">Prev</a></li>";
                        for ($pg = 1; $pg <= ceil($rec_count / $rec_limit); $pg++) {
                            if ($pg == $page)
                                $pagination .= "<li class = \"active\"><a>$pg</a></li>";
                            else
                                $pagination .= "<li><a href=\"?q=$q&city=$city&type=$type&sort=$sort&page=$pg\">$pg</a></li>";
                        }
                        $pagination .= '<li class="disabled"><a>Next</a></li>';
                    }
                    $pagination .='</ul>';


                    $sql = "SELECT Item.*, Item.Id AS \"ii\", Review.*, AVG(Rate), COUNT(*)AS \"NumOfReviews\"" . $sql . " LIMIT $offset, $rec_limit";

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
                    
                    
                    
                    

                    $result = mysqli_query($con, $sql);
                    $count_result = 0;
                    $display_result = '';
                    $userurl = "";
                    if ($result) {
                        while ($row = mysqli_fetch_array($result, MYSQLI_BOTH)) {
                            $count_result++;
                            $display_result .= '<div class ="well well-sm row"><div class="img-descr-row row col-xs-12 col-sm-8">';
                            $display_result .= '<div class ="col-xs-4" ><a href="item.php?id=' . $row['ii'] . '" class="thumbnail"><img src="image/' . $row['ImageUrl'] . '"></img></a></div>';
                            $display_result .= '<div class="col-xs-8 search_description"><div class="col-xs-1" style="padding:0"><h4>'.$count_result.'. </h4></div><a href="item.php?id=' . $row['ii'] . '"><h4>'  . $row['Name'] . '</h4></a> <br>';
                            if (!empty($row['Review']))
                                $display_result .= star(ceil($row['AVG(Rate)'])) . ' (' . $row["NumOfReviews"] . ' reviews)<br>';
                            $display_result .= $row['PriceRange'] . '<br>';
                            $display_result .= $row['Phone'] . '<br>';
                            $display_result .= '<span class="address">' . $row['Address'] . '</span><br>';
                            $display_result .= '<a href="http://' . $row['WebUrl'] . '">' . $row['WebUrl'] . '</a><br>';
                            $display_result .= '</div></div>';
                            $display_result .= '<div class="col-sm-4 recent_comment"><div class="row col-xs-8 col-sm-12">';
                            
                            // User picture fetch
                            $result3 = mysqli_query($con, "SELECT ImageUrl FROM User WHERE Id = '$row[UserId]'");
                                
                            $row3 = mysqli_fetch_array($result3);
                            $userurl = $row3['ImageUrl'];
                            
                            if (!$userurl){
                                $userurl = "nophoto.jpg";
                            }
                            
                            
                            
                            if (!empty($row['Review'])){
                                $review = $row['Review'];
                                if (strlen($review) > 30){
                                    $review = substr($review,0,30). '...';
                                }
                                
                                $display_result .= '<div class ="col-xs-2 col-xs-offset-1 col-sm-offset-0 col-sm-4" ><a href="#" class="thumbnail"><img src="image/' . $userurl . '"></img></a></div><div class="col-xs-9 col-sm-8">"' . $review . '"</div>';
                            }
                            $display_result .= '</div><div class="row">';
                            $display_result .= '<form method="get" action="writeareview2.php">
                                        <input type="hidden" name="id" value="' . $row['ii'] . '" />
                                        <button type="submit" class="btn btn-info">Write a Review</button>
                                        </form></div></div></div>';
                        }
                        echo $pagination;
                        echo '<div class="row"><p class="pg_count">Total results: <span id="rec_count">' . $rec_count . '</span></p></div>';

                        echo $display_result;
                        echo $pagination;
                        echo '<br><br><br>';
                    }
                    mysqli_free_result($retval);
                    mysqli_free_result($result);
                    mysqli_close($con);
                    ?>
                </div>
                <div class="col-sm-5"><br><br>
                    <div id ="map-canvas" class="well"></div>
                </div>
            </div>
            <div class="push"></div>

        </div>
</div>



        <?php
        include("template/footer.php");
        ?>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script src="script/search.js"></script>
        <script src="script/map.js"></script>
    </body>
</html>
