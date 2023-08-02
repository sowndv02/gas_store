/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.*;
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

/**
 *
 * @author ADMIN
 */
@MultipartConfig(maxFileSize = 16177215)
public class UpdateProductAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductAdminServlet at " + request.getContextPath() + "</h1>");
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
        String productID_raw = request.getParameter("pid");
        DAOProduct daoProducts = new DAOProduct();
        DAOSupplier daoSupplier = new DAOSupplier();
        DAOCategory daoCategories = new DAOCategory();
        DAODiscount daoDiscount = new DAODiscount();
        DAOProductImage daoProductImage = new DAOProductImage();
        String id_raw = request.getParameter("imgid");
        try {
            int productID = Integer.parseInt(productID_raw);

            Product product = daoProducts.getInformationProduct(productID);
            Vector<Category> listAllCate = daoCategories.getAllCategories();
            Vector<Supplier> listAllSup = daoSupplier.getAllSuppliers("");
            Vector<ProductImage> listAllImage = daoProductImage.getAllImageProductByProductID(productID);
            Vector<Discount> discounts = daoDiscount.getAllDiscount();

            String uploadDirectory = getServletContext().getRealPath("/") + "images/";
            String parentDirectory1 = new File(uploadDirectory).getParent();
            String parentDirectory2 = new File(parentDirectory1).getParent();
            String parentDirectory3 = new File(parentDirectory2).getParent();
            if (id_raw != null) {
                int id = Integer.parseInt(id_raw);
                for (int i = 0; i < listAllImage.size(); i++) {
                    if (listAllImage.get(i).getProductImgId() == id) {
                        checkFile(parentDirectory3 + "\\src\\main\\webapp\\" + convertPath2(listAllImage.get(i).getBase64Image()));
                        listAllImage.remove(i);
                        daoProductImage.DeleteProductImg(id);
                    }
                }

            }
            listAllImage = daoProductImage.getAllImageProductByProductID(productID);
            request.setAttribute("product", product);
            request.setAttribute("discounts", discounts);
            request.setAttribute("listAllImage", listAllImage);
            request.setAttribute("listAllCate", listAllCate);
            request.setAttribute("listAllSup", listAllSup);
            request.getRequestDispatcher("confirmproduct.jsp").forward(request, response);
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

    private String convertPath2(String url) {
        String updatedPath1 = url.replace("/", "\\");
        String updatedPath2 = "" + updatedPath1;
        return updatedPath2;
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

            String productIDOld = request.getParameter("pid");
            String categoryID_raw = request.getParameter("categoryID");
            String supplierID_raw = request.getParameter("supplierID");
            String price_raw = request.getParameter("price");
            String stock_raw = request.getParameter("stock");
            String discount_raw = request.getParameter("discount");
            String discontinued = request.getParameter("discontinued");
            String war = request.getParameter("warranty");

            if (!(!Validation.containsAlphabetCharacters(price_raw.replaceAll("\\.", "")) || !Validation.containsAlphabetCharacters(stock_raw)
                    || !Validation.containsAlphabetCharacters(war))) {
                request.setAttribute("msg", "Các thông tin stockQuantity, warranty phải là số!!");
            } else {
                boolean dis;
                if (discontinued.equals("ON")) {
                    dis = false;
                } else {
                    dis = true;
                }
                Part filePart = request.getPart("photo");

                List<Part> fileParts = (List<Part>) request.getParts();

                int productID = Integer.parseInt(productIDOld);
                String price_rep = price_raw.replace(".", "");
                int categoryID = Integer.parseInt(categoryID_raw);
                int supplierID = Integer.parseInt(supplierID_raw);
                double price = Double.parseDouble(price_rep);
                int stock = Integer.parseInt(stock_raw);
                int discount = Integer.parseInt(discount_raw);
                int warraty = Integer.parseInt(war);

                Supplier supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                Category category = daoCategories.GetCategoryById(categoryID);
                Product product = daoProducts.getInformationProduct(productID);
                product.setName(productName);
                product.setCategory(category);
                product.setSupplier(supplier);
                product.setUnitPrice(price);
                product.setDiscountId(discount);
                product.setStockQuantity(stock);
                product.setWarranty(warraty);
                product.setShortDescription(request.getParameter("shortDescription"));
                product.setKeyword(request.getParameter("keyword"));
                product.setDescription(request.getParameter("description"));

                try {
                    String uploadDirectory = getServletContext().getRealPath("/") + "images/";
                    String parentDirectory1 = new File(uploadDirectory).getParent();
                    String parentDirectory2 = new File(parentDirectory1).getParent();
                    String parentDirectory3 = new File(parentDirectory2).getParent();
                    String newFileName = "df.jpg";
                    checkFolder(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + productID);
                    if (request.getParts() != null && request.getParts().size() > 0) {
                        for (Part part : request.getParts()) {
                            if (part != null && part.getSize() > 0) {
                                String fileName = part.getSubmittedFileName();
                                if (fileName != null && !fileName.isEmpty()) {
                                    String sanitizedFileName = Paths.get(fileName).getFileName().toString();
                                    checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + productID + "\\" + sanitizedFileName);
                                    InputStream fileContent = part.getInputStream();
                                    Files.copy(fileContent, Paths.get(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + productID + "\\" + sanitizedFileName));
                                    Vector<String> imgs = new Vector<>();
                                    imgs.add(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + productID + "\\" + sanitizedFileName));
                                    DAOProductImage daoImg = new DAOProductImage();
                                    daoImg.AddImg(imgs, productID);
                                }
                            }

                        }
                    }

                    if (request.getPart("add") != null && request.getPart("add").getSize() > 0) {
                        checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + productID + "\\" + newFileName);
                        request.getPart("add").write(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + productID + "\\" + newFileName);
                        daoProducts.InsertImgProduct(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\products\\" + productID + "\\" + newFileName), productID);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int n = daoProducts.UpdateProduct(product);
                out.println(n);
                if (n > 1) {
                    request.setAttribute("msg", "Cập nhật thành công!!");
                }
                Thread.sleep(3000);
                DAODiscount daoDiscount = new DAODiscount();
                Vector<Discount> discounts = daoDiscount.getAllDiscount();
                
                Vector<Category> listAllCate = daoCategories.getAllCategories();
                Vector<Supplier> listAllSup = daoSuppliers.getAllSuppliers("");
                Vector<ProductImage> listAllImage = daoProductImage.getAllImageProductByProductID(productID);

                request.setAttribute("listAllImage", listAllImage);
                request.setAttribute("listAllCate", listAllCate);
                request.setAttribute("listAllSup", listAllSup);
                request.setAttribute("discounts", discounts);
                product = daoProducts.getInformationProduct(productID);
                request.setAttribute("product", product);
            }
            request.getRequestDispatcher("confirmproduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
