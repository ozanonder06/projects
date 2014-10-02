<?php
  session_start(); 
  
  require_once 'db.php';

/*
 * @Ozan Gazi Onder
 * This page gives access to the adminitsration
 * to verify and delete the cars that are posted by the users
 * admin must enter the password to gain access.
 * If admin logs in it creates a session and retrieves all the cars 
 * that are not verified else
 * it displays a login entry
 */
include_once 'header.php';
?>
<!DOCTYPE html>
<html lang="en">
  <head>
        
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Entry</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/seller.css" rel="stylesheet">

  </head>
  
</body>


<?php

      if (!isset($_SESSION['admin']))
      { //session is not set. display password field 
?>
    <div class="picture">
      <form class="form-signin" method="post" action="adminEntry.php">
         Password:
         <!-- admin entry password required-->
         <input type="text" class="form-control" name="name" placeholder="name" required autofocus> </input>
         <input type="password" class="form-control" name="password" placeholder="password" required autofocus> </input>
         <button class="btn btn-lg btn-warning"  name="submit" type="submit">Sign in</button>        
      </form>
    </div>    
 
<?php
  }//end of Session[admin]
  else
  {
      //session is created display unverfied cars!
      // retrieve all the cars that are not verfied
       $result = mysqli_query($con,"SELECT *  FROM car C, car_verify V 
                              WHERE C.vin = V.vin AND V.isValid=0;");
       while($row = mysqli_fetch_array($result))
       {   
               //generate the results
               //for each result create a form with basic info of the user
 ?>
  <!--display the cars-->
   <form method="post" action="adminModify.php">  
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
       }//end of while loop(all the results were generated
     
  } //end of else   
?>

 </body>
  
</html> 

<?php
  //checks name and password if true then creates session for the admin
   if (isset($_POST['submit']))
   {
       if(!strcmp($_POST['name'], "admin")==0)
       {
           exit("Invalid username or password");
       }  
       
       $pass = md5($_POST['password']); //encrypt the password
       //retrieve the encripted version of the password from the database
       $result = mysqli_query($con,"SELECT * FROM admin");
       while($row = mysqli_fetch_array($result))
       {
           if(strcmp($pass, $row['password'])==0)
           {
               //correct password create the session
               //refresh the page
               $_SESSION['admin']="admin";
           ?>
           <!-- refresh the web page-->
            <script language="javascript">
               location.replace("adminEntry.php");
            </script>
           <?php
           }
           else
           {   //wrong password
               exit("Invalid username or password");
           }    
       }
       
   }

?>

