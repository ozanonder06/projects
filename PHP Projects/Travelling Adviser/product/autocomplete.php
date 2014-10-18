<?php
    $q=filter_input(INPUT_GET, 'term');
    //$q = $_GET('term');
    $auto= array();
    $arr = array('a' => 1, 'b' => 2, 'c' => 3, 'd' => 4, 'e' => 5);
    if ($q){
        include 'model/db.php';
        $sql = "SELECT  Geography.City FROM  Geography WHERE Geography.City LIKE '%$q%'";
        $sql2 =  "SELECT  Item.Name FROM  Item WHERE Item.Name LIKE '%$q%'";
        $result_city = mysqli_query($connection, $sql);
        
        $result_item =  mysqli_query($connection, $sql2);
        
        while ($row = mysqli_fetch_array($result_item)){
            $auto[] = $row['Name'];
        }
        while ($row = mysqli_fetch_array($result_city)){
            $auto[] = $row['City'];
        }
        
        
        echo json_encode($auto);
        //echo json_encode($arr);
    }
    


?>