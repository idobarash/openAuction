Auction app
===========

This app contains one maven wrapper project which wraps 3 regualr maven projects.


Prerequisites
-------------
 - Java 1.7
 - Java EE server (preferring JBoss)
 - Relational DB  (preferring MySql)
   With schema called - "auction"

 - recomended: Maven   (https://maven.apache.org/)


 Installing
 ----------
 1) In your server:		Set JTA connection to your DB.
 						Make sure it's name is: auctionDS

 2) Add JVM argument:	-Ddisk.images
 						This will tell the server where to store images.
 						Default: /data/openu/auction/items (Linux).	
 						If you run on windows this is crucial.
 3)


