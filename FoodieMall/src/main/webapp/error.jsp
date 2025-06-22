<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Something Went Wrong</title>
<style>
body {
	margin: 0;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: linear-gradient(to right, #ff6e7f, #bfe9ff);
	color: #333;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.error-container {
	background-color: white;
	padding: 40px 60px;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
	text-align: center;
	max-width: 500px;
}

.error-container h1 {
	font-size: 36px;
	color: #d9534f;
	margin-bottom: 20px;
}

.error-container p {
	font-size: 16px;
	line-height: 1.6;
	color: #555;
}

.btn {
	margin-top: 25px;
	display: inline-block;
	padding: 12px 24px;
	background-color: #5bc0de;
	color: #fff;
	text-decoration: none;
	border-radius: 8px;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #31b0d5;
}

.debug-info {
	margin-top: 20px;
	color: #999;
	font-size: 12px;
	display: none;
}
</style>
</head>
<body>
	<div class="error-container">
		<h1>Oops! An error occurred.</h1>
		<p>We're sorry, but something went wrong while processing your
			request.</p>
		<p>Please try again later, or contact support if the issue
			persists.</p>
		<a href="Profile.jsp" class="btn">Back to Profile</a>

		<div class="debug-info">
			<%=exception != null ? exception.toString() : "No exception details available."%>
		</div>
	</div>
</body>
</html>
