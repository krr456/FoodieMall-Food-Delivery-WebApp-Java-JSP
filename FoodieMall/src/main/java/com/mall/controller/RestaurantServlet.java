package com.mall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.daoImpl.RestaurantDaoI;
import com.mall.model.Restaurant;
import com.mall.model.User;

@WebServlet("/callServlet")
public class RestaurantServlet extends HttpServlet {
//	@Override
//	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
//		
//		RestaurantDaoI r = new RestaurantDaoI();
//		List<Restaurant> list = r.getAllRestaurant();
//		
////		for (Restaurant resta : list) {
////			
////			System.out.println(resta.getName());
////			System.out.println(resta.getAddress());
////			System.out.println(resta.getCuisineType());
////			System.out.println(resta.getEta());
////			System.out.println(resta.getImagePath());
////			System.out.println(resta.getRatings());
////			System.out.println(resta.getRestaurantId());
////			System.out.println(resta.getIsActive());
////			System.out.println(resta.getRestaurantOwnerId());
////			
////		}
//		
////		arg0.setAttribute("restaurants", list);
////		RequestDispatcher rd = arg0.getRequestDispatcher("Restaurant.jsp");
////		rd.forward(arg0, arg1);
//		
//	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RestaurantDaoI restaurantDAO = new RestaurantDaoI();
		List<Restaurant> restaurants = restaurantDAO.getAllRestaurant();

		request.setAttribute("restaurants", restaurants);

		// Retrieve user from session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
		rd.forward(request, response);
	}

}
