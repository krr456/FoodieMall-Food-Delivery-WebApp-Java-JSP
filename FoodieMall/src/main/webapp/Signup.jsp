<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="container">
		<h2>Sign Up</h2>
		<form action="SignupServlet" method="post">
			<input type="text" name="name" placeholder="Full Name" required><input
				type="text" name="username" placeholder="Username" required><input
				type="email" name="email" placeholder="Email Address" required><input
				type="password" name="password" placeholder="Password" required><select
				id="role-select" name="role" required>
				<option value="" disabled selected>Select your role</option>
				<option value="customer">Customer</option>
				<option value="admin">Admin</option>
				<option value="restaurantOwner">Restaurant Owner</option>
			</select> <label style="display: block; margin: 12px 0; text-align: left;">
				<input type="checkbox" name="agreement" required> I agree to the <a href="terms.jsp" target="_blank">terms and conditions</a>.
			</label>



			<button type="submit">Create Account</button>
			<p>
				Already registered? <a href="Login.jsp">Go to Login</a>
			</p>
		</form>
	</div>
</body>
</html>

