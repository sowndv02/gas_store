/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dto.Footer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class UpdateFooter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
            Footer footer = daoHeaderFooter.getFooterById(id);
            request.setAttribute("footer", footer);
            request.getRequestDispatcher("updatefooter.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        try {
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            pw.print(id);
            String content = request.getParameter("content");
            String description = request.getParameter("description");
            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
            Footer footer = new Footer(id, content, id_raw, description);
            daoHeaderFooter.updateFooter(footer);
            response.sendRedirect("managefooter");
                    
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
