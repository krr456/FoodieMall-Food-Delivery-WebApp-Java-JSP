package com.mall.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.daoImpl.MenuDaoI;
import com.mall.model.Menu;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private MenuDaoI menuDao = new MenuDaoI();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String restaurantIdStr = request.getParameter("restaurantId");

		if (restaurantIdStr == null || restaurantIdStr.isEmpty()) {
			request.setAttribute("errorMessage", "Restaurant ID is missing.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Menu.jsp");
			dispatcher.forward(request, response);
			return;
		}

		try {
			int restaurantId = Integer.parseInt(restaurantIdStr);
			List<Menu> menuList = menuDao.getMenusByRestaurant(restaurantId);

			request.setAttribute("menuList", menuList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Menu.jsp");
			dispatcher.forward(request, response);

		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid restaurant ID.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Menu.jsp");
			dispatcher.forward(request, response);
		}
	}
}
