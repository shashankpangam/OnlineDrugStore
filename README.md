OnlineDrugStore
===============

Semester 3 , Java J2EE Project, Prepared by Shashank Pangam, Bo Huang, Meenakshi Mehta and Param Jyot kaur

Members:
n00993435 Shashank  Pangam
n01008237 Meenakshi 
n01050183 Bowen Huang
krpr0182 Paramjyot Kaur


Problem Statement: 

To build a website for an online drug store.
The website will have two types of users: Admin and Patients.
The admin will be able to manage the website in regards to the database like CRUD operations on the database.
The web-based online drug store should allow patients to register to the website, login,  order drugs and other pharmacy products, make their own shopping cart and view cart and Logout. 

Solution:
Using JSP, HTML, and Servlets, JavaScript
Database: MySQL
Project Features/ Modules:
User Registration
User  Login  
       ADMIN Profile to perform CRUD operations
      User Profiles 
Display available pharmacy products from database
Shopping Cart for User Orders - includes creating, editing and displaying of the cart
Logout the session

Description of Features:

Registration/ Login:
            Description:   It allows first time or non-existing users to create an    account by registering with their details. Then they can use their email Id and password to login. Existing users can directly login by clicking on Sign-in tab.
            Input: Customer Id, Customer name, and Other Customer Details.
            Process:  When a Customer provides all the required information into the Registration Form, the System will save those details into the database and whenever that Person tries to log in into the system, it will ask that person to authenticate his/her Id and Password fields.
            Output: Successful authentication will allow the user to change the account information incase required and also show that person the account summary.

 Display available products from the database:
                Description: The system will show all the available Products in the store that                On Page Load: The system will display the existing products from the database for the user to make his/her selection for ordering.

Shopping Cart:
          Description:  This option will allow the customers to order multiple products and add them to their Shopping cart. They can review their cart and edit or may continue with their order.
          Input: Customer Details, Product Details, Order Details of the Product.
         Process: The customers will select a Product that they wish to order and add them to their cart. They will be able to add more products into their cart.
        Output:  Ordering multiple Products using a Shopping cart.

Logout:  Closing the session.

The MVC Design - Hierarchy

Model
Customer_DB
Order_DB
Product_DB
View
	index.jsp
	login.jsp
	register.jsp
	productlist.jsp
	productdescription.jsp
	shoppingCart.jsp
	admin.jsp

Controller (Servlets)
ShoppingCart Module 
Maintain products selected by user
Display conents and allow user to change his/her order.
Order  Module - 
Allow user to browse and select products
User  Module - 
login
logout
maintain authorized status of active user
Admin Module
enable user with admin profile  to perform CRUD operations on database.

