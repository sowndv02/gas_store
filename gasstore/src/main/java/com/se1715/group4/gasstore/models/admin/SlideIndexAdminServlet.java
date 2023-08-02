/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOSlide;
import com.se1715.group4.gasstore.dto.SlideShow;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Vector;

@MultipartConfig(maxFileSize = 16177215)
/**
 *
 * @author ADMIN
 */
public class SlideIndexAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet SlideIndexAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SlideIndexAdminServlet at " + request.getContextPath() + "</h1>");
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
        DAOSlide daoSlide = new DAOSlide();
        Vector<SlideShow> vector = daoSlide.getAllSlide();
        for (int i = 0; i < vector.size(); i++) {
            request.setAttribute("i" + i, vector.get(i).getImage());
        }
        request.getRequestDispatcher("slider.jsp").forward(request, response);
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
        DAOSlide daoSlide = new DAOSlide();
        try {

            try {
                String uploadDirectory = getServletContext().getRealPath("/") + "images/";
                String parentDirectory1 = new File(uploadDirectory).getParent();
                String parentDirectory2 = new File(parentDirectory1).getParent();
                String parentDirectory3 = new File(parentDirectory2).getParent();
                checkFolder(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\");

                if (request.getPart("add1") != null && request.getPart("add1").getSize() > 0) {
                    checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\" + request.getPart("add1").getSubmittedFileName());
                    request.getPart("add1").write(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\" + request.getPart("add1").getSubmittedFileName());
                    daoSlide.UpdateImgSlide(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\" + request.getPart("add1").getSubmittedFileName()), 1);
                }

                if (request.getPart("add2") != null && request.getPart("add2").getSize() > 0) {
                    checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\" + request.getPart("add2").getSubmittedFileName());
                    request.getPart("add2").write(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\" + request.getPart("add2").getSubmittedFileName());
                    daoSlide.UpdateImgSlide(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\" + request.getPart("add2").getSubmittedFileName()), 2);
                }

                if (request.getPart("add3") != null && request.getPart("add3").getSize() > 0) {
                    checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\" + request.getPart("add3").getSubmittedFileName());
                    request.getPart("add3").write(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\" + request.getPart("add3").getSubmittedFileName());
                    daoSlide.UpdateImgSlide(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\index\\banner\\" + request.getPart("add3").getSubmittedFileName()), 3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(3000);
            Vector<SlideShow> vector = daoSlide.getAllSlide();
            for (int i = 0; i < vector.size(); i++) {
                request.setAttribute("i" + i, vector.get(i).getImage());
            }
            request.getRequestDispatcher("slider.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
