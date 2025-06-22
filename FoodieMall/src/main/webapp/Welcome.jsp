<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mall.model.Restaurant"%>
<%@ page import="com.mall.model.User, com.mall.model.OrderItem"%>

<!DOCTYPE html>
<html>
<head>
<title>Welcome | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
<style>
.navbar {
	display: grid;
	grid-template-columns: 1fr 2fr 2fr;
	align-items: center;
	padding: 15px 30px;
	background-color: #333;
	column-gap: 20px;
	flex-wrap: wrap;
}

.nav-left, .nav-center, .nav-right {
	display: flex;
	align-items: center;
}

.nav-left {
	justify-content: flex-start;
}

.nav-center {
	justify-content: center;
}

.nav-right {
	justify-content: flex-end;
	gap: 50px;
}

.search-wrapper {
	position: relative;
	width: 15cm;
}

.search-icon {
	position: absolute;
	top: 50%;
	left: 12px;
	transform: translateY(-50%);
	font-size: 16px;
	color: #888;
	pointer-events: none;
}

.search-bar {
	width: 100%;
	padding: 10px 12px 10px 36px;
	border-radius: 24px;
	border: none;
	font-size: 14px;
	outline: none;
	transition: box-shadow 0.3s ease;
	font-family: 'Segoe UI', sans-serif;
}

.search-bar:focus {
	box-shadow: 0 0 6px rgba(255, 112, 67, 0.4);
}

.profile-icon {
	width: 38px;
	height: 38px;
	border-radius: 50%;
	cursor: pointer;
}

.nav-right a {
	color: white;
	text-decoration: none;
	font-size: 14px;
}

body {
	font-family: 'Segoe UI', sans-serif;
	background: #f9f9f9;
	margin: 0;
	padding: 0;
}

.container {
	text-align: center;
	padding: 20px;
}

h1, h2 {
	color: #333;
	margin-bottom: 20px;
}

.restaurant-grid {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: center;
	padding: 20px;
}

.restaurant-card {
	border: 1px solid #ccc;
	padding: 15px;
	width: 250px;
	text-align: center;
	background-color: #f9f9f9;
	box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
}

.menu-link {
	display: block;
	margin-top: 10px;
	padding: 8px;
	background-color: #007bff;
	color: white;
	text-decoration: none;
	border-radius: 5px;
}

.menu-link:hover {
	background-color: #0056b3;
}
</style>

</head>
<body>

	<div class="navbar">
		<div class="nav-left">
			<a href="callServlet"
				style="display: flex; align-items: center; gap: 10px; text-decoration: none;">
				<img src="https://cdn-icons-png.flaticon.com/256/10823/10823283.png"
				alt="FoodieMall Icon" style="height: 32px;">
			<h3 style="color: white; font-weight: bold; margin: 0;">𝔣Ｏ𝕠𝕕iⓔϻａℓ𝓁</h3>
			</a>
		</div>


		<div class="nav-center">
			<form action="SearchServlet" method="get" class="search-form">
				<div class="search-wrapper">
					<span class="search-icon">🔍</span> <input type="text" name="query"
						class="search-bar"
						placeholder="     Search restaurants and food items...">
				</div>
			</form>
		</div>

		<div class="nav-right">
			<%
			User loggedInUser = (User) session.getAttribute("user");
			if (loggedInUser == null) {
			%>
			<a href="Login.jsp">Login</a> <a href="Signup.jsp">Signup</a>
			<%
			} else {
			%>
			<a href="Login.jsp">Logout</a>
			<%
			}
			%>
			<a href="CartViewServlet" class="cart-icon">🛒Cart</a> <a
				href="ProfileServlet"><img
				src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
				alt="Profile" class="profile-icon"></a>
		</div>
	</div>



	<!-- Displays Welcome  -->
	<div class="container">
		<h1>
			Welcome,
			<%=loggedInUser != null ? loggedInUser.getName() : "Guest"%>!
		</h1>
		<p>What would you like to do today?</p>
		<ul class="dashboard-links">
			<li><a href="OrderHistory.jsp">My Orders</a></li>
		</ul>
	</div>

	<!-- Restaurants -->
	<h3>Available Restaurants</h3>

	<%
	List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
	if (restaurants != null && !restaurants.isEmpty()) {
	%>
	<div class="restaurant-grid">
		<%
		for (Restaurant r : restaurants) {
		%>
		<div class="restaurant-card">
			<h4><%=r.getName()%></h4>
			<img src="<%=r.getImagePath()%>" alt="Restaurant Image">
			<p>
				<strong>Cuisine:</strong>
				<%=r.getCuisineType()%></p>
			<p>
				<strong>ETA:</strong>
				<%=r.getEta()%></p>
			<p>
				<strong>Ratings:</strong>
				<%=r.getRatings()%></p>
			<p>
				<strong>Location:</strong>
				<%=r.getAddress()%></p>
			<a href="MenuServlet?restaurantId=<%=r.getRestaurantId()%>"
				class="menu-link">Show Menu</a>
		</div>
		<%
		}
		%>
	</div>
	<%
	} else {
	%>
	<p>No restaurants found.</p>
	<%
	}
	%>

</body>
</html>
