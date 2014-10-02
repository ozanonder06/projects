<?php

/*
 * @author: ozan gazi onder
 * the following program validates the vin number by length
 * receives vin, make and year from the user
 * and checks if the vin validates the make and year
 * reads data from years.xml, years2.xml and makes.xml
 * The program can process the years between 1980 and 2039
 */

    class  vinDecoder
    {
        //returns true if the given
        //input has 17 digits/letters
        public function isVin($vin)
        {
            if(strlen($vin)==17)
                return true;
            else
                return false;
        }  
        
        //returns true if the entered year is valid
        public function yearCheck($year)
        {
            if(($year >= 1980) && ($year <= 2039))
                return true;
            else
                return false;
        }        
        
        
        //returns true if year is matched
        //with the vin number
        //year must be in the range between
        //1980 and 2039, inclusively.
        public function isYear($vin, $year)
        {
            //reads the 10th digit of the vin
            $tdigit = substr($vin, 9, 1);
            
            //checks the year of the car
            // and depending on the condition
            //it reads from one of the years xml data
            if(($year >= 1980) && ($year <= 2009))
            {
                $doc = new DOMDocument();
                $doc->load('years.xml');
                
                $cars = $doc->getElementsByTagName('car');
                
                //generates the xml to see if the year matches to vin
                foreach($cars as $car)
                {
                    //retrieve the year of each car in the xml file
                    $y = $car->getElementsByTagName('year')->item(0)->nodeValue;
                    
                    //retrieve the letter digit of each car coorespnds to year
                    // in the xml file
                    $d = $car->getAttribute('ten_pos');
                    
                    //check if they match with our data
                    if(($y==$year)&&($d==$tdigit))
                        return true;
                } 
                return false;
                
            }  
            else if(($year >= 2010) && ($year <= 2039))
            {
                $doc = new DOMDocument();
                $doc->load('years2.xml');
                
                $cars = $doc->getElementsByTagName('car');
                
                //generates the xml to see if the year matches to vin
                foreach($cars as $car)
                {
                    //retrieve the year of each car in the xml file
                    $y = $car->getElementsByTagName('year')->item(0)->nodeValue;
                    
                    //retrieve the letter digit of each car coorespnds to year
                    // in the xml file
                    $d = $car->getAttribute('ten_pos');
                    
                    //check if they match with our data
                    if(($y==$year)&&($d==$tdigit))
                        return true;
                } 
                return false;
                
            }
            else
                return false;
        }  
        
        
        //checks if the vin and make is matched
        //by reading makes.xml
        public function isMake($vin, $make)
        {
            //reads the 2nd digit of the vin
            $sdigit = substr($vin, 1, 1);
            
            $doc = new DOMDocument();
            $doc->load('makes.xml');
                
            $cars = $doc->getElementsByTagName('car');
            
             //generates the xml to see if the year matches to vin
             foreach($cars as $car)
             {   
                 //retrieve attribute  make of each car
                 // in the xml file
                 $m = $car->getAttribute('make');
                 
                    
                 //retrieve attribute pos of each car
                 // pos resperents the letter digit of the car
                 //that corresponds to a specific car make
                 $p = $car->getAttribute('pos');
                 
                 //check if they match with our data
                 if(($m==$make)&&($p==$sdigit))
                      return true;
                 
             }
              return false; //car make doesn't match
                        
        }
            
        
    }//end of the class
    
     //returns true if the given input is an integer
     //and verifies milage and price
     function isNumericInfo($input)
     {
         $len = strlen($input);
         if(!is_numeric($input)) //numeric test
                 return false;
         else if($input < 0 || $len > 7) //accuracy test
                 return false;
         else
                 return true;             
     }        
    
    
    /*some functions that are going to be used in
     * the user information verification process
     */   
     
    //validates if the email is valid
    function isEmail($email_a)
    {
        if (filter_var($email_a, FILTER_VALIDATE_EMAIL))
            return true;
        else
            return false;
                
    }
    
    //returns true if two strings are equal
    //used to validate email address
    function emailMatch($first, $second)
    {
        if(strcmp($first,$second)==0)
                return true;
        else return false;
    }  
    
    /*validates the user by checking
     * 1) characters and length of the username
     * 2) if there is another user with the same name in the database
     */
    function isUserValid($usr)
    {
        if(!isUserCorrect($usr))
            return false;
        
        //connect to the database
        require_once 'db.php';
        $result = mysqli_query($con,"SELECT seller FROM Car");
    
        while($row = mysqli_fetch_array($result))
        {
          
         if(strcmp($usr,$row['seller'])==0)
             return false; //nickname has already exist
         
        }
        return true;
     }//end of isUserValid
    
     
    /* checks whether the username is valid
     * username must be at least 4, at most 10 
     * character long and it can at most 2 digits
     * others MUST be alphabetical characters
     */
    function isUserCorrect($username)
    {
        $username = strtolower($username);
        $limit = strlen($username);
        $numberOfDigit = 0;
        if(($limit < 4) || ($limit > 10)) //checks the length
            return false;
        
        for($i = 0; $i<$limit; $i++)
        {
            $character = substr($username, $i, 1);
            if($character >= 'a' && $character <= 'z')
                continue;       
            else if($character >= '0' && $character <= '9')
            {
               $numberOfDigit++;
               if($numberOfDigit>2)
                  return false;
               else
                  continue;
            }    
            else //other characters such as *, &, % etc....
                return false;
        }
        return true;
     }//end of isUserCorrect
            
?>
