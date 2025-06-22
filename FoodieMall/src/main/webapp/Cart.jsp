<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, com.mall.model.CartItem"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
<style>
body {
	font-family: 'Poppins', sans-serif;
	background-color: #f8f9fa;
	text-align: center;
	margin: 0;
}

.cart-container {
	max-width: 800px;
	margin: auto;
	padding: 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.cart-item {
	display: flex;
	justify-content: space-between;
	padding: 10px;
	border-bottom: 1px solid #ccc;
}

.cart-actions {
	display: flex;
	gap: 10px;
}

.update-btn, .delete-btn {
	border: none;
	padding: 5px 10px;
	cursor: pointer;
}

.update-btn {
	background: #007bff;
	color: white;
}

.delete-btn {
	background: #dc3545;
	color: white;
}

.add-more-btn {
	display: inline-block;
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
	text-decoration: none;
	color: white;
	background-color: #007bff;
	border-radius: 5px;
	transition: 0.3s ease-in-out;
}

.add-more-btn:hover {
	background-color: #0056b3;
}

.checkout-btn {
	display: inline-block;
	padding: 12px 20px;
	font-size: 18px;
	font-weight: bold;
	text-decoration: none;
	color: white;
	background-color: #28a745;
	border-radius: 5px;
	transition: 0.3s ease-in-out;
}

.checkout-btn:hover {
	background-color: #218838;
}
</style>
</head>
<body>

	<h1>🛒 Your Cart</h1>

	<div class="cart-container">
		<%
		Map<Integer, CartItem> cartItems = (Map<Integer, CartItem>) request.getAttribute("cartItems");
		if (cartItems != null && !cartItems.isEmpty()) {
		%>
		<%
		for (CartItem item : cartItems.values()) {
		%>
		<div class="cart-item">
			<span><%=item.getName()%> (₹ <%=item.getPrice()%>)</span>

			<!-- Update Quantity  -->
			<form action="CartServlet" method="post">
				<input type="hidden" name="menuId" value="<%=item.getItemId()%>">
				<input type="hidden" name="price" value="<%=item.getPrice()%>">
				<input type="hidden" name="name" value="<%=item.getName()%>">
				<input type="hidden" name="restaurantId"
					value="<%=item.getRestaurantId()%>"> <input type="number"
					name="quantity" value="<%=item.getQuantity()%>" min="1" required>
				<input type="hidden" name="action" value="update">
				<button type="submit">Update</button>
			</form>

			<!-- Delete Item  -->
			<form action="CartServlet" method="post">
				<input type="hidden" name="menuId" value="<%=item.getItemId()%>">
				<input type="hidden" name="action" value="delete">
				<button type="submit">Delete</button>
			</form>
		</div>
		<%
		}
		%>
		<%
		} else {
		%>
		<p>Your cart is empty.</p>
		<%
		}
		%>
	</div>

	<div style="text-align: center; margin-top: 20px;">
		<a href="callServlet" class="add-more-btn">🔄 Add More Items</a>
	</div>

	<div style="text-align: center; margin-top: 20px;">
		<a href="CheckoutServlet" class="checkout-btn">✅ Proceed to
			Checkout</a>
	</div>

</body>
</html>
