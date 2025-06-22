package com.mall.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mall.dao.UserDao;
import com.mall.daoImpl.UserDaoI;
import com.mall.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		Integer count = (Integer) session.getAttribute("count");

		if (count == null) {
			count = 3;
		}

		UserDao userDao = new UserDaoI();
		List<User> userList = userDao.getVerifyByUserName(username);

		if (userList.isEmpty()) {
			request.setAttribute("error", "User not found!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}

		User user = userList.get(0); // Assuming unique usernames

		if (user.getPassword().equals(password)) {
			// Success
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getUserId());
			session.removeAttribute("count"); // Reset attempt counter
			response.sendRedirect("callServlet");
		} else {
			// Failed login
			count--;
			session.setAttribute("count", count);
			if (count > 0) {
				request.setAttribute("error", "Invalid password. " + count + " attempt(s) left.");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			} else {
				session.invalidate();
				request.setAttribute("error", "Too many failed attempts. Session terminated.");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
	}
}
