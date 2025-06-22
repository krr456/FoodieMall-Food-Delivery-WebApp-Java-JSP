package com.mall.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.model.CartCreator;
import com.mall.model.CartItem;

@WebServlet("/CartViewServlet")
public class CartViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CartCreator cart = (CartCreator) session.getAttribute("cart");

		if (cart == null) {
			cart = new CartCreator();
			session.setAttribute("cart", cart);
		}

		request.setAttribute("cartItems", cart.getAllItems());
		RequestDispatcher rd = request.getRequestDispatcher("Cart.jsp");
		rd.forward(request, response);
	}
}
