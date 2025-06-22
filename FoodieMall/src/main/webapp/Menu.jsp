<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.mall.model.Menu"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Menu | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
<style>
body {
	font-family: 'Poppins', sans-serif;
	background-color: #f8f9fa;
	text-align: center;
	margin: 0;
}

h1 {
	color: #ff5733;
	margin-bottom: 20px;
}

.menu-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
	gap: 20px;
	justify-content: center;
	padding: 20px;
}

.menu-card {
	background: #fff;
	padding: 15px;
	border-radius: 10px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
	transition: transform 0.3s;
}

.menu-card:hover {
	transform: scale(1.05);
}

.menu-card img {
	width: 100%;
	height: 180px;
	object-fit: cover;
	border-radius: 8px;
}

.desc {
	font-size: 14px;
	color: #666;
}

.price {
	font-size: 18px;
	color: #28a745;
	font-weight: bold;
}

.ratings {
	font-size: 16px;
	color: #ffc107;
}

.order-btn {
	background: #ff5733;
	color: #fff;
	border: none;
	padding: 10px;
	border-radius: 5px;
	cursor: pointer;
	margin-top: 10px;
}

.order-btn:hover {
	background: #c44124;
}
</style>
</head>
<body>

	<h1>🍽️ Explore Our Menu</h1>

	<%
	List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
	if (menuList != null && !menuList.isEmpty()) {
	%>
	<div class="menu-container">
		<%
		for (Menu menu : menuList) {
		%>
		<div class="menu-card">
			<img src="<%=menu.getImagePath()%>" alt="<%=menu.getName()%>">
			<h3><%=menu.getName()%></h3>
			<p class="desc"><%=menu.getDescription()%></p>
			<p class="price">
				₹
				<%=menu.getPrice()%></p>
			<p class="ratings">
				⭐
				<%=menu.getRatings()%></p>

			<!-- Add to Cart Form -->
			<form action="CartServlet" method="post">
				<input type="hidden" name="menuId" value="<%=menu.getMenuId()%>">
				<input type="hidden" name="quantity" value="1"> <input
					type="hidden" name="action" value="add"> <input
					type="hidden" name="price" value="<%=menu.getPrice()%>">
				<input type="hidden" name="name" value="<%=menu.getName()%>">
				<input type="hidden" name="restaurantId"
					value="<%=menu.getRestaurantId()%>">
				<button type="submit" class="order-btn">ADD</button>
			</form>

		</div>
		<%
		}
		%>
	</div>
	<%
	} else {
	%>
	<p>No menu items available. Try selecting a restaurant!</p>
	<%
	}
	%>

</body>
</html>
