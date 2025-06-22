package com.mall.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mall.dao.UserDao;
import com.mall.daoImpl.UserDaoI;
import com.mall.model.User;
import java.time.LocalDateTime;

@WebServlet("/SignupServlet") // Maps servlet
public class SignupServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = "customer"; // Default role

		UserDao userDao = new UserDaoI();

		if (!userDao.getVerifyByUserName(username).isEmpty()) {
			request.setAttribute("error", "Username already exists!");
			request.getRequestDispatcher("Signup.jsp").forward(request, response);
			return;
		}

		User newUser = new User(0, name, email, 0, "", username, password, role, LocalDateTime.now(),
				LocalDateTime.now());

		userDao.addUser(newUser);
		response.sendRedirect("Login.jsp");
	}
}
