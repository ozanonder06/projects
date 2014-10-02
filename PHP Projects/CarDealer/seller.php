
<?php
  include_once 'header.php';

  /*
   * Displays the seller profile with the general description of the car
   * also shows pictures and enables user to contact with the seller
   */
   if (isset($_POST['submit']))
   {
        $vin = $_POST['vin'];
        include_once 'db.php';
        //retrieves the dropdown of car models depending on the car make
        $result = mysqli_query($con,"SELECT * FROM car WHERE vin = '{$vin}'");
        while($row = mysqli_fetch_array($result))
        {
           
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

    <!-- Custom styles for this template -->
    <link href="css/seller.css" rel="stylesheet">

  </head>
  <body>
      <div class="container">  
    <div class="picture">	   
      <img src = "<?php echo $row['pic']; ?>" height="280px" width="380px">
	</div>
	
	<div class="description">	   
	 <p> <b>Seller: </b> <?php echo $row['seller']; ?> </p>
         <p> <b>Make: </b> <?php echo $row['make']; ?>  <b> Model: </b> <?php echo $row['model']; ?></p>
         <p> <b>Year: </b> <?php echo $row['year']; ?> </p>
         <p> <b>Price: </b> <?php echo "$",$row['price']; ?> </p>
         <p> <b>Color: </b> <?php echo $row['color']; ?> </p>
         <p> <b>Transmission: </b> <?php echo $row['trans']; ?> </p>
         <p> <b>Location: </b> <?php echo $row['location']; ?> </p>
         <p> <b>Description: </b> <?php echo $row['desciption']; ?> </p>

	</div>
	
	<div class="admin-button">	   
	

      <form class="form-signin">
        <h2 class="form-signin-heading">Contact with Seller</h2>
		 Enter your E-mail Address:
        <input type="text" class="form-control" placeholder="Email address" required autofocus>
		 Message to Seller:
        <textarea name="description" value="description" rows="3" cols="60" required></textarea> 
        <button class="btn btn-lg btn-warning" type="send">Send</button>
        
      </form>

     
      </div>
    </div>  <!-- /container -->

  </body>

</html> 




<?php
        }//end of while loop
        
  }
  else
  {
    //invalid entry, display the message below
    echo "<h1> Sorry, this post has been removed </h1>";
  }    
?>
