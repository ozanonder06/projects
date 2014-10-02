<!DOCTYPE html>
<html>
 <head>
 </head>    

 <body> 
     <script language="javascript" type="text/javascript">
     var x = 150;
     document.cookie = "value=" + x + ";";
     </script>
     
     <?php
     if (isset($_COOKIE["value"]))
            $variable = $_COOKIE["value"];
            echo "$variable", "<br><br>";
            $new = $variable/2;
            echo "New Value: $new";
     ?>
 </body>
 

</html>