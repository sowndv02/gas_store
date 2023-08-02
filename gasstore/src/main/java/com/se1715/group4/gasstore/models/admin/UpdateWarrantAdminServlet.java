/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOAdministrator;
import com.se1715.group4.gasstore.dao.DAOOrder;
import com.se1715.group4.gasstore.dao.DAOWarranty;
import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Warranty;
import com.se1715.group4.gasstore.dto.WarrantyImg;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.List;
import java.util.Vector;

@MultipartConfig(maxFileSize = 16177215)
/**
 *
 * @author ADMIN
 */
public class UpdateWarrantAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateWarrantAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateWarrantAdminServlet at " + request.getContextPath() + "</h1>");
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
        String wid = request.getParameter("id");
        String id_raw = request.getParameter("imgid");
        try {
            
            int id = Integer.parseInt(wid);
            DAOWarranty daoWarranty = new DAOWarranty();
            Warranty w = daoWarranty.getWarrantyById(id);
            Vector<WarrantyImg> listAllImage = daoWarranty.getAllImageWarrantyById(id);
            
            if (id_raw != null) {
                int idImg = Integer.parseInt(id_raw);
                for (int i = 0; i < listAllImage.size(); i++) {
                    if (listAllImage.get(i).getWarrantyImgId()== idImg) {
                        listAllImage.remove(i);
                        daoWarranty.DeleteImg(idImg);
                    }
                }

            }
            request.setAttribute("warranty", w);
            request.setAttribute("listAllImage", listAllImage);
            response.sendRedirect("warrantydetail?id=" + id);
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        DAOWarranty daoWarranty = new DAOWarranty();
        DAOAdministrator daoAdmin = new DAOAdministrator();
        try {
            List<Part> fileParts = (List<Part>) request.getParts();
            int id = Integer.parseInt(request.getParameter("id"));
            String aDay = request.getParameter("aDay");
            String des = request.getParameter("des");
            String note = request.getParameter("note");
            Administrator admin = daoAdmin.getAdminInfor(1);
            int method = Integer.parseInt(request.getParameter("method"));
            Warranty w = new Warranty(id, aDay, admin, note, des, method);
            Warranty after = daoWarranty.getWarrantyById(id);
            after.setAdmin(admin);
            session.setAttribute("warranty", after);
            daoWarranty.UpdateWarranty(w, fileParts);
            response.sendRedirect("proxy");
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
