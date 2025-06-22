<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
<style>
body {
	font-family: 'Poppins', sans-serif;
	background-color: #f8f9fa;
	text-align: center;
	margin: 0;
}

.checkout-container {
	max-width: 600px;
	margin: auto;
	padding: 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

input, select, button {
	display: block;
	width: 100%;
	margin: 10px 0;
	padding: 10px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

.confirm-btn {
	background: #28a745;
	color: white;
	font-size: 18px;
	font-weight: bold;
	cursor: pointer;
}

.confirm-btn:hover {
	background: #218838;
}
</style>
</head>
<body>

	<h1>Checkout</h1>

	<div class="checkout-container">
		<form action="CheckoutServlet" method="post">
			<label for="address">📍 Delivery Address:</label> <input type="text"
				name="address" id="address" placeholder="Enter your address"
				required> <label for="paymentMode">💳 Payment Mode:</label>
			<select name="paymentMode" id="paymentMode" required>
				<option value="COD">Cash on Delivery (COD)</option>
				<option value="UPI">UPI Payment</option>
				<option value="Credit">Credit/Debit Card</option>
			</select>

			<button type="submit" class="confirm-btn">✅ Confirm Order</button>
		</form>
	</div>

</body>
</html>
