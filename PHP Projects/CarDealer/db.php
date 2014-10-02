<?php
        /*contains necessary infor about our database mysql
         * this file will be called by other files to connect to db
         * also contains the functions that has basic database operations
         * in the car_verify table
         * means not verfied, 1 means verified, 2 means deleted
         */
        $username = "root";
        $password="swimmer06";
        $hostname="localhost";
        $db="student_oonder";
         
        $con = mysqli_connect($hostname, $username, $password, $db)
             or die("Unable to connect");
        
        //adds to the table car with 12 parameters
        function addCar($seller, $email, $vin, $location, $make, $model, $year, $pic, $mil,  $trans, $des, $color, $price)
        {
            $con = mysqli_connect('localhost', 'root', 'swimmer06', 'student_oonder')
             or die("Unable to connect");
            
            $result = mysqli_query($con,"INSERT INTO car(vin, seller, make, model, location, year, 
             color, mil, trans, desciption, email, pic, price) VALUES ('{$vin}','{$seller}','{$make}','{$model}',
             '{$location}','{$year}','{$color}','{$mil}','{$trans}','{$des}','{$email}','{$pic}','{$price}')");
             
             if($result)
               echo "added";
             else
               echo "couldnt add to the database";  
        }
        
        //adds the vin number to the car_verify table with false(0) value
        //this means the car is added but not verified yet.
        //cars that are NOT verified shall not be displayed in the carsale list
        //admininstatior needs to you verify() function to verify the car
         //the vin number must exit in the car table
        //can not enter more than one same vin entry
        
        //in the car_verify table
        // 0 means not verfied, 1 means verified, 2 means deleted
        function addToVerify($vin)
        {
             $con = mysqli_connect('localhost', 'root', 'swimmer06', 'student_oonder')
             or die("Unable to connect");
             
              $result = mysqli_query($con,"INSERT INTO car_verify(vin, isValid)
                                           VALUES ('{$vin}', '0')");
                                           
              if($result)
               echo "added to car_verify";
             else
               echo "couldnt add to the car_verify";                             
             
        }
        
        //verfies a car so it can be displayed on the car sale list
        //connects to the database table car_verify
        //searchs the data with the given vin
        //and changes its isValid value to true
        //the vin number must exit in the car table
        //in the car_verify table
        // 0 means not verfied, 1 means verified, 2 means deleted
        function verifyCar($vin)
        {
            
             $con = mysqli_connect('localhost', 'root', 'swimmer06', 'student_oonder')
             or die("Unable to connect");
             
              $result = mysqli_query($con,"UPDATE car_verify SET isValid = '1' WHERE vin = '{$vin}'");
              
               if($result)
               return true;
             else
               return false;
                                           
        }
        
        //unverifies a car with given vin
        //in the car_verify table
        // 0 means not verfied, 1 means verified, 2 means deleted
        function unverifyCar($vin)
        {
            
             $con = mysqli_connect('localhost', 'root', 'swimmer06', 'student_oonder')
             or die("Unable to connect");
             
              $result = mysqli_query($con,"UPDATE car_verify SET isValid = '0' WHERE vin = '{$vin}'");
              
               if($result)
                 return true;
             else
                 return false;
                                           
        }
        
        //deletes a car
        //by changing isValid attribute to 2.
        //in the car_verify table
        // 0 means not verfied, 1 means verified, 2 means deleted
        function deleteCar($vin)
        {
             $con = mysqli_connect('localhost', 'root', 'swimmer06', 'student_oonder')
             or die("Unable to connect");
             
             unverifyCar($vin); //unverify the car from the list
             
             $result = mysqli_query($con,"UPDATE car_verify SET isValid = '2' WHERE vin = '{$vin}'");
                                           
             if($result)
               return true;
             else
               return false;                             
             
        }
                
 
?>
