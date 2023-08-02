/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.google.api.client.util.DateTime;
import com.se1715.group4.gasstore.dao.DAO;
import com.se1715.group4.gasstore.dao.DAOFaq;
import com.se1715.group4.gasstore.dto.Faq;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class UpdateFaqAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateFaqAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFaqAdminServlet at " + request.getContextPath() + "</h1>");
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
        int delete = 0, update = 0;
        try {
            delete = Integer.parseInt(request.getParameter("delete"));
        } catch (Exception e) {
        }
        try {
            update = Integer.parseInt(request.getParameter("update"));
        } catch (Exception e) {
        }
        DAOFaq daoFaq = new DAOFaq();
        Vector<Faq> listAllFaq = daoFaq.getAllFaq("");
        Faq faq;
        if (delete >= 1) {
            DAO dao = new DAO();
            if (daoFaq.deleteFaq(delete) == true) {
                request.setAttribute("message", "Xóa câu hỏi thành công");
            } else {
                request.setAttribute("message", "Xóa câu hỏi thất bại");
            }
            listAllFaq = daoFaq.getAllFaq("");
            request.setAttribute("listAllFaq", listAllFaq);
            request.getRequestDispatcher("managefaq.jsp").forward(request, response);
        }
        if (update >= 1) {
            faq = daoFaq.getFaqById(update);
            request.setAttribute("f", faq);
            request.getRequestDispatcher("updatefaq.jsp").forward(request, response);
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
        DAOFaq daoFaq = new DAOFaq();
        Vector<Faq> listAllFaq = daoFaq.getAllFaq("");
        int faqID = Integer.parseInt(request.getParameter("faqID"));
        String status_raw = request.getParameter("isActive");
        boolean isActive = status_raw.equals("ON");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        int createdBy = 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String createdDate = now.toString();

        DAO dao = new DAO();
        if (question.isEmpty() || answer.isEmpty() || createdDate.isEmpty()) {
            request.setAttribute("message", "Thông tin không được để trống!");
        } else {
            Faq f = new Faq(faqID, isActive, question, answer, createdBy, createdDate, null);
            daoFaq.updateFaq(f);
        }
        request.setAttribute("message", "Cập nhật câu hỏi thành công");
        response.sendRedirect("faqs");
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
