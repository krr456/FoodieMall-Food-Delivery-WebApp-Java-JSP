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

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private UserDao userDao = new UserDaoI();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId != null) {
			User user = userDao.getUser(userId);
			request.setAttribute("user", user);
			request.getRequestDispatcher("Profile.jsp").forward(request, response);
		} else {
			response.sendRedirect("Login.jsp");
		}
	}
}
