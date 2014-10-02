<?php

/*
 * display admin that it is successfully verified
 * or deleted BY connecting to the database and updating
 * the data in the car table.
 */
 session_start();
 $message ="";//message to display to the admin 
  if (isset($_SESSION['admin']))
  {
      //admin had logged in, display the page
      include_once 'db.php';
      include_once 'header.php';
      
      if (isset($_POST['verify']))
      {    
         $vin = $_POST['vin'];         
         if(verifyCar($vin))  
           $message = "VERFIED SUCCESSFULLY!";
         else
           $message = "AN ERROR OCCURED. PLEASE TRY AGAIN LATER"; 
      }
      else if (isset($_POST['delete']))
      {    
         $vin = $_POST['vin'];         
         deleteCar($vin);
         if(deleteCar($vin))  
           $message = "CAR DELETED!";
         else
           $message = "AN ERROR OCCURED. PLEASE TRY AGAIN LATER"; 
      }
  } 
  else//if session is not set
  {
      echo "NO BUTTON!!!!";
  }
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
  </head>

  <body>
      <?php
        echo"<h1>",$message,"</h1>";
      ?>
      
  </body>
</html>  
