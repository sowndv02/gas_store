/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOCustomer;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Product;
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
import java.io.InputStream;
import java.util.*;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(maxFileSize = 16177215)
public class InformationClientServlet extends HttpServlet {

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
            out.println("<title>Servlet InformationClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InformationClientServlet at " + request.getContextPath() + "</h1>");
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
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        DAOProduct daoProduct = new DAOProduct();
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        DAOCustomer daoCustomers = new DAOCustomer();
        InputStream inputStream = null;
        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            String fileName = extractFileName(part);
            if (fileName != null && !fileName.isEmpty() && isImageFile(fileName)) {
                inputStream = part.getInputStream();
                out.print(fileName);
                out.print(isImageFile(fileName));
            }
        }

        Customer cus = (Customer) session.getAttribute("account");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender_raw = request.getParameter("gender");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        boolean gender;
        int checkEmail = daoCustomers.checkCustomer(email, "email");
        int checkPhone = daoCustomers.checkCustomer(phone, "phone");
        if ((checkEmail > 1) || (checkPhone > 1)) {
            request.setAttribute("error", "Email hoặc số điện thoại đã được sử dụng vui lòng chọn Email khác!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);

        }
        try {
            gender = gender_raw.equals("male");
            cus.setPhone(phone);
            cus.setAddress(address);

            cus.setEmail(email);
            cus.setGender(gender);
            cus.setFirstName(firstName);
            cus.setLastName(lastName);
            daoCustomers.UpdateCustomer(cus, inputStream);
            Customer update = daoCustomers.GetCustomerById(cus.getCustomerID());
            cus.setBase64Image(update.getBase64Image());
            session.setAttribute("account", cus);
            request.setAttribute("error", "Cập nhật thành công!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            String[] tokens = contentDisp.split(";");
            for (String token : tokens) {
                if (token.startsWith("filename")) {
                    return token.substring(token.indexOf("=") + 2, token.length() - 1);
                }
            }
        }
        return null;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }

    private boolean isImageFile(String filePath) {
        // Get the file extension
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1);

        // Check if the file extension is an image file extension
        return extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("jfif")
                || extension.equalsIgnoreCase("webp")
                || extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("gif")
                || extension.equalsIgnoreCase("bmp");
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
