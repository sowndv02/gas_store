/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOCategory;
import com.se1715.group4.gasstore.dao.DAODiscount;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOProductImage;
import com.se1715.group4.gasstore.dao.DAOSupplier;
import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.Discount;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.ProductImage;
import com.se1715.group4.gasstore.dto.Supplier;
import com.se1715.group4.gasstore.util.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@MultipartConfig
/**
 *
 * @author ADMIN
 */
public class AddProductAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProductAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductAdminServlet at " + request.getContextPath() + "</h1>");
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
        DAOSupplier daoSupplier = new DAOSupplier();
        DAOCategory daoCategories = new DAOCategory();
        DAODiscount daoDiscount = new DAODiscount();
        try {
            Vector<Category> listAllCate = daoCategories.getAllCategories();
            Vector<Supplier> listAllSup = daoSupplier.getAllSuppliers("");
            Vector<Discount> discounts = daoDiscount.getAllDiscount();

            request.setAttribute("discounts", discounts);
            request.setAttribute("listAllCate", listAllCate);
            request.setAttribute("listAllSup", listAllSup);

            request.getRequestDispatcher("newproduct.jsp").forward(request, response);

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
        DAOProduct daoProducts = new DAOProduct();
        DAOSupplier daoSuppliers = new DAOSupplier();
        DAOCategory daoCategories = new DAOCategory();
        DAOProductImage daoProductImage = new DAOProductImage();
        try {

            String productName = request.getParameter("productName");

            String categoryID_raw = request.getParameter("categoryID");
            String supplierID_raw = request.getParameter("supplierID");
            String price_raw = request.getParameter("price");
            String stock_raw = request.getParameter("stock");
            String discount_raw = request.getParameter("discount");
            String war = request.getParameter("warranty");
            if (!(!Validation.containsAlphabetCharacters(price_raw.replaceAll("\\.", "")) || !Validation.containsAlphabetCharacters(stock_raw)
                    || !Validation.containsAlphabetCharacters(war))) {
                request.setAttribute("msg", "Các thông tin stockQuantity, warranty phải là số!!");
                request.getRequestDispatcher("newproduct.jsp").forward(request, response);
            } else {
                String discontinued = request.getParameter("discontinued");
                String shortDescription = request.getParameter("shortDescription");
                String keyword = request.getParameter("keyword");
                String description = request.getParameter("description");
                boolean dis;
                if (discontinued.equals("ON")) {
                    dis = false;
                } else {
                    dis = true;
                }

                int discount = Integer.parseInt(discount_raw);

                int categoryID = Integer.parseInt(categoryID_raw);
                int supplierID = Integer.parseInt(supplierID_raw);
                double price = Double.parseDouble(price_raw.replaceAll("\\.", ""));
                int stock = Integer.parseInt(stock_raw);
                int warranty = Integer.parseInt(war);
                Product product = new Product(productName, keyword, shortDescription, description, categoryID, supplierID, dis, price, null, stock, discount, warranty);
                Administrator admin = new Administrator();
                admin.setAdministratorId(1);
                int newId = daoProducts.AddNewProduct(product, admin);
                Vector<Category> listAllCate = daoCategories.getAllCategories();
                Vector<Supplier> listAllSup = daoSuppliers.getAllSuppliers("");
                Product productNew = daoProducts.getProductNew(admin);
                product = daoProducts.getProductById(productNew.getProductId());

                try {
                    String uploadDirectory = getServletContext().getRealPath("/") + "images/";
                    String parentDirectory1 = new File(uploadDirectory).getParent();
                    String parentDirectory2 = new File(parentDirectory1).getParent();
                    String parentDirectory3 = new File(parentDirectory2).getParent();
                    String newFileName = "df.jpg";
                    checkFolder(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + newId);
                    if (request.getParts() != null && request.getParts().size() > 0) {
                        for (Part part : request.getParts()) {
                            if (part != null && part.getSize() > 0) {
                                String fileName = part.getSubmittedFileName();
                                if (fileName != null && !fileName.isEmpty()) {
                                    String sanitizedFileName = Paths.get(fileName).getFileName().toString();
                                    checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + newId + "\\" + sanitizedFileName);
                                    InputStream fileContent = part.getInputStream();
                                    Files.copy(fileContent, Paths.get(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + newId + "\\" + sanitizedFileName));
                                    Vector<String> imgs = new Vector<>();
                                    imgs.add(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + newId + "\\" + sanitizedFileName));
                                    DAOProductImage daoImg = new DAOProductImage();
                                    daoImg.AddImg(imgs, newId);
                                }
                            }

                        }
                    }

                    if (request.getPart("add") != null && request.getPart("add").getSize() > 0) {
                        checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + newId + "\\" + newFileName);
                        request.getPart("add").write(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + newId + "\\" + newFileName);
                        daoProducts.InsertImgProduct(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + newId + "\\" + newFileName), newId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Vector<ProductImage> listAllImage = daoProductImage.getAllImageProductByProductID(productNew.getProductId());
                Thread.sleep(3000);
                request.setAttribute("listAllImage", listAllImage);
                request.setAttribute("listAllCate", listAllCate);
                request.setAttribute("listAllSup", listAllSup);
                request.setAttribute("product", product);
                request.setAttribute("msg", "Thêm sản phẩm mới thành công! Thông tin của sản phẩm vừa thêm!");

                response.sendRedirect("updateproduct?pid=" + product.getProductId());
            }

        } catch (Exception e) {
        }
    }

    private void checkFolder(String name) {
        File uploadDir = new File(name);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    private void checkFile(String name) {
        File existingFile = new File(name);
        if (existingFile.exists()) {
            existingFile.delete();
        }
    }

    private String covertPath(String url) {
        String updatedPath1 = url.replace("\\", "/");
        String updatedPath2 = updatedPath1.substring(updatedPath1.indexOf("webapp/") + "images/".length());
        return updatedPath2;
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
