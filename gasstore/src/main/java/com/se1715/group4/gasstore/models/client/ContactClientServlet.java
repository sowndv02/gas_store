/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOFeedback;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAORole;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Feedback;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Item;
import com.se1715.group4.gasstore.dto.Role;
import com.se1715.group4.gasstore.util.SendMail;
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
public class ContactClientServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContactClientServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContactClientServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAORole daoRole = new DAORole();
        HttpSession session = request.getSession();
        Vector<Role> roles = daoRole.getRoleByFeedback();
        request.setAttribute("roles", roles);
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        request.getRequestDispatcher("contact.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        try {
            SendMail send = new SendMail();
            String customerName = request.getParameter("customerName");
            String customerEmail = request.getParameter("customerEmail");
            int role = Integer.parseInt(request.getParameter("role"));
            String contactSubject = request.getParameter("contactSubject");
            String contactMessage = request.getParameter("contactMessage");
            DAOFeedback daoFeedback = new DAOFeedback();
            Feedback f = new Feedback(customerName, contactSubject, customerEmail, contactMessage, role);
            int number = daoFeedback.AddNewFeedback(f);
            if(number > 0){
//                send.sendVerifiedFeedBack(customerEmail, txt);
                request.setAttribute("msg", "Phản hồi của bạn đã được chúng tôi ghi nhận. Vui lòng kiểm tra email.");
            } else request.setAttribute("msg", "Error");
            request.getRequestDispatcher("contact.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     private final String txt = "Xin chào! Cảm ơn bạn đã gửi phản hồi cho chúng tôi. "
    + "Chúng tôi gửi thư này để xác nhận rằng chúng tôi đã nhận được phản hồi từ bạn! Chúng tôi sẽ sớm hồi âm! "
    + "Xin chân thành cảm ơn về đóng góp của bạn! Chúc bạn có một ngày mới tốt lành!!";
}
