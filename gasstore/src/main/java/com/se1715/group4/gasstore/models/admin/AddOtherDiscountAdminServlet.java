/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAODiscount;
import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.Discount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;

/**
 *
 * @author nguye
 */
public class AddOtherDiscountAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AddDiscountAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDiscountAdminServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("othernewdiscount.jsp").forward(request, response);
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
        DAODiscount dao = new DAODiscount();
        Vector<Discount> listAllDiscountID = dao.getAllDiscountID();

        Discount lastDiscount = listAllDiscountID.get(listAllDiscountID.size() - 1);
        int discountID = lastDiscount.getDiscountId() + 1;
//        int discountID = Integer.parseInt(request.getParameter("discountID"));

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String isActiveParam = request.getParameter("isActive");
        boolean isActive = "ON".equals(isActiveParam);
        double discount = Double.parseDouble(request.getParameter("discount"));
        String couponCode = request.getParameter("couponCode");
        String startDate = request.getParameter("startDate");
        String expirationDate = request.getParameter("expirationDate");
        String createdDate = request.getParameter("createdDate");

        String[] provalues = request.getParameterValues("pro");

        Discount d = new Discount(discountID, name, description, isActive, discount, couponCode, startDate, expirationDate, createdDate);
        int number = dao.addNewDiscount(d);
        if (number > 0) {
            request.setAttribute("message", "Thêm mới thành công!!");
        }

        if (provalues != null || !(provalues.length == 0) ) {
            for (String provalue : provalues) {
                dao.UpdateDiscountIdInProduct(discountID, provalue);
            }
        }
        Vector<Discount> listAllDiscount = dao.getAllDiscountFull();

        request.setAttribute("listAllDiscount", listAllDiscount);
        request.getRequestDispatcher("discount.jsp").forward(request, response);
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
