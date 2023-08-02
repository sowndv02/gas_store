/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAO;
import com.se1715.group4.gasstore.dao.DAOOrder;
import com.se1715.group4.gasstore.dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author ADMIN
 */
public class OrdersManagerServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrdersManagerServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrdersManagerServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DAOOrder daoOrders = new DAOOrder();
        DAO dao = new DAO();
        try {
            int sizeNew = dao.GetNumbers("[Order]", " WHERE Status = 2");
            int sizeShipping = dao.GetNumbers("[Order]", " WHERE Status = 3");
            int sizeOrders = dao.GetNumbers("[Order]", " WHERE Status = 1 OR Status = 0");
            Vector<Order> getNewOrders = daoOrders.getNewOrders("process");
            Vector<Order> getProcessOrders = daoOrders.getNewOrders("ship");
            Vector<Order> getDoneOrders = daoOrders.getNewOrders("done");
            request.setAttribute("sizeOrders", sizeOrders);
            request.setAttribute("sizeShipping", sizeShipping);
            request.setAttribute("sizeNew", sizeNew);
            request.setAttribute("getProcessOrders", getDoneOrders);
            request.setAttribute("getShippingOrders", getProcessOrders);
            request.setAttribute("getNewOrders", getNewOrders);
        } catch (Exception e) {
        }
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAOOrder daoOrders = new DAOOrder();
        String orderID_raw = request.getParameter("orderID");
        String from1 = request.getParameter("from1");
        String to1 = request.getParameter("to1");
        String from2 = request.getParameter("from2");
        String to2 = request.getParameter("to2");
        String from0 = request.getParameter("from0");
        String to0 = request.getParameter("to0");
        String type = request.getParameter("status");

        try {
            if (orderID_raw != null && !orderID_raw.matches("\\d+") ) {
                request.setAttribute("error", "Please Input Number!");
            } else {
                if(from1 != null && to1 != null && type.equalsIgnoreCase("process")){
                    Vector<Order> getNewOrders = daoOrders.SearchOrders(from1, to1, type);
                    request.setAttribute("getNewOrders", getNewOrders);
                    request.setAttribute("from1", from1);
                    request.setAttribute("to1", to1);
                }
                if(from2 != null && to2 != null && type.equalsIgnoreCase("done")){
                    Vector<Order> getProcessOrders = daoOrders.SearchOrders(from2, to2, type);
                    request.setAttribute("getProcessOrders", getProcessOrders);
                    request.setAttribute("from2", from2);
                    request.setAttribute("to2", to2);
                }
                if(from0 != null && to0 != null && type.equalsIgnoreCase("shipping")){
                    Vector<Order> getShippingOrders = daoOrders.SearchOrders(from0, to0, type);
                    request.setAttribute("getShippingOrders", getShippingOrders);
                    request.setAttribute("from0", from0);
                    request.setAttribute("to0", to0);
                }
                if(orderID_raw != null && type == null){
                    Order order = daoOrders.getOrdersByOrderID(Integer.parseInt(orderID_raw));
                    request.setAttribute("order", order);
                }
            }
            request.setAttribute("type", type);
            
            request.getRequestDispatcher("searchorder.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
