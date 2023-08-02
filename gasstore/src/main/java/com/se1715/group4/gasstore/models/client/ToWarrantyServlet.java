/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOOrder;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOWarranty;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Warranty;
import com.se1715.group4.gasstore.dto.WarrantyPolicy;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author CC
 */
@WebServlet(name = "ToWarrantyServlet", urlPatterns = {"/client/warranty"})
public class ToWarrantyServlet extends HttpServlet {

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
            out.println("<title>Servlet ToWarrantyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ToWarrantyServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Product war = new Product();
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        //try {
        //Customer ad = (Customer)session.getAttribute("account");
        int id = Integer.parseInt(request.getParameter("id"));
        int date = Integer.parseInt(request.getParameter("status"));
        DAOProduct daoW = new DAOProduct();
        DAOWarranty daow = new DAOWarranty();
        war = daoW.getInformationProduct(id);
        WarrantyPolicy warrantyPolicy = daow.getWarrantyPolicy(war.getCategory().getCategoryId());
        String[] sSentence = warrantyPolicy.getPolicy2().split("[.:]");
        Vector<String> s = new Vector<>();
        Vector<String> s1 = new Vector<>();
        for (String string : sSentence) {
            s.add(string);
        }
        sSentence = warrantyPolicy.getPolicy3().split("[.:]");
        for (String string : sSentence) {
            s1.add(string);
        }
        request.setAttribute("orderId", request.getParameter("orderId"));
        session.setAttribute("warranty", war);
        session.setAttribute("policy", s);
        session.setAttribute("policy1", s1);
        session.setAttribute("policy2", warrantyPolicy.getPolicy1());
        session.setAttribute("status", date);
        request.getRequestDispatcher("warranty.jsp").forward(request, response);
//        } catch (Exception e) {
//            request.setAttribute("error", "Vui lòng đăng nhập để sử dụng chức năng này");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            Customer customer = (Customer) session.getAttribute("account");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            DAOWarranty daoWarranty = new DAOWarranty();
            daoWarranty.RegisterWarranty(customer.getCustomerID(), orderId, productId);
            response.sendRedirect("order");
        } catch (Exception e) {
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
