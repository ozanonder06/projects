<?php
$item_id;
$ishelpful = true;
if (isset($_REQUEST['help'])){
      $item_id = $_REQUEST["help"];
}

if (isset($_REQUEST['nothelp'])){
      $item_id = $_REQUEST["nothelp"];
      $ishelpful=false;
}


$con = mysqli_connect("sfsuswe.com", "f13g02", "team2pass", "student_f13g02");
// Check connection
if (mysqli_connect_errno($con)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$result = mysqli_query($con, "SELECT Nrhelp FROM Review WHERE id='$item_id'");
$row = mysqli_fetch_array($result);
if ($ishelpful){
if (!empty($row[0])){
    echo $row[0]+1;
    mysqli_query($con,"UPDATE Review SET Nrhelp=Nrhelp+1 WHERE id='$item_id'");
} else {
    echo 1;
    mysqli_query($con,"UPDATE Review SET Nrhelp= 1 WHERE id='$item_id'");
}
}else {
   if (!empty($row[0])){
    echo $row[0]-1;
    mysqli_query($con,"UPDATE Review SET Nrhelp=Nrhelp-1 WHERE id='$item_id'");
} else {
    echo -1;
    mysqli_query($con,"UPDATE Review SET Nrhelp= -1 WHERE id='$item_id'");
} 
}



mysqli_close($con);

?>
