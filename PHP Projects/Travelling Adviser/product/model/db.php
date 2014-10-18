<?php

define("DB_SERVER", "sfsuswe.com");
define("DB_USER", "f13g02");
define("DB_PASS", "team2pass");
define("DB_NAME", "student_f13g02");

// 1. Create a database connection
$connection = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME);
// Test if connection succeeded
if (mysqli_connect_errno()) {
    die("Database connection failed: " .
            mysqli_connect_error() .
            " (" . mysqli_connect_errno() . ")"
    );
}
?>