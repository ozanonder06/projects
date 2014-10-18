<link href="stylesheet/carousel.css" rel="stylesheet">
<?php include "template/modal.php" ?>
<div id="demo" class="cEnd tabs-container">
    <div class="carousel widget">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="mid">
                    <img src="image/<?php echo $row["ImageUrl"]; ?>" alt="image of place" class="img-responsive">
                </div>
            </div>
        </div>


        <?php
        $result_media = mysqli_query($con, "SELECT Media.MediaUrl, Media.Id FROM Media WHERE Media.ItemId = '$item_id'");
        $get_count_media = mysqli_query($con, "SELECT COUNT(Media.MediaUrl) FROM Media, Item WHERE Media.ItemId = '$item_id'");
        $count_media = mysqli_fetch_array($get_count_media);
        ?>


        <div class="jCarouselLite" >
            <ul>
                <?php while ($row_media = mysqli_fetch_array($result_media)) { ?>
                    <li><a class="<?php echo "id=". $row['Id']?>" href="#" data-toggle="modal" data-target="#myModal"><img src="image/<?php echo $row_media["MediaUrl"] ?>" alt="" class="img-thumbnail carousel-thumb"></a></li>
                <?php } ?>
            </ul>
        </div>
        <button class="prev carousel-btn btn btn-info"><b>&lt;&lt;</b></button> <button class="next carousel-btn btn btn-info"><b>&gt;&gt;</b></button>
        <div class="clear"></div>   
    </div>
</div>