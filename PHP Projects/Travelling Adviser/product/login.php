<?php require_once("include/session.php"); ?>
<?php require_once("include/function.php"); ?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Awayz</title>

        <!-- Bootstrap core CSS -->
        <link href="stylesheet/bootstrap.css" rel="stylesheet">

        <!-- Styles for template -->
        <link href="stylesheet/template.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="stylesheet/login.css" rel="stylesheet">

    </head>
    <body>
        <div id="wrapper">
            <?php
            include("template/header.php");
            ?>
            
            <div id="main">
                <div class="container">
                    <div class="jumbotron">


                    <?php
                    include("model/db.php");
                    ?>

                    <?php
                    $email = "";
                    $password = "";

                    if (isset($_POST['submit'])) {
                        // Process the form
                        // validations
                        $email = $_POST["email"];
                        $password = $_POST["password"];

                        $email = mysqli_real_escape_string($connection, $email);
                        $query = "SELECT * ";
                        $query .= "FROM User ";
                        $query .= "WHERE Email = '{$email}' ";
                        $query .= "LIMIT 1";
                        $user_set = mysqli_query($connection, $query);
                        if (!$user_set) {
                            die("Database query failed.");
                        }
                        $user = mysqli_fetch_assoc($user_set);

                        //Check Password
                        if ($password === $user["Password"]) {
                            $_SESSION["user_id"] = $user["Id"];
                            $_SESSION["user_name"] = $user["Name"];
                            
                            if(isset($_SESSION['url'])) 
                                $url = $_SESSION['url'];
                            else 
                                $url = "index.php"; 
                            header("Location: http://sfsuswe.com".$url);
                            
                        } else {
                            echo "Username/password not found.";
                        }
                    }
                    ?>

                    <?php
//                    echo "<br/>";
//                    print_r($user);
//                    echo "<br/>";
//                    print_r($_SESSION);
//                    echo "<br/>";
//                    print_r($_POST);
//                    echo "<br/>";
                    ?>
                    <div class="headline">
                    <p><b>Log In</b></p>
                    <p>Please enter your email address and password to log in.</p>
                    </div>
                        
                 
                    
                            

                    <form class="form-inline" role="form" action="login.php" method="post">
                        
                        <div class="row">
                        <div class="form-group col-xs-2">
                            <label for="txtEmail" >Email:</label>
                        </div>
                            <div class="form-group col-xs-4">
                                <input type="email" class="form-control" id="txtEmail" placeholder="Email"  name="email" value="" >
                            </div>
                    </div>
           
                        
                        <div class="row">
                        <div class="form-group col-xs-2">
                            <label for="txtPassword">Password:</label>
                        </div>
                            <div class="form-group col-xs-4">
                                <input type="password" class="form-control" id="txtPassword" placeholder="Password"  name="password" value="" >
                            </div>
                        </div>
                        
                        
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="remember"> Remember me
                                    </label>
                                    
                                </div>
                            </div>
                            
                        </div>
                            
                        
                        
                        <div class="row">
                            <div class="col-xs-6">
                                <button type="submit" class="btn btn-info" name="submit" value="submit">Log In</button>
                            </div>
                        </div>
                    </form>
                        
                </div>
            </div>
        </div>
            </div>

        <?php
        include("template/footer.php");
        ?>
        <!-- Bootstrap core JavaScript -->
        <script src="script/jquery.js"></script>
        <script src="script/bootstrap.min.js"></script>
    </body>
</html>
