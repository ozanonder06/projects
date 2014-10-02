<?php session_start(); ?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <?php
       //INCLUDE HEADER
       require_once 'header.php';
     ?>  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Post your car!</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/register.css" rel="stylesheet">

  </head>

  <body>

   <div id="register_form">   
   

      <form class="form-signin" method="get" action="register1.php">
        <h2 class="form-signin-heading">Basic Information</h2>
         Enter your username
        <input type="text" name="seller" size="3" class="form-control" placeholder="username" required>
        
        Enter your email address
        <input type="text" name="email" class="form-control" placeholder="email adress" required>
        Confirm your email address
        <input type="text" name="email2" class="form-control" placeholder="reenter email adress" required>
        Enter your car's vin
        <input type="text" name="vin" class="form-control" placeholder="vin" required>
        
        <h5> SELECT YOUR LOCATION </h5> 
         <select name="state"> <!--beginning of dropdown-->
              
         <?php
            //connect to database
             require_once 'db.php';
    
             $result = mysqli_query($con,"SELECT * FROM states");
    
             while($row = mysqli_fetch_array($result))
             {
                echo '<option value="'. $row['model']. '">'. $row['model'] . '</option>';
                //<option value="CAR_BRAND"> CAR_BRAND </option>
             }

        ?> 
       </select>   <!--end of dropdown-->
       
       <h5> SELECT the year your car was made </h5> 
         <select name="year"> <!--beginning of dropdown-->
              
         <?php

           /*generates the valid years that are displayed in the dropdown
            * it displays all the years since 1980 to current year
            */
           $year = 1980;
           $currentYear = date('Y');
           while($year<=$currentYear)
           {
               echo '<option value="'. $year. '">'. $year . '</option>';
               $year++;
           }    
           

        ?> 
       </select>   <!--end of dropdown-->
       
       
        <h5> SELECT YOUR CAR'S Make</h5> 
         <select name="make"> <!--beginning of dropdown-->
              
         <?php
            //connect to database
             require_once 'db.php';
    
             $result = mysqli_query($con,"SELECT * FROM manufacturer");
    
             while($row = mysqli_fetch_array($result))
             {
                echo '<option value="'. $row['car']. '">'. $row['car'] . '</option>';
                //<option value="CAR_BRAND"> CAR_BRAND </option>
             }

        ?> 
       </select>   <!--end of dropdown-->
        <p> <button class="btn btn-lg btn-warning"" name="submit" type="submit">SUBMIT</button> </p>
      </form>

   
   </div> <!--register_form-->

    
    <?php
        //checks if the user presses the submit button
        if (isset($_GET['submit']))
        {    
            include_once 'vinDecoder.php';
            
             // get input values
             $seller = $_GET['seller'];
             $email = $_GET['email'];
             $email2 = $_GET['email2'];
             $vin = trim($_GET['vin']);
             //vin must be upper case
             $vin = strtoupper($vin);
             $state = $_GET['state'];
             $yr = (integer) $_GET['year'];
             $make = $_GET['make'];
             
             $decoder = new vinDecoder;
             
             //validates username
             if(!isUserCorrect($seller))
                 exit("Invalid username");
             
             //validates email address and checks if reentry of the email address matches
             if(!isEmail($email))
                exit("Not a valid email address");
             
             if(!emailMatch($email, $email2))
                exit("e-mail addresses do not match!");
             
             //checks if the vin number is valid
             if(!$decoder->isVin($vin))
                exit("vin must have 17 characters");
             
       
             //validates the vin by checking the year and model of the car
             if(!$decoder->isYear($vin, $yr) || !$decoder->isMake($vin, $make))
               exit("vin is not valid. Please recheck vin ,model and year!");   
             
             //create sessions to send the data to register2.php
             $_SESSION['seller']=$seller;
             $_SESSION['email']=$email;
             $_SESSION['vin']=$vin;
             $_SESSION['state']=$state;
             $_SESSION['year']=$yr;
             $_SESSION['make']=$make;
             
                                  
    ?>
       <!--REDIRECT THE PAGE TO register2.php-->
       <script language="javascript">
       location.replace("register2.php");
       </script>
    <?php
        }//end of validation process after setting the submit button
    ?>


  </body>
</html>
