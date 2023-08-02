/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAO;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOReview;
import com.se1715.group4.gasstore.dto.Product;
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
public class ReviewsProductAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet ReviewsProductAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReviewsProductAdminServlet at " + request.getContextPath() + "</h1>");
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
        DAO dao = new DAO();
        DAOReview daoReview = new DAOReview();
        Vector<Product> top5Review = daoReview.getReviewsProductsByAdmin();
        int totalReview = dao.GetNumbers(" Review ", "");
        request.setAttribute("totalReview", totalReview);
        request.setAttribute("top5Review", top5Review);
        request.getRequestDispatcher("reviews.jsp").forward(request, response);
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
        DAOReview daoReview = new DAOReview();
        String total = request.getParameter("total");
        try {
            int amount = Integer.parseInt(total);
            Vector<Product> vector = daoReview.getNextReviewsProducts(amount);
            for (Product products : vector) {
                out.print("<div class=\"products\">\n"
                        + "   <h4 class=\"\" style=\"margin-left: 20px\"><a href=\"" + request.getContextPath() + "/user/item?pid=" + products.getProductId()+ "\">" + "(#"+products.getCode()+")"+products.getName()+ "</a> </h4>\n");

                // Loop through the reviews and include them in the output
                for (Review review : products.getReviews()) {
                    out.print("<div class=\"d-flex flex-row comment-row mt-0\">\n"
                            + "   <div class=\"p-2\">\n"
                            + "      <img src=\"data:image/jpg;base64," + review.getCustomer().getBase64Image() + "\" alt=\"user\" width=\"50\" class=\"rounded-circle\" />\n"
                            + "   </div>\n"
                            + "   <div class=\"comment-text w-100\">\n"
                            + "      <h6 class=\"font-medium\"><a href=\"" + request.getContextPath() + "/admin/profile?type=customer&id=" + review.getCustomer().getCustomerID() + "\">" + review.getCustomer().getFirstName()+ " "+ review.getCustomer().getLastName() + "</a></h6>\n"
                            + "      <span class=\"mb-3 d-block\">(" + review.getRate() + " <i class=\"fas fa-star\" style=\"color: gold;\"></i>)" + review.getContent()+ "\n");
                    if (review.isStatus()) {
                        out.print("<i class=\"mdi mdi-check-circle\"></i>\n");
                    }
                    if (!review.isStatus()) {
                        out.print("<i class=\"mdi mdi-block-helper\"></i>\n");
                    }
                    out.print("      </span>\n"
                            + "      <div class=\"comment-footer\">\n"
                            + "         <span class=\"text-muted float-end\">" + review.getDateRate() + "</span>\n"
                            + "         <form action=\"\" method=\"POST\" id=\"review\" name=\"review\">\n"
                            + "            <button type=\"button\" onclick=\"changeReview('public', '" + review.getReviewId()+ "')\" class=\"  btn btn-success btn-sm text-white");
                    if (review.isStatus()) {
                        out.print(" disabled");
                    }
                    out.print("\">\n"
                            + "               Publish\n"
                            + "            </button>\n"
                            + "            <button type=\"button\" onclick=\"changeReview('hidden', '" + review.getReviewId() + "')\" class=\"btn btn-danger btn-sm text-white");
                    if (!review.isStatus()) {
                        out.print(" disabled");
                    }
                    out.print("\">\n"
                            + "               Hidden\n"
                            + "            </button>\n"
                            + "         </form>\n"
                            + "      </div>\n"
                            + "   </div>\n"
                            + "</div>\n");
                }

                out.print("<hr>\n"
                        + "</div>");
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
