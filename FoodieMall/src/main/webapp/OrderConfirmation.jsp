<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, com.mall.model.CartItem"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmation | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
<style>
body {
	font-family: 'Poppins', sans-serif;
	background-color: #f8f9fa;
	text-align: center;
	margin: 0;
}

.confirmation-container {
	max-width: 600px;
	margin: auto;
	padding: 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.order-summary {
	text-align: left;
	margin-top: 20px;
}

.total-price {
	font-size: 20px;
	font-weight: bold;
	color: #28a745;
}

.confirm-btn {
	display: inline-block;
	padding: 15px 25px;
	font-size: 20px;
	font-weight: bold;
	color: white;
	background-color: #ff5733;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: 0.3s ease-in-out;
}

.confirm-btn:hover {
	background-color: #c44124;
}
</style>
</head>
<body>

	<h1>✅ Thank You for Your Order!</h1>

	<div class="confirmation-container">
		<h3>🛍️ Your Items:</h3>

		<%
		Map<Integer, CartItem> cartItems = (Map<Integer, CartItem>) session.getAttribute("cartItems");
		int totalPrice = 0;

		if (cartItems != null && !cartItems.isEmpty()) {
		%>
		<div class="order-summary">
			<%
			for (CartItem item : cartItems.values()) {
			%>
			<p>
				📌 <strong><%=item.getName()%></strong> - ₹
				<%=item.getPrice()%>
				x
				<%=item.getQuantity()%>
				= ₹
				<%=item.getPrice() * item.getQuantity()%></p>
			<%
			totalPrice += item.getPrice() * item.getQuantity();
			%>
			<%
			}
			%>
		</div>

		<p class="total-price">
			Total: ₹
			<%=totalPrice%></p>

		<div style="text-align: center; margin-top: 20px;">
			<form action="FinalConfirmationServlet" method="post">
				<button type="submit" class="confirm-btn">✅ Confirm Order</button>
			</form>
		</div>

		<%
		} else {
		%>
		<p>No items found in your cart.</p>
		<%
		}
		%>
	</div>



</body>
</html>
