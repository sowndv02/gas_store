/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAO;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOReview;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Item;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Review;
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
public class ItemClientServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductsClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsClientServlet at " + request.getContextPath() + "</h1>");
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
        
        DAOReview daoReview = new DAOReview();
        try {
            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
            String pattern = request.getPathInfo();
            String[] parts = pattern.split("-");
            int id = Integer.parseInt(parts[parts.length - 1]);
            DAOProduct daoProduct = new DAOProduct();
            Product p = daoProduct.getInformationProduct(id);
            String description = p.getDescription();
            DAO dao = new DAO();
            Object c = session.getAttribute("account");
            Customer b = (Customer) session.getAttribute("customer");
            if (b != null) {
                Vector<Review> reviewCustomer = daoReview.getAllReviewByCustomer(id, b.getCustomerID());
                request.setAttribute("reviewCustomer", reviewCustomer);
            } else {
                if (c != null) {
                    Vector<Review> reviewCustomer = daoReview.getAllReviewByCustomer(id, ((Customer) c).getCustomerID());
                    request.setAttribute("reviewCustomer", reviewCustomer);
                }
            }

            int totalReview = dao.GetNumbers("review", " WHERE status = 1 AND productID = " + id);
            Vector<Review> reviews = daoReview.getAllReviewByProductId(id);
            Vector<Product> sameProduct = daoProduct.getProductByCategory(p.getCategory().getCategoryId(), "  AND isActive = 1 ");
            double rateAvg = daoReview.getAverageRate(id);
            String descriptionView = description.replace(":", "<br>- ");
            descriptionView = "- " + descriptionView.substring(0);

            request.setAttribute("product", p);
            request.setAttribute("reviews", reviews);
            request.setAttribute("rateAvg", rateAvg);
            request.setAttribute("sameProduct", sameProduct);
            request.setAttribute("totalReview", totalReview);
            request.setAttribute("descriptionView", descriptionView);
            request.getRequestDispatcher("/client/item.jsp").forward(request, response);
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
        try {
            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
            HttpSession session = request.getSession();
            Object acc = (Object) session.getAttribute("account");
            Customer cus = (Customer) session.getAttribute("customer");
            String currentURL = request.getRequestURL().toString();
            DAOReview daoReview = new DAOReview();
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
