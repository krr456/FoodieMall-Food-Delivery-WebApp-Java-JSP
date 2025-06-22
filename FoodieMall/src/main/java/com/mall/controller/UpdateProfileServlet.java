package com.mall.controller;

import com.mall.dao.UserDao;
import com.mall.daoImpl.UserDaoI;
import com.mall.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    private UserDao userDao = new UserDaoI();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String phoneStr = request.getParameter("phoneNo");
            String address = request.getParameter("address");

            User existingUser = userDao.getUser(userId);
            if (existingUser != null) {
                int phoneNo = (phoneStr != null && !phoneStr.trim().isEmpty()) ? Integer.parseInt(phoneStr.trim()) : 0;

                existingUser.setPhoneNo(phoneNo);
                existingUser.setAddress(address);
                existingUser.setLastlogin(LocalDateTime.now());
                userDao.updateUserContactInfo(existingUser);

                request.setAttribute("user", existingUser);
                request.setAttribute("successMsg", "Profile updated successfully!");
                request.getRequestDispatcher("Profile.jsp").forward(request, response);
            } else {
                response.sendRedirect("Login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
