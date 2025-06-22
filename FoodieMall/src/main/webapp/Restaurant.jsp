<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="com.mall.model.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Restaurants</title>
<style>
.restaurant-grid {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: center;
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

.restaurant-card img {
	width: 100%;
	height: auto;
	border-radius: 10px;
	box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.2);
}
</style>
</head>
<body>

	<div class="restaurant-grid">
		<%
		List<Restaurant> list = (List<Restaurant>) request.getAttribute("restaurants");
		for (Restaurant res : list) {
		%>
		<div class="restaurant-card">
			<h3><%=res.getName()%></h3>
			<img src="<%=res.getImagePath()%>" alt="Restaurant Image"
				width="250px">
			<p>
				<strong>Address:</strong>
				<%=res.getAddress()%></p>
			<p>
				<strong>Cuisine:</strong>
				<%=res.getCuisineType()%></p>
			<p>
				<strong>ETA:</strong>
				<%=res.getEta()%></p>
			<p>
				<strong>Ratings:</strong>
				<%=res.getRatings()%></p>
			<p>
				<strong>Active:</strong>
				<%=res.getIsActive() == true ? "Yes" : "No"%></p>
			<a class="menu-link"
				href="menu.jsp?restaurantId=<%=res.getRestaurantId()%>">View
				Menu</a>
		</div>
		<%
		}
		%>
	</div>

</body>
</html>
