<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*, java.util.List, com.mall.model.User"%>
<!DOCTYPE html>
<html>
<head>
<title>Login | FoodieMall</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>

	<!--

List<User> users = (List<User>) request.getAttribute("user");
	for(User menu : users) {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		
		request.setAttribute("name", username);
	
		response.setContentType("text/html");
		
		if(menu.getPassword().equals(password)) {
			out.print("Welcome Home raa");
			RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
			rd.forward(request, response);
			
		}
		else if (count>0) {
			out.print("Invalid Password, "+count+" Attempts left.");
			count--;
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.include(request, response);
			
		}
		else {
			out.print("Attempts over");
		}
  -->

	<div class="container">
		<h2>Welcome Back!</h2>
		<form action="LoginServlet" method="post">
			<input type="text" name="username" placeholder="Username" required><input
				type="password" name="password" placeholder="Password" required>
			<button type="submit">Log In</button>
			<p>
				New here? <a href="Signup.jsp">Create an account</a>
			</p>
		</form>

		<%
		String errorMessage = (String) request.getAttribute("error");
		if (errorMessage != null) {
		%>
		<p style="color: red;"><%=errorMessage%></p>
		<%
		}
		%>
	</div>

</body>
</html>


