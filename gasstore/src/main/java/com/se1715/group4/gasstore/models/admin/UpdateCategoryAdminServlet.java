/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOCategory;
import com.se1715.group4.gasstore.dto.Category;
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
public class UpdateCategoryAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateCategoryAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCategoryAdminServlet at " + request.getContextPath() + "</h1>");
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

        DAOCategory daoCategory = new DAOCategory();

        String categoryID_raw = request.getParameter("cid");
        try {
            int categoryID = Integer.parseInt(categoryID_raw);
            Category category = daoCategory.GetInfoCategory(categoryID);
            Vector<Category> listAllCate = daoCategory.getAllCategories();
            request.setAttribute("category", category);
            request.setAttribute("listAllCate", listAllCate);
            request.getRequestDispatcher("updatecategory.jsp").forward(request, response);
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
        try {
            String categoryName = request.getParameter("catename");
            String code = request.getParameter("code");
            String keyword = request.getParameter("keyword");
            String des = request.getParameter("des");
            String categoryID_raw = request.getParameter("categoryID");
            int categoryID = Integer.parseInt(categoryID_raw);
            DAOCategory daoCategories = new DAOCategory();
            Category categoryNew = daoCategories.GetInfoCategory(categoryID);
            if (code.isEmpty() || categoryName.isEmpty()) {
                request.setAttribute("message", "Không được để trống code và tên!");
            } else {
                if (!categoryName.equalsIgnoreCase(categoryNew.getName()) || !code.equalsIgnoreCase(categoryNew.getCode())) {
                    if (!categoryName.equalsIgnoreCase(categoryNew.getName())) {
                        Category cateName = daoCategories.getCategoryByCol("name", categoryName);
                        if (cateName != null) {
                            request.setAttribute("message", "Name đã tồn tại!!");
                        }
                    }
                    if (!code.equalsIgnoreCase(categoryNew.getCode())) {
                        Category cateCode = daoCategories.getCategoryByCol("code", code);
                        if (cateCode != null) {
                            request.setAttribute("message", "Code đã tồn tại!!");
                        }
                    }
                } else {
                    categoryNew.setName(categoryName);
                    categoryNew.setCode(code);
                    categoryNew.setDescription(des);
                    categoryNew.setKeyword(keyword);
                    int n = daoCategories.UpdateCategory(categoryNew);
                    if (n > 0) {
                        request.setAttribute("message", "Cập nhật thành công!");
                    }
                }
            }

            Vector<Category> listAllCate = daoCategories.getAllCategories();
            request.setAttribute("listAllCate", listAllCate);
            request.setAttribute("category", categoryNew);
            request.getRequestDispatcher("updatecategory.jsp").forward(request, response);
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
