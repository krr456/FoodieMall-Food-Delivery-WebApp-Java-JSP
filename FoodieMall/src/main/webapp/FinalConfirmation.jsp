<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Placed | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
<style>
body {
	font-family: 'Poppins', sans-serif;
	background-color: #ff5733;
	text-align: center;
	margin: 0;
	color: white;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	flex-direction: column;
}

.message {
	font-size: 50px;
	font-weight: bold;
	text-transform: uppercase;
	letter-spacing: 2px;
}

.sub-message {
	font-size: 22px;
	margin-top: 10px;
}

.checkmark {
	font-size: 80px;
	margin-bottom: 20px;
}
</style>
</head>
<body>

	<div class="checkmark">✅</div>
	<div class="message">CHILL, RELAX</div>
	<div class="message">FOOD IS ON YOUR WAY</div>
	<div class="sub-message">Estimated delivery time: 30-45 mins</div>

	<script>
		setTimeout(function() {
			window.location.href = "callServlet";
		}, 3000);
	</script>

</body>

</html>