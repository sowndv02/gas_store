/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.OrderDetail;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Shipments;
import com.se1715.group4.gasstore.dto.Warranty;
import com.se1715.group4.gasstore.dto.WarrantyImg;
import com.se1715.group4.gasstore.dto.WarrantyPolicy;
import com.se1715.group4.gasstore.util.DBUtil;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOWarranty {

    private final Connection connection = DBUtil.makeConnection();

    public Vector<Warranty> getProductTop5(){
        Vector<Warranty> vector = new Vector<>();
        DAOProduct daoProduct = new DAOProduct();
        String sql = "SELECT ProductId, COUNT(*) as Number, MONTH(createdDate) as CurrentMonth FROM dbo.Warranty WHERE status = 1 AND MONTH(createdDate) = MONTH(GETDATE()) GROUP BY productID, status, MONTH(createdDate) HAVING COUNT(*) >= 5";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                int pid = rs.getInt("ProductId");
                int number = rs.getInt("Number");
                int currentMonth = rs.getInt("CurrentMonth");
                Product product = daoProduct.getInformationProduct(pid);
                vector.add(new Warranty(product, number, currentMonth));
            }
        } catch (SQLException ex) {
        }
        return vector;
    }
    
    public int UpdateWarranty(Warranty w, List<Part> input) {
        Warranty old = getWarrantyById(w.getWarrantyId());
        String sql = "UPDATE Warranty SET AdministratorId = ?, Status = ?, Note = ?, appointmentDate = ?, description = ?, method = ? WHERE warrantyID = ?";
        if (old.getStatus() == 2) {
            w.setStatus(3);
        }
        if (w.getMethod() == 0) {
            w.setStatus(0);
            sql = "UPDATE Warranty SET AdministratorId = ?, Status = ?, Note = ?, appointmentDate = ?, description = ?, method = ?, finishDay = GETDATE() WHERE warrantyID = ?";
        }
        if (old.getStatus() == 3) {
            w.setStatus(1);
            sql = "UPDATE Warranty SET AdministratorId = ?, Status = ?, Note = ?, appointmentDate = ?, description = ?, method = ?, finishDay = GETDATE() WHERE warrantyID = ?";
        }
        int number = 0;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, w.getAdmin().getAdministratorId());
            pre.setInt(2, w.getStatus());
            pre.setString(3, w.getNote());
            pre.setString(4, w.getAppointmentDate());
            pre.setString(5, w.getDescription());
            pre.setInt(6, w.getMethod());
            pre.setInt(7, w.getWarrantyId());
            number = pre.executeUpdate();
            number += AddImg(input, w.getWarrantyId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public int DeleteImg(int id) {
        int number = 0;
        String sql = "DELETE WarrantyImg WHERE warrantyImgId =?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProductImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public Vector<WarrantyImg> getAllImageWarrantyById(int wId) {
        Vector<WarrantyImg> vector = new Vector<>();
        String sql = "SELECT * FROM WarrantyImg WHERE warrantyId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, wId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("warrantyImgId");
                int wID = rs.getInt("warrantyId");
                Blob blob = rs.getBlob("Image");
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    try {
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] imageBytes = outputStream.toByteArray();
                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                        inputStream.close();
                        outputStream.close();
                        vector.add(new WarrantyImg(id, wID, base64Image));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    vector.add(new WarrantyImg(id, wID, null));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int AddImg(List<Part> fileParts, int pid) {
        int number = 0;
        String sql = "INSERT INTO WarrantyImg(warrantyID, Image) VALUES(?, ?)";
        if (!fileParts.isEmpty()) {
            for (Part file : fileParts) {
                long fileSize = file.getSize();
                String fileName = getFileName(file);
                if (file != null && fileSize > 1000 && isImageFile(fileName)) {
                    try {
                        PreparedStatement pre = connection.prepareStatement(sql);
                        pre.setInt(1, pid);
                        pre.setBinaryStream(2, file.getInputStream());
                        pre.executeUpdate();
                        number++;
                    } catch (Exception e) {
                    }
                }
            }
        }
        return number;
    }

    public String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            String[] tokens = contentDisp.split(";");
            for (String token : tokens) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf("=") + 2, token.length() - 1);
                }
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

    public int RegisterWarranty(int cid, int oid, int pid) {
        int number = 0;
        String sql = "INSERT INTO dbo.Warranty( productID, customerID, OrderId,AdministratorId, status, note, createdDate, appointmentDate, finishDay, description) VALUES( ?, ?, ?,NULL, 2, NULL, GETDATE(), NULL, NULL, NULL)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            pre.setInt(2, cid);
            pre.setInt(3, oid);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOWarranty.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    public int UpdateWarranty(Warranty warranty) {
        int number = 0;
        String sql = "UPDATE Warranty SET Status = ? WHERE OrderID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, warranty.getAdmin().getAdministratorId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Warranty getWarrantyById(int id) {
        if(id <= 0) throw new IllegalArgumentException("Id cannot <= 0");
        Warranty w = null;
        DAOCustomer daoCustomer = new DAOCustomer();
        DAOProduct dAOProduct = new DAOProduct();
        String sql = "SELECT * FROM Warranty Where WarrantyId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt("productID");
                int customerID = rs.getInt("customerID");
                int status = rs.getInt("status");
                String note = rs.getString("note");
                String createdDate = rs.getString("createdDate");
                String appointmentDate = rs.getString("appointmentDate");
                String finishDay = rs.getString("finishDay");
                String description = rs.getString("description");
                int method = rs.getInt("method");
                Product p = dAOProduct.getProductById(productID);
                Customer c = daoCustomer.getCustomerInfor(customerID);
                w = new Warranty(id, status, createdDate, appointmentDate, finishDay, p, c, null, note, description, method);
            }
        } catch (SQLException ex) {
        }
        return w;
    }

    public Vector<Warranty> getWarrantyByStatus(String type) throws IOException {
        Vector<Warranty> vector = new Vector<>();
        DAOCustomer daoCustomers = new DAOCustomer();
        DAOAdministrator daoAdmin = new DAOAdministrator();
        DAOProduct daoProduct = new DAOProduct();
        int status = 1;
        String sql;
        if (type.equalsIgnoreCase("done")) {
            sql = "SELECT TOP 5 * FROM [Warranty] WHERE Status = ? OR Status = 0 ORDER BY createdDate DESC";
        } else {
            sql = "SELECT TOP 5 * FROM [Warranty] WHERE Status = ? ORDER BY createdDate DESC";
        }
        if (type.equalsIgnoreCase("process")) {
            status = 2;
        }
        if (type.equalsIgnoreCase("ship")) {
            status = 3;
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, status);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int warrantyID = rs.getInt("warrantyID");
                int productID = rs.getInt("productID");
                int customerID = rs.getInt("customerID");
                String note = rs.getString("note");
                int administratorId = rs.getInt("AdministratorId");
                String appointmentDate = rs.getString("appointmentDate");
                String finishDay = rs.getString("finishDay");
                String description = rs.getString("description");
                String createdDate = rs.getString("createdDate");
                int method = rs.getInt("method");
                Customer customer = daoCustomers.GetCustomerById(customerID);
                Administrator admin = daoAdmin.getAdminById(administratorId);
                Product product = daoProduct.getProductById(productID);
                vector.add(new Warranty(warrantyID, status, createdDate, appointmentDate, finishDay, product, customer, admin, note, description, method));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Vector<Warranty> SearchWarranties(String from, String to, String type) {
        DAOCustomer daoCustomers = new DAOCustomer();
        DAOAdministrator daoAdmin = new DAOAdministrator();
        DAOProduct daoProduct = new DAOProduct();
        Vector<Warranty> vector = new Vector<>();
        String sql = "SELECT * FROM Warranty WHERE createdDate Between ? AND ?";
        if (type.equalsIgnoreCase("shipping")) {
            sql = "SELECT * FROM [Warranty] WHERE Status = 3 AND createdDate Between ? AND ?";
        }
        if (type.equals("process")) {
            sql = "SELECT * FROM [Warranty] WHERE Status = 2 AND createdDate Between ? AND ?";
        }
        if (type.equalsIgnoreCase("done")) {
            sql = "SELECT * FROM [Warranty] WHERE (Status = 1 OR Status = 0) AND createdDate Between ? AND ?";
        }

        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            pre.setString(1, from);
            pre.setString(2, to);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int warrantyID = rs.getInt("warrantyID");
                int productID = rs.getInt("productID");
                int customerID = rs.getInt("customerID");
                String note = rs.getString("note");
                int administratorId = rs.getInt("AdministratorId");
                int status = rs.getInt("status");
                String appointmentDate = rs.getString("appointmentDate");
                String finishDay = rs.getString("finishDay");
                String description = rs.getString("description");
                String createdDate = rs.getString("createdDate");
                Customer customer = daoCustomers.GetCustomerById(customerID);
                Administrator admin = daoAdmin.getAdminById(administratorId);
                Product product = daoProduct.getProductById(productID);
                int method = rs.getInt("method");
                vector.add(new Warranty(warrantyID, status, createdDate, appointmentDate, finishDay, product, customer, admin, note, description, method));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Warranty getWarrantyByProductID(int ID) {
        Warranty vector = new Warranty();
        DAOProduct daoProducts = new DAOProduct();
        String sql = "SELECT * FROM Warranty WHERE ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int warrantyId = rs.getInt("warrantyID");
                int productID = rs.getInt("ProductID");
                String description = rs.getString("description");
                Product product = daoProducts.getInformationProduct(productID);
                WarrantyPolicy warrantyPolicy = getWarrantyPolicy(product.getCategory().getCategoryId());
                vector = new Warranty(warrantyId, productID, description, product, warrantyPolicy);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Vector<WarrantyPolicy> getAllWarrantyPolicy() {
        Vector<WarrantyPolicy> vector = new Vector<>();
        String sql = "SELECT * FROM WarrantyPolicy";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int warrantyPolicyID = rs.getInt("warrantyPolicyID");
                String policy1 = rs.getString("policy1");
                String policy2 = rs.getString("policy2");
                String policy3 = rs.getString("policy3");
                vector.add(new WarrantyPolicy(warrantyPolicyID, policy1, policy2, policy3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int UpdateWarrantyPolicy(WarrantyPolicy category) {
        int number = 0;
        String sql = "UPDATE WarrantyPolicy SET policy1 = ?, policy2 = ?, policy3 = ? WHERE warrantyPolicyID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, category.getPolicy1());
            pre.setString(2, category.getPolicy2());
            pre.setString(3, category.getPolicy3());
            pre.setInt(4, category.getWarrantyPolicyID());
            number = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;

    }

    public WarrantyPolicy getWarrantyPolicy(int ID) {
        WarrantyPolicy vector = new WarrantyPolicy();
        String sql = "SELECT * FROM WarrantyPolicy WHERE warrantyPolicyID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int warrantyPolicyID = rs.getInt("warrantyPolicyID");
                String policy1 = rs.getString("policy1");
                String policy2 = rs.getString("policy2");
                String policy3 = rs.getString("policy3");
                vector = new WarrantyPolicy(warrantyPolicyID, policy1, policy2, policy3);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

}
