# Coupon Management System
 Full-Stack Project Based in Spring & React

Coupon Management System: Final project by Shachaf Izhaki. 

This project was built throughout the course, we have put a lot of effort into it by facing and solving many problems head on, which has taught us very much.  

Instructions:   

1. In order to initiate the SQL scheme, you will need to enter the "application.properties" file located in the Spring project under src/main/resources and enter your DB username & password, it will create the entire scheme when running the project.

2. Using the React CLI, run the command "npm install", it will download all the necessary packages that we use in our project. 

3. The Spring server will run on port 8080.
4. The React server will run on port 3000.

Server Structure:  
The server consists of several layers and entities.

Entities include: Coupon, Company and Customer.

Two ENUM Lists: The first contains the Client types and the second contains the Coupon categories.

Layers:  
* The Repository layer: This layer communicate with the DB by using custom queries and Spring Data JPA queries.  
* The Service layer: This layer contains the business logic of the project.  
* The Controller layer (RESTful API): Constitutes the access point to the server and does not have any BL. In the controller layer, there is a login controller that constitutes the first stage when connecting as an Admin, Customer and Company.  
* The project also contains the Login Manneger layer which is the responsible for the login inspection and all the other authentication logic.  

Website Structure:  
The site is composed of a default main page that includes links that routes to several locations.  
  
The first authentication to the system will be as an Administrator.  
We will send an email with the password attached for the first authentication.  
  
After the login, the website will route you to the admin personal area, the user will be routed to the personal area of the Admin, there he will have a menu with the functions he can use such as: creating companies, updating said companies, etc.  
  
In case you logged in as a company, the user will be routed to the personal area of the company, there you will find a menu with a myriad of functions to use, such as creating new coupons. 
  
In case you logged in as a customer, the user will be routed to the customer menu.  
Here, you can purchase coupons from any category. 
  
Each of the users can go back to their personal area by pressing the client type button.  

The Admin details to login with:  
Email Address - admin@gmail.com.  
Password - admin1234. 

We hope you enjoy our product! 
