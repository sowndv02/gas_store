/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOAttribute;
import com.se1715.group4.gasstore.dao.DAOCategory;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOSlide;
import com.se1715.group4.gasstore.dto.Attribute;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Item;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.SlideShow;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(maxFileSize = 16177215)
public class IndexClientServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
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
        // Forward the request to a JSP or servlet that renders your HTML page
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);

        DAOCategory daoCategory = new DAOCategory();
        DAOProduct daoProducts = new DAOProduct();
        DAOSlide daoSlide = new DAOSlide();

        Vector<SlideShow> vector = daoSlide.getAllSlide();
        for (int i = 0; i < vector.size(); i++) {
            request.setAttribute("i" + i, vector.get(i).getImage());
        }

        Vector<Product> binhgas = daoProducts.getProductByCategory(1, " AND isActive = 1");
        Vector<Product> bepgas = daoProducts.getProductByCategory(2, " AND isActive = 1");
        Vector<Product> phukien = daoProducts.getProductByCategory(3, " AND isActive = 1");
        Vector<Category> categories = daoCategory.getAllCategories();

        request.setAttribute("binhgas", binhgas);
        request.setAttribute("bepgas", bepgas);
        request.setAttribute("categories", categories);
        request.setAttribute("phukien", phukien);

        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        String key = request.getParameter("key");

        if (key != null && !key.isEmpty()) {
            DAOProduct daoProduct = new DAOProduct();
            Vector<Product> listAllType = daoProduct.SearchProduct(key);
            request.setAttribute("listAllType", listAllType);
            request.setAttribute("key", key);

            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
            ArrayList<Footer> footers = daoHeaderFooter.getFooters();
            request.setAttribute("footers", footers);
            ArrayList<Header> headers = daoHeaderFooter.getHeaders();
            request.setAttribute("headers", headers);

            request.getRequestDispatcher("search.jsp").forward(request, response);
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
