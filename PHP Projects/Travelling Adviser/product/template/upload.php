<?php

include("model/db.php"); //include database php file   
?>

<?php

//set cookie so it will remeber the string query
//when the user uploads the file
$imgurl = "";
$uploadalert = "";
$itemId = "";
if (isset($_GET['id'])) {
    $itemId = $_GET['id']; //get string query
}

//finds the extension name of given file
function findexts($filename) {
    $filename = strtolower($filename);
    $exts = split("[/\\.]", $filename);
    $n = count($exts) - 1;
    $exts = $exts[$n];
    return $exts;
}

$terms = "off";
if (isset($_POST['terms'])) {
    $terms = mysqli_real_escape_string($connection, trim($_POST['terms']));
    if (isset($_POST['id'])) {
        $itemId = mysqli_real_escape_string($connection, trim($_POST['id']));
    }
    if (LoggedInUserId() . LoggedIn()) {
        if ($terms === "on") {
            //upload to the server  
            if ($_POST['upload'] == "1") {
                $ext = findexts($_FILES['file']['name']); //extension of file
                $ran = rand();
                $ran2 = $ran . ".";
                $target = "image/";
                $imgurl = $ran2 . $ext;
                $target = $target . $ran2 . $ext;
                if (move_uploaded_file($_FILES['file']['tmp_name'], $target)) {
                    $uploadalert = "<div class='alert alert-success'>Uploaded successfully!</div>";
                    header("Refresh: 2, URL = http://sfsuswe.com/~f13g02/item.php?id=".$itemId);
                } else {
                    $uploadalert = "<div class='alert alert-danger'>Sorry, there was a problem uploading your file.</div>";
                }
            }
            $filename = $target; //name of the selected file
            $userId = LoggedInUserId();
            $item = $_COOKIE['id']; //retrieve the item id
            //user accepts the condition,
            //store database operation 
            $query = "INSERT INTO Media (";
            $query .= " ItemId, UserId, MediaUrl";
            $query .= ") VALUES (";
            $query .= "'{$itemId}',  '{$userId}', '{$imgurl}'";
            $query .= ")";
            $result = mysqli_query($connection, $query);
            //$result = false;
            mysqli_close($connection);
        } else {
            $uploadalert = "<div class='alert alert-danger'>You need to agree to our terms and conditions.</div>";
        }
    } else {
        RequireLoggedIn();
    }
    if ($uploadalert) {
        echo $uploadalert;
    }
}
?>   