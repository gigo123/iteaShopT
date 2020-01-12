# Online Store on JAVA servlets API
This project is a course project for java web courses at ITEA, a web application of Online Store that uses apple products as test data.
 ## Features
Project represent partly worked online store web application. JEE Servlet API technology is used, with JavaScript added. MySQL used as the database
#### The following pages implemented:
- Product list
- User create/edit 
- User login
- Cart
#### The following features implemented:
- Create user (date store in database)
- Edit user data
- Show product by categories
- Destroy user session
- Adding items to cart
- Shopping cart editing.
- Hashing user password
- Blocking brute-force password

## Tech
On backend, Store built on JAVA infrastructure, specifically used:
* [Maven] -as software project management and comprehension tool
* [JEE Servlet API]
* [MySQL] - used as the database 
On front side,  
* [Twitter Bootstrap] - great UI boilerplate for modern web apps
* [jQuery] – duh
## Getting Started
### Prerequisites
JAVA JDK at least version 8 
Maven
In order for the application to work properly, the database must be connected. The database description is in the database file.
### Build project
Build project directly by Maven
Open command prompt on main folder of project
Enter command
```
 mvn  package
```
 or open it in JAVA IDE, and run build in it
### Deploy project
After build of the project build, a WAR file created. This file must be hosted on a java web server (I'm using Apache Tomcat).
### Run project
To access Store you must enter the url  “ path_to_you_ server/iteaShopT/ “in your browser ( like localhost:8080/iteaShopT/  for Tomcat)

[//]: # ()
   [Maven]: < https://maven.apache.org/>
   [JEE Servlet API]: < https://javaee.github.io/servlet-spec/>
   [MySQL]: < https://www.mysql.com/>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>





