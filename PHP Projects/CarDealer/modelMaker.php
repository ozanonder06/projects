<?php

     //@author: ozan gazi onder

     //scans the files with given extension format in a given directory
     //and creates  tables with their names(excluding their extension)
     //and enters each line of a file's contents to the corresponding table
     //arguments directory name: name of directory, ext is extension (ie ".txt")
     //others are database connections arguments
     // current directory is "."
     //example $directoryName = ".";
     //$ext = ".txt";
     //createDatabaseFromDirectory($directoryName,$ext,"oonder","abc", "localhost", "student_oonder");

     function createDatabaseFromDirectory($directoryName,$ext, $user, $pass, $host, $dbName)
     {
         // create directory pointer
        $dp = opendir($directoryName) or die ('ERROR: Cannot open directory');
        // read directory contents
        // print filenames found
        while ($file = readdir($dp)) 
        {
          if ($file != '.' && $file != '..')
          {
             if(substr($file, strlen($file)-4, 4)==$ext)
             {
                //calls loadModels function
                  loadModels($file, $user, $pass, $host, $dbName);
                echo "$file <br>"; 
             }
          }
        }
        // destroy directory pointer
       closedir($dp);
    }        
          
     /*opens the a text file and creates a database table with file name
     *and adds its contents to the databas by line
     *four arguments are:filename, db username, db hostname, name of db
     */ 
    
    function loadModels($fileName, $user, $pass, $host, $dbName)
    {
          //modify the file name to remove .txt(or another extension) and make it lowercase
          $len = strlen($fileName);
          $tableName = substr($fileName, 0, ($len-4));
          $tableName = strtolower($tableName);
          
          
          //connect to the database
          $username = "$user";
          $password="$pass";
          $hostname="$host";
          $db="$dbName";
     
        
          $con = mysqli_connect($hostname, $username, $password, $db)
                 or die("Unable to connect");
    
          //create table with our modified file name
         $result = mysqli_query($con,"CREATE TABLE $tableName (model varchar(50) );");
         
         if($result)
         {
             //open text file
             // read file into array with new lines
             $arr = file($fileName); //enter input as loadModel(ie: honda.txt)
             if($arr)
             {    
                 foreach ($arr as $line) 
                 {
                    //for each line add a new entry to the file
                    //create table with our modified file name
                    $model = strtolower($line); 
                    $result = mysqli_query($con,"INSERT INTO $tableName (model) VALUES ('$model');");
                    if($result)
                    {
                       //LOADED SUCCESSFULLY
                    }
                    else
                    {
                        echo "problem when writing from text file";                        
                    }                   
                 }
                 
                 //close the database and file
                 mysqli_close($con);
             }
             else
             {
                 //could not find and open the text file
                 echo "could not find and open the text file ";
             }             
         }
         else //$result false
         {
             //couldnt create a table
             echo "couldnt create a table";
         }    
    }        

?>
