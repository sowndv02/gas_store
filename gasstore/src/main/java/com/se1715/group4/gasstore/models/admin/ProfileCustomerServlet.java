/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAO;
import com.se1715.group4.gasstore.dao.DAOCategory;
import com.se1715.group4.gasstore.dao.DAOCustomer;
import com.se1715.group4.gasstore.dao.DAOOrder;
import com.se1715.group4.gasstore.dao.DAOOrderDetail;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOShipments;
import com.se1715.group4.gasstore.dao.DAOSupplier;
import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Shipments;
import com.se1715.group4.gasstore.dto.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(maxFileSize = 16177215)
public class ProfileCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet ProfileCustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileCustomerServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");

        DAOCustomer daoCustomers = new DAOCustomer();
        DAOSupplier daoSuppliers = new DAOSupplier();
        DAOOrderDetail daoOrderDetails = new DAOOrderDetail();
        DAO dao = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            if (type.equals("sup")) {

                Supplier entity = daoSuppliers.getSuppliersBySupplierID(id);
                if (!entity.isStatus()) {
                    request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                }
                int totalCategoriesBySupplier = dao.GetNumbers("(SELECT DISTINCT CategoryID FROM dbo.Product WHERE SupplierID = " + id + ") AS SubQueryAlias", "");
                int totalCategories = dao.GetNumbers("category", "");
                Vector<Order> listAllOrders = daoSuppliers.getOrderBySupplier(id);
                int totalOrders = listAllOrders.size();
                int totalProductSale = daoOrderDetails.TotalProductsSaleBySupplier(id);
                int totalProductsBySupplier = daoSuppliers.TotalProductBySupplier(id);
                double totalMoney = daoSuppliers.TotalMoneyBySupplier(id);

                Vector<Product> productBestSale = daoSuppliers.getProductBestSaleBySupplier(id);
                Vector<Category> numberProductsOfCategory = daoSuppliers.NumberOfProductsBySupplier(id);

                request.setAttribute("numberProductsOfCategory", numberProductsOfCategory);
                request.setAttribute("productBestSale", productBestSale);
                request.setAttribute("totalCategoriesBySupplier", totalCategoriesBySupplier);
                request.setAttribute("totalCategories", totalCategories);
                request.setAttribute("totalOrders", totalOrders);
                request.setAttribute("totalProductSale", totalProductSale);
                request.setAttribute("totalProductsBySupplier", totalProductsBySupplier);
                request.setAttribute("totalMoney", totalMoney);
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                request.setAttribute("listAllOrders", listAllOrders);
                request.getRequestDispatcher("profilecollaborators.jsp").forward(request, response);
            }
            if (type.equals("ship")) {
                DAOShipments daoShippers = new DAOShipments();
                Shipments entity = daoShippers.getShipperByShipperID(id);
                if (!entity.isStatus()) {
                    request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                }

                int totalSuppliersByShipper = daoShippers.TotalSuppliersByShipper(id);
                int totalSuppliers = dao.GetNumbers("Supplier", "");
                int totalOrders = daoShippers.TotalOrdersByShipper(id);
                int totalProducts = daoShippers.TotalProductsByShipper(id);
                int totalOrderSuccess = daoShippers.TotalOrderSuccessByShipper(id);
                int totalOrderFail = daoShippers.TotalOrderFailByShipper(id);
                Vector<Supplier> listSuppliers = daoShippers.GetNumberProductsByShipper(id);
                Vector<Order> listOrdersByShipperID = daoShippers.getOrderByShipperID(id);
                int numberOrderLate = daoShippers.NumberOrderLaterByShipper(id);

                request.setAttribute("numberOrderLate", numberOrderLate);
                request.setAttribute("listSuppliers", listSuppliers);
                request.setAttribute("totalOrderSuccess", totalOrderSuccess);
                request.setAttribute("totalOrderFail", totalOrderFail);
                request.setAttribute("totalProducts", totalProducts);
                request.setAttribute("totalOrders", totalOrders);
                request.setAttribute("totalSuppliers", totalSuppliers);
                request.setAttribute("listOrdersByShipperID", listOrdersByShipperID);
                request.setAttribute("totalSuppliersByShipper", totalSuppliersByShipper);
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                request.getRequestDispatcher("profilecollaborators.jsp").forward(request, response);
            }
            if (type.equals("customer")) {

                Customer entity = daoCustomers.getProfileCustomerById(id);
                if (!entity.isStatus()) {
                    request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                }

                Vector<Order> listAllOrders = daoCustomers.getOrdersByCustomerID(id);
                double rate = daoCustomers.rateOrders(id);
                String status = " WHERE customerId = " + id;
                double total = dao.GetTotal("TotalMoney", " [Order] ", status);

                request.setAttribute("total", total);
                request.setAttribute("rate", rate);
                request.setAttribute("listAllOrders", listAllOrders);
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                request.getRequestDispatcher("profilecustomer.jsp").forward(request, response);
            }

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

        try {
            DAO dao = new DAO();
            boolean lock = true;
            String type = request.getParameter("type");
            String companyName = request.getParameter("name");
            DAOSupplier daoSuppliers = new DAOSupplier();
            DAOShipments daoShippers = new DAOShipments();
            DAOCustomer daoCustomers = new DAOCustomer();
            String check = request.getParameter("lock");
            String newName = request.getParameter("compName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            if (check.equals("ON")) {
                lock = false;
            }
            if (type.equals("sup")) {
                Supplier entity = daoSuppliers.GetSupplierByCompanyName(companyName);
                
                String page = request.getParameter("page");
                
                if (Boolean.compare(entity.isStatus(), lock) != 0) {
                    entity.setStatus(lock);
                    if (!entity.isStatus()) {
                        request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                    }
                }
                if (dao.CheckCompanyAndEmail(" supplier ", newName, email, phone, page) <= 1 && !newName.isEmpty()) {
                    entity.setCompanyName(newName);
                    entity.setPhone(phone);
                    entity.setEmail(email);
                    entity.setHomePage(page);
                } else {
                    request.setAttribute("duplicate", "Email hoặc tên công ty hoặc số điện thoại hoặc homePage đã được sử dụng");
                }
                daoSuppliers.UpdateSupplier(entity);
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                
                response.sendRedirect("profile?type=" + type + "&id=" + entity.getSupplierId());
            }
            if (type.equals("ship")) {
                Shipments entity = daoShippers.getShipperByCompanyName(companyName);
                if (Boolean.compare(entity.isStatus(), lock) != 0) {
                    entity.setStatus(lock);
                    if (!entity.isStatus()) {
                        request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                    }
                }
                if (dao.CheckCompanyAndEmail("shipments", newName, email, phone, "") <= 1) {

                    entity.setCompanyName(newName);
                    entity.setEmail(email);
                    entity.setPhone(phone);
                    daoShippers.UpdateShipments(entity);
                } else {
                    request.setAttribute("duplicate", "Email hoặc tên công ty hoặc số điện thoại đã được sử dụng");
                }
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                response.sendRedirect("profile?type=" + type + "&id=" + entity.getShipmentId());
            }
            if (type.equals("customer")) {
                String id_raw = request.getParameter("customerID");
                int id = Integer.parseInt(id_raw);
                Customer entity = daoCustomers.GetCustomerById(id);
                if (Boolean.compare(entity.isStatus(), lock) != 0) {
                    entity.setStatus(lock);
                    daoCustomers.LockCustomers(entity);
                    if (!entity.isStatus()) {
                        request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                    }
                }
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                response.sendRedirect("profile?type=" + type + "&id=" + entity.getCustomerID());
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
