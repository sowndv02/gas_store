/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.admin;

import com.google.gson.Gson;
import com.se1715.group4.gasstore.dto.Warranty;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ProxyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scriptUrl = "https://script.google.com/macros/s/AKfycbwvBsdwEsciXTP54fRzYIOURIchIN-PsQk15mf4SzDGAbPYwrpqiC20u-yQ2jRVSXHJSw/exec";

        // Get the customerName and phoneNumber parameters from the servlet request
        HttpSession session = request.getSession();
        Warranty warranty = (Warranty) session.getAttribute("warranty");
        

        
        Gson gson = new Gson();
        
        // Serialize the warranties object to JSON
        String warrantiesJson = gson.toJson(warranty);
        
        // URL encode the JSON data
        String encodedWarrantiesJson = URLEncoder.encode(warrantiesJson, StandardCharsets.UTF_8);

        // Serialize the warranties object to JSON

        // URL encode the JSON data

        // Encode the parameters for URL

        // Append the parameters to the script URL
        String modifiedScriptUrl = scriptUrl + "?&warranty=" + encodedWarrantiesJson;

        // Get the actual download URL from the modified script URL
        String downloadUrl = getDownloadUrl(modifiedScriptUrl);

        if (downloadUrl != null) {
            URL url = new URL(downloadUrl);
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            // Set the response headers
            response.setContentType(connection.getContentType());
            response.setHeader("Content-Disposition", "attachment; filename=\"example.pdf\"");

            // Get the output stream from the response
            OutputStream outputStream = response.getOutputStream();

            // Create a buffer to hold the data while transferring
            byte[] buffer = new byte[8192];
            int bytesRead;

            // Read data from the input stream and write it to the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close the streams
            inputStream.close();
            outputStream.close();
            response.sendRedirect("index.html");
        } else {
            // Handle the case when the download URL couldn't be retrieved
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String getDownloadUrl(String scriptUrl) throws IOException {
        URL url = new URL(scriptUrl);
        URLConnection connection = url.openConnection();

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        String downloadUrl = null;

        // Read the response and search for the download URL
        while ((line = reader.readLine()) != null) {
            if (line.contains("https://drive.google.com/uc?id=")) {
                downloadUrl = line;
                break;
            }
        }

        reader.close();
        inputStream.close();

        return downloadUrl;
    }
}