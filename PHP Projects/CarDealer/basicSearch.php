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
    <link href="css/basicSearch.css" rel="stylesheet">
  </head>
  
  <body>
        
       <div class="container">

       <!-- Main component for a primary marketing message or call to action -->
       <div class="jumbotron2">
          
         <?php
           include_once 'db.php';
           //connect to the database and retrieve all the cars from the database table car
           //whose verification is approved by the admin
           $result = mysqli_query($con,"SELECT *  FROM car C, car_verify V 
                                 WHERE C.vin = V.vin AND V.isValid=1;");
         
           while($row = mysqli_fetch_array($result))
           {   //generate the results
               //for each result create a form with basic info of the user
         ?>  
             <form method="post" action="seller.php">  
               <!--displays the basic information of the seller-->  
               <p> <img src = "<?php echo $row['pic']; ?>" height="75" width="75"> </img>
                   <input class="btn btn-success" type="submit" name="submit" value="See Details" />
                      <?php echo "<b>Year: </b>", $row['year']; ?>
                      <?php echo "<b>Make: </b>", $row['make'], "  <b>Model:</b> ",$row['model'] ; ?> 
                      <?php echo "<b>Location: </b>", $row['location']; ?>
                      <?php echo " <b>$ </b>", $row['price']; ?>
                      <?php echo " <b>by </b> ", $row['seller']; ?>
                   
                  <!--THE INFORMATION BELOW WILL NOT BE DISPLAYED TO THE USER-->
                  <input type="hidden" name="vin" value="<?php echo $row['vin'];  ?>"/>
                  
                  
                  <hr size="1" color="black"> </hr>
            </form>  
      
       <?php
         }//end of the while loop(serach ends retrieve all cars!)
       ?>      
        </div>
      </div> 
      
      <?php
             
      
      
      ?>
  </body>
</html>  