<?php
    session_start(); 
    
     //finds the extension name of given file
     function findexts ($filename) 
     { 
          $filename = strtolower($filename) ; 
          $exts = split("[/\\.]", $filename) ; 
          $n = count($exts)-1; 
          $exts = $exts[$n]; 
          return $exts; 
     }
    
/*
 * Receives additional required information from the user
 * and adds them to the database after validiction
 */
 //check if the seesions were created from register1.php
  if($_SESSION['seller'] && $_SESSION['email'] && $_SESSION['vin'] && $_SESSION['state'] 
    && $_SESSION['year'] && $_SESSION['make'])
    {    
       
      //connect to database
      require_once 'db.php';
      $make = $_SESSION['make'];
  
      //OPEN THE PARANTHESIS WHEN PAGE IS READY!!!!!
    
?>

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
    <link href="css/signin.css" rel="stylesheet">

  </head>

  <body>
      <div class="container">

      <form class="form-signin" method="post" action="register2.php" enctype="multipart/form-data">
        <h2 class="form-signin-heading">Basic Information 2</h2>
        
        <h5> SELECT MODEL </h5> 
         <select name="model"> <!--beginning of dropdown-->
              
         <?php
                
             //retrieves the dropdown of car models depending on the car make
             $result = mysqli_query($con,"SELECT * FROM $make");
    
             while($row = mysqli_fetch_array($result))
             {
                echo '<option value="'. $row['model']. '">'. $row['model'] . '</option>';
                
             }
        ?> 
         </select>   
        
        <h5> SELECT COLOR </h5> 
         <select name="color"> <!--beginning of dropdown-->
              
         <?php
                
             //retrieves the dropdown of car models depending on the car make
             $result = mysqli_query($con,"SELECT * FROM color");
    
             while($row = mysqli_fetch_array($result))
             {
                echo '<option value="'. $row['model']. '">'. $row['model'] . '</option>';
                
             }
        ?> 
         </select> 
        
        <h5> SELECT TRANSMISSION </h5> 
        <!-- RADIOBUTTON FOR TRANSMISSION SELECTION-->
        
          <input type="radio" name="transmission" value="automatic" checked>Automatic </input>
          <input type="radio" name="transmission" value="manual">Manual </input> </br></br>
          
        Enter milage:
        <input type="text" name="milage" class="form-control" placeholder="milage" required> 
        
         Enter price:
        <input type="text" name="price" class="form-control" placeholder="$$$$$$$" required>
        
        Car Description: <br />
        <textarea name="description" value="description" rows="5" cols="40" required></textarea> 
        
        Upload a picture:
        <input type= "hidden" name="upload" value="1">
        <label for="file">Filename:</label>
        <input type="file" name="file" id="file"><br>
        
         <button class="btn btn-lg btn-primary btn-block" name="submit" type="submit">SUBMIT</button>
          
      </form>
      </div>     
      
      <?php
          if (isset($_POST['submit']))
          {
           include_once 'vinDecoder.php';
  
           
           //validate milage
           $milage = $_POST['milage'];
           if(!isNumericInfo($milage))
               exit("Invalid milage entry!");
           
           //validate price
           $price = $_POST['price'];
           if(!isNumericInfo($price))
               exit("Invalid price entry!");
           
           //get user desciption
           $description = $_POST['description'];
           
           $ext = findexts ($_FILES['file']['name']) ;//extension of file
           
           //User can only upload the files in jpeg format
           if(!strcmp($ext,'jpg')==0)
                   exit("PICTURE MUST BE IN JPEG FORMAT");
           
           //Give a random number to the file and uploaded to the server
           //in a folder called photos
           $ran = rand () ;//creates random name for the file
           $ran2 = $ran.".";
           $target = "photos/";
           $target = $target . $ran2.$ext;
            if(move_uploaded_file($_FILES['file']['tmp_name'], $target)) 
            {
                echo $target;
                 //file is uploaded successfully
                 //create sessions 
                  $_SESSION['model']=$_POST['model'];
                  $_SESSION['color']=$_POST['color'];
                  $_SESSION['transmission']=$_POST['transmission'];
                  $_SESSION['description']=$_POST['description'];
                  $_SESSION['picture']=$target;
                  $_SESSION['milage']=$_POST['milage'];
                  $_SESSION['price']=$_POST['price'];

            ?>      
                  <!--redirect to the added.php-->
                  <script language="javascript">
                  location.replace("added.php");
                  </script>
                  
            <?php    
                 
            } 
            else
            {
                echo "Sorry, there was a problem uploading your file.";
            }
          } 
      
       }
       else//sessions werent created user can not see the page
       {
          //redirect the user to the homepage index.php   
      ?>
         <!--REDIRECT THE PAGE TO register2.php-->
         <script language="javascript">
         location.replace("index.php");
         </script>
       
       <?php
       }
       ?>
  </body>
  
</html>  


  

