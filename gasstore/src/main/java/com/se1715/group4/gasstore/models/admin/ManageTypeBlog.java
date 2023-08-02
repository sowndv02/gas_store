/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOAdministrator;
import com.se1715.group4.gasstore.dao.DAOTypeBlog;
import com.se1715.group4.gasstore.dto.TypeBlog;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class ManageTypeBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        DAOTypeBlog daoTypeBlog = new DAOTypeBlog();
        ArrayList<TypeBlog> typeblogs = daoTypeBlog.getAllTypeBlogs();
        req.setAttribute("typeblogs", typeblogs);

        DAOAdministrator daoAdmin = new DAOAdministrator();
        Map<Integer, String> mapAdmin = daoAdmin.getMapAdminstratorName();
        req.setAttribute("mapAdmin", mapAdmin);

        req.getRequestDispatcher("managetypeblog.jsp").forward(req, resp);

    }
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String slugify(String input, int id) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = slug.toLowerCase(Locale.ENGLISH);
        return slug + "-" + id;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equals("update1")) {
            String updattingTypeId = req.getParameter("typeId");
            req.setAttribute("updattingTypeId", updattingTypeId);
        }
        
        if (action.equals("update2")) {
            String typeId_raw = req.getParameter("typeId");
            try {
                int typeId = Integer.parseInt(typeId_raw);
                String updatingContent = req.getParameter("updatingContent");
                DAOTypeBlog daoTypeBlog = new DAOTypeBlog();
                daoTypeBlog.updateTypeBlog(typeId, updatingContent);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        
        if (action.equals("insert")) {
            DAOTypeBlog DAOTypeBlog = new DAOTypeBlog();
            int id = DAOTypeBlog.getCurrentTypeBlogID() + 1;
            String typeName = req.getParameter("typeName");
            String code = slugify(typeName, id);
            DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime ldt = LocalDateTime.now();
            String createdDate = ldt.format(CUSTOM_FORMATTER);
            int createdBy = 1;
            DAOTypeBlog = new DAOTypeBlog();
            DAOTypeBlog.insertTypeBlog(new TypeBlog(id, code, typeName, createdDate, createdBy));
        }
        doGet(req, resp);
    }

}
