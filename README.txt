Auction app
===========

This app contains one maven wrapper project which wraps 3 regualr maven projects.


Prerequisites
-------------
 - Java 1.7
 - Java EE application server (preferring JBoss) 
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

 3) Deploy the WAR File located in: auction-parent/auction-server/target 
 	on the JEE Applicaiton server

 4) Deploy the WAR File located in: auction-parent/auction-app/target 
 	on the JEE Applicaiton server


Compilation and packaging
-------------------------
If you wish to make changes you must have maven installed.
After changing the source code, Run from auction-parent root folder: mvn clean package



@ Ido Barash
idobarash@gmail.com


