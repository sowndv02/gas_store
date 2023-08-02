/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.google.gson.Gson;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOReview;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Review;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class UpdateReviewClientServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateReviewClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateReviewClientServlet at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");
        String currentURL = request.getParameter("url");
        out.print(type);
        try {
            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
            DAOReview daoReview = new DAOReview();
            int id = Integer.parseInt(request.getParameter("id"));
            if (type.equalsIgnoreCase("info")) {
                Review review = daoReview.getContent(id);
                request.setAttribute("r", review);
            }
            if (type.equals("delete")) {
                daoReview.deleteReview(id, "");
            }
//            response.sendRedirect(currentURL);
        } catch (Exception e) {
        }
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
        String type = request.getParameter("type");
        String currentURL = request.getParameter("url");
        try {
            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
            DAOReview daoReview = new DAOReview();

            if (type.equalsIgnoreCase("update")) {
                System.out.println("Ok");
                int id = Integer.parseInt(request.getParameter("rid"));
                int rate2 = Integer.parseInt(request.getParameter("rate2"));
                String comment2 = request.getParameter("comment2");
                daoReview.UpdateReview(new Review(id, rate2, comment2, null, null));
                response.sendRedirect(currentURL);
            }
            if (type.equals("add")) {
                Object acc = (Object) session.getAttribute("account");
                Customer cus = (Customer) session.getAttribute("customer");
                int pid = Integer.parseInt(request.getParameter("pid"));
                String comment = request.getParameter("comment");
                int rate = Integer.parseInt(request.getParameter("rate"));
                if (cus != null) {
                    Review r = new Review(cus.getCustomerID(), pid, rate, comment);
                    daoReview.AddNewReview(r);
                    response.sendRedirect(currentURL);
                } else {
                    Review r = new Review(((Customer) acc).getCustomerID(), pid, rate, comment);
                    daoReview.AddNewReview(r);
                    response.sendRedirect(currentURL);
                }
            }
            response.sendRedirect(currentURL);
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
