-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 24, 2013 at 05:55 AM
-- Server version: 5.5.32
-- PHP Version: 5.3.10-1ubuntu3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `student_f13g02`
--

-- --------------------------------------------------------

--
-- Table structure for table `Geography`
--

CREATE TABLE IF NOT EXISTS `Geography` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `City` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `State` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `Country` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Geography`
--

INSERT INTO `Geography` (`Id`, `City`, `State`, `Country`) VALUES
(1, 'Los Gatos', 'California', 'United States'),
(2, 'San Francisco', 'California', 'United States'),
(3, 'San Jose', 'California', 'United States');

-- --------------------------------------------------------

--
-- Table structure for table `Item`
--

CREATE TABLE IF NOT EXISTS `Item` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `Type` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `GeographyId` int(11) NOT NULL,
  `Description` text CHARACTER SET utf8,
  `ImageUrl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `PriceRange` varchar(11) DEFAULT NULL,
  `Phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `Address` text CHARACTER SET utf8,
  `WebUrl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=77 ;

--
-- Dumping data for table `Item`
--

INSERT INTO `Item` (`Id`, `Name`, `Type`, `GeographyId`, `Description`, `ImageUrl`, `PriceRange`, `Phone`, `Address`, `WebUrl`) VALUES
(1, 'Krusty Burger', 'Restaurant', 1, 'Krusty Burger is a fast food restaurant chain owned by Krusty the Clown as one of his many branded products and services.', 'A190E13F-5B99-4606-A5B2-86D4A6E2819A.jpg', '$$$', '(402) 123-2924', '368 Village Ln Los Gatos, CA 95030', 'www.Krustyburger.com'),
(2, 'CoffeeShop1', 'Coffee Shop', 2, 'Description for CoffeeShop1', 'A18BD35C-404F-44F3-8575-4A8985B60318.jpg', '$$', '(213) 293-9012', '315 Linden St San Francisco, CA 94102', 'www.coffeeshop1.com'),
(3, 'The Gilded Truffle', 'Restaurant', 1, 'Description for Restaurant2', '473BE39E-7B52-4F0A-8010-EFC55F995EB7.jpg', '$$$', '(391) 120-2912', '565 University Ave Los Gatos, CA 95032', 'www.restaurant2.com'),
(4, 'Hotel1', 'Hotel', 2, 'Description for Hotel1', '1A56E4DF-91BF-4F66-85D7-3FBDEA1BD86B.jpg', '$$$$', '(120) 134-2402', '1805 Geary Blvd. San Francisco, CA 94115', 'www.hotel1.com'),
(5, 'Moe''s Tavern', 'Bar', 3, 'Description for Bar1', 'AF2D50A2-08AA-46AB-8672-5BABCAA83A16.jpg', '$$$', '(219) 121-1293', '265 N 1st St San Jose, CA 95113', 'www.bar1.com'),
(6, 'Shopping Center1', 'Shopping Center', 3, 'Description for Shopping Center1', '848E3518-AA9F-4343-9D92-00F45F8D8388.jpg', '$$$$', '(201) 129-1293', '925 Blossom Hill Rd San Jose, CA 95123', 'www.shoppingcenter1.com'),
(7, 'Shopping Center2', 'Shopping Center', 2, 'Description for Shopping Center2', '18CC2BAA-7D85-4134-A200-7969A2BA3189.jpg', '$$$$', '(230) 928-1293', '865 Market St San Francisco, CA 94103', 'www.shoppingcenter2.com'),
(8, 'Hotel2', 'Hotel', 2, 'Description for Hotel2', '0A2E8437-2E4D-4CE2-B17B-33033A2CFEC4.jpg', '$$$$', '(201) 102-2948', '775 Bush Street San Francisco, CA 94108', 'www.hotel2.com'),
(9, 'Hotel3', 'Hotel', 3, 'Description for Hotel3', 'AF880A8B-0187-42D2-B6F1-E76314A0775D.jpg', '$$$$', '(849) 102-2391', '725 Geary Street San Francisco, CA 94109', 'www.hotel3.com'),
(59, 'The Mill', 'Coffee Shop', 2, 'Coffee shop and toast!', '1284627282.jpg', '$$$$', '415-435-4515', '736 Divisadero Street San Francisco, CA 94117', 'www.themillsf.com/'),
(69, 'Rickhouse', 'Bar', 2, 'The objective at Rickhouse is to provide you, our guest, with a superior beverage experience. Whether you are interested in artisanal cocktails, fine spirits, local beers or boutique California wines, we strive to provide a precious and peculiar selection for your exploration and enjoyment.', '1915791055.gif', '$$', '(415) 3248763', '246 Kearny Str CA 94108 San Francisco', 'www.rickhouse.com'),
(70, 'Marges Yoga', 'Other', 2, 'The bay areas premier yoga studio', '1460908925.jpg', '$$', '(415)3245762', '16185 Los Gatos Blvd Los Gatos, CA 95032', 'www.yogasf.com'),
(72, 'Rilly Cheep Hotel ', 'Hotel', 2, 'Hotel located in the heart of downtown San Francisco near shopping, restaurants, bars, and nightlife. ', '639069763.jpg', '$', '1-800-123-4567', '50 3rd Street  San Francisco, 94103', 'www.starwoodhotels.com/westin/property/overview/index.html?propertyID=1981'),
(73, 'Bambino', 'Restaurant', 2, 'bakery, cafe', '275650096.jpg', '$', '(415) 123-1258', '123 clement San Francisco, 94118', 'www.bambino.com'),
(74, 'Very Comfy Hotel', 'Hotel', 2, 'Hotel with comfy beds and a great view from our windows', '1276532105.jpg', '$', '12333555', '55 Cyril Magnin Street  San Francisco, 94102', 'www.parc55hotel.com'),
(75, 'Hams Diner', 'Restaurant', 2, 'A hole in a wall burger joint with the best burgers and fries. In the Richmond district', '178774871.jpg', '$$', '805-555-5555', '2014 Clement Street San Francisco, CA', 'www.hamsDiner.com'),
(76, 'Stacies Inn', 'Hotel', 2, 'Luxuries hotel with asian themes', '310657735.jpg', '$$$$', '555-555-555', '2015 Clement Street San Francisco, CA 94121', 'www.staciesInn.com');

-- --------------------------------------------------------

--
-- Table structure for table `Media`
--

CREATE TABLE IF NOT EXISTS `Media` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ItemId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `MediaUrl` varchar(200) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=211 ;

--
-- Dumping data for table `Media`
--

INSERT INTO `Media` (`Id`, `ItemId`, `UserId`, `MediaUrl`) VALUES
(151, 2, 1, '1911868549.jpg'),
(174, 56, 1, '1028668827.jpg'),
(175, 57, 1, '1219773974.jpg'),
(179, 59, 14, '1284627282.jpg'),
(180, 59, 14, '140774089.jpg'),
(183, 69, 17, '1915791055.gif'),
(187, 70, 2, '1460908925.jpg'),
(188, 59, 1, '776965905.jpg'),
(189, 59, 1, '278564651.jpg'),
(190, 59, 1, '504748633.jpg'),
(191, 72, 2, '639069763.jpg'),
(192, 73, 18, '275650096.jpg'),
(193, 0, 2, '1034930285.jpg'),
(194, 74, 2, '1276532105.jpg'),
(195, 2, 19, '1787207265.mov'),
(196, 2, 19, '1407743974.jpg'),
(197, 0, 2, ''),
(198, 1, 2, '627078536.jpg'),
(199, 59, 2, '259032360.jpg'),
(200, 1, 2, '2113365263.jpg'),
(201, 1, 2, '1622315025.jpg'),
(202, 1, 2, '898944880.jpg'),
(203, 1, 2, '1552695202.jpg'),
(204, 75, 4, '178774871.jpg'),
(205, 76, 4, '310657735.jpg'),
(206, 9, 6, '2111246675.jpg'),
(207, 9, 6, '1464559718.jpg'),
(208, 9, 6, '1199627202.jpg'),
(209, 9, 6, '378117296.jpg'),
(210, 70, 2, '74385695.png');

-- --------------------------------------------------------

--
-- Table structure for table `Review`
--

CREATE TABLE IF NOT EXISTS `Review` (
  `Id` int(32) NOT NULL AUTO_INCREMENT,
  `ItemId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Rate` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `Review` text CHARACTER SET utf8,
  `Recommend` int(11) DEFAULT NULL,
  `Nrhelp` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=52 ;

--
-- Dumping data for table `Review`
--

INSERT INTO `Review` (`Id`, `ItemId`, `UserId`, `Rate`, `Review`, `Recommend`, `Nrhelp`, `date`) VALUES
(1, 1, 1, '3', 'The clerk never acknowledged me even after he was finished helping the two guys that walked in to pick up their order, he then proceeded to go in the back leaving the front unattended and seemed like he was searching for orders to bring to the front. \r\n\r\nThe waiting area is small and filled with people so you probably might want to wait outside. Overall this place produces some delicious sandos but the attempt at getting sando is exhausting. Would I go back during non-peak hours? Sure. Would I go back for lunch probably not. Too much of a hassle.', 1, 3, '2013-12-02'),
(2, 0, 0, '4', 'This place is awesome!', 1, 7, '2013-04-02'),
(3, 1, 6, '4', 'Good sandwiches, good prices for the size. Comes with great chip offerings! \r\n\r\nI really liked their dirty sauce, I think that''s what made the sandwich itself. Also the Dutch Crunch bread was delicious! These sandwiches were definitely meant to be eaten right away because my leftovers were a cold soggy mess. \r\n\r\nNot a lot of space to sit on the inside and the bathrooms were out of order. \r\n\r\nOverall a good sandwich spot that lives up to expectations.', 0, 7, '2013-01-05'),
(4, 1, 0, '4', 'A great place to eat out!', 1, 31, '2013-03-19'),
(5, 2, 1, '2', 'OK!', NULL, 24, '2013-04-13'),
(6, 3, 2, '2', 'This is a decent place', 1, 1, '2013-06-09'),
(7, 4, 1, '5', 'Excellent!', NULL, 10, '2013-05-05'),
(10, 5, 1, '5', 'Best place I''ve ever been to!', NULL, 5, '2013-05-02'),
(12, 5, 0, '4', 'This place is cool.', 1, 3, '2013-11-02'),
(13, 6, 1, '5', 'Awesome!', NULL, NULL, '2013-12-01'),
(14, 7, 1, '1', 'Terrible!', NULL, NULL, '2013-10-12'),
(16, 8, 4, '5', 'Very nice!', NULL, NULL, '2013-05-12'),
(18, 9, 3, '2', 'Not too good!', NULL, 1, '2013-07-30'),
(20, 5, 0, '3', 'Cool place. ', 1, 1, '2013-09-09'),
(21, 3, 1, '4', 'I ate here once. It was good.', 1, NULL, '2013-12-04'),
(22, 1, 1, '2', 'It could be better', 0, 3, '2013-01-12'),
(23, 3, 1, '3', 'This needs more reviews', 1, 10, '2013-05-07'),
(24, 9, 1, '1', 'This play is aweful', 0, NULL, '2013-08-10'),
(25, 9, 1, '1', 'Soooo bad', 0, NULL, '2013-05-20'),
(26, 8, 1, '4', 'This hotel is not bad', 1, NULL, '2013-12-16'),
(27, 1, 14, '5', 'Best food in town! I love this spot. You should order the spaghetti!', 1, 5, '2013-12-16'),
(28, 59, 14, '4', 'Best coffee and toast in town!', 1, NULL, '2013-12-16'),
(29, 5, 14, '4', 'This place is great! you should come.', 1, NULL, '2013-12-16'),
(30, 7, 1, '5', 'This is a greate yoga studio.', 1, NULL, '2013-12-17'),
(31, 69, 17, '5', 'I am not sure if it is the amazing decor that whips me away to prohibition era speak-easys or the fantastic music or the amazingly delicious and unique cocktails but this place is an obsession of mine. \r\n\r\nEach drink is made with house-made bitters and a jaw dropping selection of alcohols and a series of flourishes that seem half magic trick. They have antique punch bowls filled with classic recipes and more selections on the "secret menu" than you can keep in your kindle. This was the very first place I had seen do a flaming burst of citrus for a drink zest. \r\n\r\nAlso, suspenders and vests EVERYWHERE.', 1, 3, '2013-12-17'),
(32, 69, 6, '4', '1. Nice bouncer.\r\n\r\n2. Fun and friendly bartenders (Primarily my reason for giving 4 stars instead of 3).\r\n\r\n3. Strong Drinks.\r\n\r\n4. Great music\r\n\r\n5. Nice decor/ambiance\r\n\r\nCame here solely for the punch bowls to learn that they are only available to those who have a table and a large party :/\r\n\r\nLimited vodka and tequila options on the menu.', 1, 2, '2013-12-17'),
(33, 1, 2, '3', 'The burgers are pretty good! ', 1, NULL, '2013-12-17'),
(36, 3, 2, '4', 'This place is awesome!', 1, NULL, '2013-12-17'),
(37, 70, 4, '5', 'I love this studio - intimate space, knowledgeable instructors, classes adjusted to accommodate and challenge advance practitioners as well as beginners, general positive energy.  I could not ask for more from my yoga studio.  \r\nWould not go anywhere else to do yoga in San Francisco.', 1, 3, '2013-12-17'),
(38, 70, 2, '4', 'I just switched to Satori from another studio because the location is very close to my office. I bought a 20-pack, and have gone to 2 classes for far, it has been great! Satori  has a lot of what you need: large hardwood floor studio with ample light, friendly instructors, mats, props, water, changing area, hot towels & a class schedule conducive to lunch time or after work yoga. Only minus is the lack of showers, makes it a little hard to go to yoga during lunch then back to the office.', 1, 1, '2013-12-17'),
(39, 1, 2, '5', 'Coool place! ', 1, NULL, '2013-12-17'),
(40, 72, 2, '5', 'Best hotel ever!', 1, 10, '2013-12-17'),
(41, 1, 18, '4', 'I prefer In N out, but this place is near to my house and the service is great.', 1, 1, '2013-12-17'),
(42, 1, 2, '1', 'This place is terrible. Salty!', 0, NULL, '2013-12-17'),
(43, 3, 18, '4', 'Tasty!', 1, NULL, '2013-12-17'),
(44, 1, 19, '3', 'It was Ok. Id come back for the ribwich ', 1, NULL, '2013-12-17'),
(45, 8, 4, '3', 'IT was pretty dope', 1, NULL, '2013-12-18'),
(46, 73, 4, '4', 'Best desert place EVER!!!', 1, 1, '2013-12-18'),
(47, 75, 4, '5', 'Looks Sketchy but the burgers are fries are da bomb. Make sure you try their strawberry shake. ', 1, NULL, '2013-12-18'),
(48, 76, 4, '4', 'Very expensive, but worth the money!', 1, NULL, '2013-12-18'),
(49, 74, 4, '3', 'Very comfy. Will stay here again when i visit the town', 1, NULL, '2013-12-18'),
(50, 73, 18, '4', 'great place!', 1, NULL, '2013-12-18'),
(51, 1, 2, '1', 'Bad place!!!!', 0, 1, '2013-12-18');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `Password` varchar(200) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(200) CHARACTER SET utf8 NOT NULL,
  `ImageUrl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`Id`, `Name`, `Password`, `Email`, `ImageUrl`) VALUES
(1, 'Homer', 'pass', 'homer@email.com', 'turtle.jpg'),
(2, 'Marge', 'pass', 'marge@email.com', 'margesi.jpg'),
(3, 'Lisa', 'pass', 'lisa@email.com', 'ice.jpg'),
(4, 'Meggie', 'pass', 'meggie@email.com', 'pigeon.jpg'),
(6, 'Bart', 'pass', 'bart@email.com', 'usa.jpg'),
(17, 'Ned Flanders', 'pass', 'ned@email.com', 'nedfla.jpg'),
(18, 'Khalil Watashinosukinahito', 'l', 'shalala@email.com', NULL),
(19, 'Mike', 'hell', 'mike@hell.none', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
