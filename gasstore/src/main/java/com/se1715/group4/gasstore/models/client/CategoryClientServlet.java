/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOSupplier;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Supplier;
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
public class CategoryClientServlet extends HttpServlet {

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
            out.println("<title>Servlet CategoryClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryClientServlet at " + request.getContextPath() + "</h1>");
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
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();

            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
            
            String pattern = request.getPathInfo();

            String[] parts = pattern.split("-");
            int id = Integer.parseInt(parts[parts.length - 1]);

            DAOProduct daoProduct = new DAOProduct();
            DAOSupplier daoSupplier = new DAOSupplier();
            Vector<Supplier> listAllSuppliersType = daoSupplier.GetSupplierByType(id, " AND status = 1 ");

            double maxPrice = daoProduct.getTopPrice("DESC ");
            request.setAttribute("maxPrice", maxPrice);
            double minPrice = daoProduct.getTopPrice("ASC ");
            request.setAttribute("minPrice", minPrice);

            String orderby_raw = request.getParameter("orderby");

            if (orderby_raw == null) {
                orderby_raw = "1";
            }

            String[] sid_raw = request.getParameterValues("sid");
            int[] sid = null;
            if (sid_raw != null) {
                sid = new int[sid_raw.length];
                for (int i = 0; i < sid.length; i++) {
                    sid[i] = Integer.parseInt(sid_raw[i]);
                }
            }

            String from_raw = request.getParameter("from");
            String to_raw = request.getParameter("to");
            double from = minPrice, to = maxPrice;

            if (from_raw != null && to_raw != null) {
                from = Double.parseDouble(from_raw);
                to = Double.parseDouble(to_raw);
            }
            request.setAttribute("to", to);
            request.setAttribute("from", from);
            String statusDiscount = "";
            if(id == 0){
                statusDiscount = "yes";
            }
            Vector<Product> vector = daoProduct.getProductsBySuppliers(sid, id, orderby_raw, from, to, statusDiscount);
            String link = "";
            boolean[] cid = new boolean[listAllSuppliersType.size()];
            for (int i = 0; i < cid.length; i++) {
                if (isCheck(listAllSuppliersType.get(i).getSupplierId(), sid)) {
                    cid[i] = true;
                    link = link + "&sid=" + listAllSuppliersType.get(i).getSupplierId();
                } else {
                    cid[i] = false;
                }
            }
            if (!link.isEmpty()) {
                link = link + "&";
            }

            int numPs = vector.size();
            int numperPage = 9;
            int numpage = numPs / numperPage + (numPs % numperPage == 0 ? 0 : 1);
            int start, end;
            String tpage = request.getParameter("page");
            int page;

            try {
                page = Integer.parseInt(tpage);
            } catch (NumberFormatException e) {
                page = 1;
            }
            start = (page - 1) * numperPage;
            if (page * numperPage > numPs) {
                end = numPs;
            } else {
                end = page * numperPage;
            }

            request.setAttribute("link", link);
            Vector<Product> vector1 = daoProduct.getListByPage(vector, start, end);
            request.setAttribute("listAllType", vector1);
            request.setAttribute("page", page);
            request.setAttribute("num", numpage);
            request.setAttribute("cid", cid);
            request.setAttribute("orderby", orderby_raw);

            request.setAttribute("type", pattern.substring(1));
            request.setAttribute("listAllSuppliersType", listAllSuppliersType);

            
            request.getRequestDispatcher("/client/shopping.jsp").forward(request, response);
        } catch (Exception e) {
        }

    }

    private boolean isCheck(int d, int[] id) {
        if (id == null) {
            return false;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == d) {
                    return true;
                }
            }
            return false;
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
        processRequest(request, response);
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
