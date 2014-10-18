<link href="stylesheet/header.css" rel="stylesheet">


<div class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span><span
                    class="icon-bar"></span><span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/~f13g02">Awayz Travel Advisor</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">




                <li><a href="search.php?type=Restaurant">Restaurant</a></li>
                <li><a href="search.php?type=Hotel">Hotel</a></li>
                <li><a href="writeareview.php">Write a Review</a></li>
                <li><a href="additem.php">Add Venue</a></li>
            </ul>

            <div class="col-lg-3"><form action="search.php" method="get" class="search-head form-search"> 
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control searchbar" name="q" placeholder="City or Venue Name">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                    </div><!-- /input-group -->
                </form>
            </div>



            <ul class="nav navbar-nav navbar-right">

                <?php
                if (isset($_SESSION["user_name"])) {
                    ?>
                    <li><a href="#">Hi <?php echo $_SESSION["user_name"]; ?></a></li>  
                    <li><a href="logout.php">Logout</a></li>
                    <?php
                } else {
                    ?>
                    <li><a href="login.php">Login</a></li>
                    <li><a href="signup.php">Register</a></li>
                <?php } ?>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>