# FoodieMall - Food Delivery Web Application (Java JSP)

Welcome to **FoodieMall**, a comprehensive food delivery web application designed using Java, JSP, and Servlets. This project enables users to browse restaurants, explore menus, place orders, and manage profiles—all in a user-friendly and responsive environment.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

**FoodieMall** is a web-based platform connecting hungry customers with a variety of restaurants. It provides role-based access for customers, restaurant owners, and administrators, supporting everything from restaurant management to order tracking, with a modern, intuitive web interface.

> **Note:**  
> Currently, only the customer role is fully implemented. Admin and restaurant owner roles will be added in the future.

---

## Features

- **User Authentication:** Secure registration and login for customers (admin and restaurant owner roles coming soon).
- **Encrypted Passwords:** User passwords are encrypted in the database for enhanced security.
- **Restaurant Management:** View restaurant details (name, cuisine, ratings, ETA, location, status).
- **Menu Browsing:** View restaurant menus and food items, add items to cart.
- **Order Placement & Tracking:** Place orders, view order history, and track live orders.
- **User Profile Management:** View and update user details.
- **Search Functionality:** Search restaurants and food items by keyword.
- **Responsive Design:** Clean, modern UI that works across devices.

---

## Tech Stack

- **Backend:** Java, Servlet API, JSP
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL  
  &nbsp;&nbsp;• **Schema name:** `foodiemall`  
  &nbsp;&nbsp;• **Tables:** `User`, `Restaurant`, `Menu`, `Order`, `OrderHistory`, `OrderItem`
- **Build Tool:** Maven/Gradle
- **Architecture:** MVC (Model-View-Controller)

---

## Project Structure

```
FoodieMall-Food-Delivery-WebApp-Java-JSP/
├── FoodieMall/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── mall/
│           │           ├── controller/
│           │           │   ├── CartServlet.java
│           │           │   ├── CartViewServlet.java
│           │           │   ├── CheckoutServlet.java
│           │           │   ├── FinalConfirmationServlet.java
│           │           │   ├── LoginServlet.java
│           │           │   ├── MenuServlet.java
│           │           │   ├── ProfileServlet.java
│           │           │   ├── RestaurantServlet.java
│           │           │   ├── SignupServlet.java
│           │           │   └── UpdateProfileServlet.java
│           │           ├── dao/
│           │           │   ├── MenuDao.java
│           │           │   ├── OrderDao.java
│           │           │   ├── OrderHistoryDao.java
│           │           │   ├── OrderItemDao.java
│           │           │   ├── RestaurantDao.java
│           │           │   └── UserDao.java
│           │           ├── daoImpl/
│           │           │   ├── MenuDaoImpl.java
│           │           │   ├── MenuDaoTest.java
│           │           │   ├── OrderDaoImpl.java
│           │           │   ├── OrderDaoTest.java
│           │           │   ├── OrderHistoryDaoImpl.java
│           │           │   ├── OrderHistoryDaoTest.java
│           │           │   ├── OrderItemDaoImpl.java
│           │           │   ├── OrderItemDaoTest.java
│           │           │   ├── RestaurantDaoImpl.java
│           │           │   ├── RestaurantDAOTest.java
│           │           │   ├── UserDaoImpl.java
│           │           │   └── UserDAOTest.java
│           │           └── model/
│           │               ├── CartCreator.java
│           │               ├── CartItem.java
│           │               ├── Menu.java
│           │               ├── Order.java
│           │               ├── OrderHistory.java
│           │               ├── OrderItem.java
│           │               ├── Restaurant.java
│           │               └── User.java
│           └── webapp/
│               ├── Images/
│               │   └── BgWelcome.jpg
│               ├── META-INF/
│               ├── WEB-INF/
│               ├── Cart.jsp
│               ├── Checkout.jsp
│               ├── Berror.jsp
│               ├── FinalConfirmation.jsp
│               ├── index.html
│               ├── Login.jsp
│               ├── Menu.jsp
│               ├── OrderConfirmation.jsp
│               ├── Profile.jsp
│               ├── Restaurant.jsp
│               ├── Signup.jsp
│               ├── styles.css
│               └── Welcome.jsp
├── README.md
├── pom.xml (or build.gradle)
└── (other files)
```

- `com.mall.controller` – Servlet controllers for handling HTTP requests.
- `com.mall.dao` – DAO interfaces for data operations.
- `com.mall.daoImpl` – DAO implementations and test classes for database logic.
- `com.mall.model` – Java model classes for business entities.
- `webapp/` – JSP files, HTML, CSS, static resources (images), and deployment descriptors.

---

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AdapaJohn/FoodieMall-Food-Delivery-WebApp-Java-JSP.git
   ```
2. **Import into your IDE:**
   - Open the project as a Maven or Java web project in Eclipse, IntelliJ IDEA, etc.
3. **Database Setup:**
   - Create a MySQL database named `foodiemall`.
   - Create the following tables: `User`, `Restaurant`, `Menu`, `Order`, `OrderHistory`, `OrderItem` (structure should match your model classes).
   - **Update your database connection settings in your DAO implementation files (e.g., in the DAO classes or a dedicated configuration properties file) to match your MySQL database credentials (username, password, URL, etc.).**
   - **Use the SQL table definitions as described above to create the tables in your MySQL database.**
4. **Build and Deploy:**
   - Build the project using Maven/Gradle or your IDE.
   - Deploy the generated WAR file to a servlet container (e.g., Apache Tomcat).

---

## Usage

### For Customers

1. **Sign Up**  
   - Visit the Signup page and create a new account by providing your name, email, username, password, phone number, and address.  
   - Your password will be securely encrypted before storing in the database.

2. **Log In**  
   - Log in using your registered username and password.
   - After successful login, you will be redirected to your dashboard.

3. **Browse Restaurants**  
   - View the list of available restaurants, each displaying their cuisine, ratings, estimated delivery time, and location.
   - Click on any restaurant to view their menu.

4. **Search Functionality**  
   - Use the search bar to find restaurants or specific food items quickly.

5. **View Menu and Add to Cart**  
   - Browse the menu items of a selected restaurant.
   - View details such as dish name, description, price, availability, and ratings.
   - Add desired items to your shopping cart, specifying the quantity.

6. **Cart Management**  
   - Click on the cart icon to view all items added to your cart.
   - Modify quantities or remove items as needed.

7. **Place Order**  
   - Proceed to checkout from your cart page.
   - Review your order summary, select the mode of payment (UPI, cash, debit/credit), and confirm your order.

8. **Order Tracking and History**  
   - After placing an order, track its status (confirmed, dispatched, delivered) in real time.
   - View your complete order history, including past orders and their statuses.

9. **Profile Management**  
   - Access your profile to view or update your personal information (name, email, address, phone number, etc.).
   - Change your password securely if needed.

10. **Logout**  
   - Log out securely using the logout option in the navigation menu.

> **Note:** Features for restaurant owners and admins are planned for future updates.

---

## Contributing

Contributions and suggestions are welcome!  
Please open issues or pull requests for improvements, bug fixes, or new features.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

**Developed and maintained by [AdapaJohn](https://github.com/AdapaJohn)**
