/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOAdministrator;
import com.se1715.group4.gasstore.dto.Administrator;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class LoginAdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginAdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        DAOAdministrator daoAdmin = new DAOAdministrator();
        if (username == null || password == null) {
            request.setAttribute("error", "Please fill in all the required fields.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        Administrator admin = daoAdmin.LoginAdmin(username, password);
        if (admin == null) {
            request.setAttribute("error", "Tài khoản mật khẩu không chính xác vui lòng nhập lại!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {

            if (admin != null && admin.getRoleId() != 1) {

                request.setAttribute("error", "Tài khoản của bạn không được đăng nhập vào khu vực này này!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            if (admin != null && admin.isIsActive() && admin.getRoleId() == 1) {
                session.setAttribute("admin", admin);
                Cookie cuser = new Cookie("user", username);
                Cookie cpass = new Cookie("pass", password);
                Cookie cr = new Cookie("cr", remember);
                if (remember == null) {
                    cuser.setMaxAge(0);
                    cpass.setMaxAge(0);
                    cr.setMaxAge(0);
                } else {
                    cuser.setMaxAge(60 * 60 * 60);
                    cpass.setMaxAge(60 * 60 * 60);
                    cr.setMaxAge(60 * 60 * 60);
                }
                response.addCookie(cr);
                response.addCookie(cuser);
                response.addCookie(cpass);
                response.sendRedirect("index");
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
