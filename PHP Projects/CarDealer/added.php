<?php
   session_start(); 
   
   if($_SESSION['seller'] && $_SESSION['email'] && $_SESSION['vin'] && $_SESSION['state'] 
     && $_SESSION['year'] && $_SESSION['make'] && $_SESSION['model'] && $_SESSION['color'] && $_SESSION['transmission']
     && $_SESSION['description'] && $_SESSION['picture'] && $_SESSION['milage'] && $_SESSION['price'])
   {
       require_once 'db.php';
       
       //SESSIONS are created add the information to the database
       $seller = trim($_SESSION['seller']);
       $email =  $_SESSION['email'];
       $vin =    $_SESSION['vin'];
       $location = $_SESSION['state'];
       $make = $_SESSION['make'];
       $model = $_SESSION['model'];
       $color = $_SESSION['color'];
       $trans = $_SESSION['transmission'];
       $des = trim($_SESSION['description']);
       $pic = $_SESSION['picture'];
       $mil = trim($_SESSION['milage']);
       $year = $_SESSION['year'];
       $price = $_SESSION['price'];
       
       echo $seller, "<br>";
       echo $email, "<br>";
       echo $vin, "<br>";
       echo $location, "<br>";
       echo $make, "<br>";
       echo $model, "<br>";
       echo $year, "<br>";
       echo $pic, "<br>";
       echo $mil, "<br>";
       echo $trans, "<br>";
       echo $des, "<br>";
       echo $color, "<br>";
       echo "$",$price, "<br>";
       
       //add the data to the database table car
       addCar($seller, $email, $vin, $location, $make, $model, $year, $pic, $mil, $trans, $des, $color, $price);
       
       //add the car to the car_verify list(this means the car will not be posted until the admin verfies it
       addToVerify($vin);
       
       //session_destroy();
       
   }  
   else
   {
     //REDIRECT TO error.php
 ?>
   <!--REDIRECT THE PAGE TO error.php-->
       <script language="javascript">
       location.replace("error.php");
       </script>

<?php
   }     
  
?>
