<?php

function redirect_to($new_location) {
    header("Location: " . $new_location);
    exit;
}

//Retrieve name of logged in user
function LoggedInUserName() {
    return $_SESSION['user_name'];
}

//Retrieve id of logged in user
function LoggedInUserId() {
    return $_SESSION['user_id'];
}

//Check if any user is logged in
function LoggedIn() {
    return isset($_SESSION['user_id']);
}

//Enfore user to be logged in to view that page
function RequireLoggedIn() {
    if (!LoggedIn()) {
        redirect_to("login.php");
    }
}

?>