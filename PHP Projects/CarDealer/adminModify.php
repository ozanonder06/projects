<?php

/*
 * This page allws admin to verify or delete a car
 * It displays all the information from the user
 * the admin must sign in to see this page
 */
  session_start();
  if (isset($_SESSION['admin']))
  {
      //admin had logged in, display the page
      include_once 'db.php';
      include_once 'header.php';
      $vin = $_POST['vin'];
      
       $result = mysqli_query($con,"SELECT * FROM car WHERE vin = '{$vin}'");
       while($row = mysqli_fetch_array($result))
       {
 ?>

   <!--display the unverified seller profile to the admin-->
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

    <!-- Custom styles for this template -->
    <link href="css/seller.css" rel="stylesheet">

  </head>
  <body>
      <div class="container">  
         <div class="picture">	   
             <img src = "<?php echo $row['pic']; ?>" height="280px" width="380px">
             <p> </p>
	</div>
	
	<div class="description">	   
	 <p>
             <b>Seller: </b> <?php echo $row['seller'], "      "; ?> 
             <b>Vin: </b> <?php echo $row['vin'], "      "; ?> 
             <b>E-mail: </b> <?php echo $row['email'], "      "; ?> 
         
         
         </p>
         <p> <b>Make: </b> <?php echo $row['make']; ?>  <b> Model: </b> <?php echo $row['model']; ?>
             <b>Year: </b> <?php echo $row['year']; ?> 
         </p>
         
         <p> <b>Price: </b> <?php echo "$",$row['price']; ?> </p>
         <p> <b>Color: </b> <?php echo $row['color']; ?> </p>
         <p> <b>Transmission: </b> <?php echo $row['trans']; ?> </p>
         <p> <b>Milage: </b> <?php echo $row['mil']; ?> </p>
         <p> <b>Location: </b> <?php echo $row['location']; ?> </p>
         <p> <b>Description: </b> <?php echo $row['desciption']; ?> </p>

	</div>
      </div>  

      
      <!--verify and delete buttons-->         	
            <div class="admin-button">
               
              <form class="form-signin" method="POST" action="adminDone.php">
                  <input class="btn btn-lg btn-warning" type="submit" name="verify" value="Verify" />
                  <input class="btn btn-lg btn-danger" type="submit" name="delete" value="Delete" />
                 <!--THE INFORMATION BELOW WILL NOT BE DISPLAYED TO THE USER-->
                  <input type="hidden" name="vin" value="<?php echo $row['vin'];  ?>"/>
                
                
              </form>
          
            </div> <!--container-->
            
   
  </body>
</html>



<?php
       }//end of while 
  } //end of if(session set)
  else
  {
      //session is not set 
      //redirect to the home page
  ?>
      <!--REDIRECT THE PAGE TO index.php-->
         <script language="javascript">
         location.replace("index.php");
         </script>

  <?php
  }//end of else statement
?>

