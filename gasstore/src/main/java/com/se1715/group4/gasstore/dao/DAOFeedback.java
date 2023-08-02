/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.*;
import com.se1715.group4.gasstore.util.DBUtil;
import com.se1715.group4.gasstore.util.SendMail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOFeedback {

    private final Connection connection = DBUtil.makeConnection();

    public int AddNewFeedback(Feedback f) {
        int number = 0;
        String sql ="INSERT INTO dbo.Feedback(name, subject, email, content, reply, repDate, sendDate, status, repBy, roleId) VALUES (?, ?, ?, ?, NULL, NULL, GETDATE(), 0, NULL, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, f.getName());
            pre.setString(2, f.getSubject());
            pre.setString(3, f.getEmail());
            pre.setString(4, f.getContent());
            pre.setInt(5, f.getRole());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int UpdateFeedback(Feedback feedback, String contentRep) {
        SendMail send = new SendMail();
        int number = 0;
        String sql = "UPDATE Feedback SET reply = ?, Status = 1, repDate = GETDATE() WHERE feedbackID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, feedback.getReply());
            pre.setInt(2, feedback.getFeedbackId());

            number = pre.executeUpdate();
            if (number > 0) {
//                send.sendFeedBack(feedback.getEmail(), contentRep);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public Vector<Feedback> getAllFeedbackByEmail(Feedback f) {
        if(f == null || f.getEmail() == null || f.getEmail().isEmpty()) throw new IllegalArgumentException("Cannot null or empty");
        Vector<Feedback> vector = new Vector<>();
        DAOSupplier daoSuppliers = new DAOSupplier();
        DAOShipments daoShippers = new DAOShipments();
        DAOCustomer daoCustomer = new DAOCustomer();
        String sql = "SELECT * FROM Feedback WHERE Email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, f.getEmail());
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("feedbackId");
                int role = rs.getInt("roleId");
                String contentSend = rs.getString("Content");
                String contentRep = rs.getString("reply");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                String dateSend = rs.getString("sendDate");
                String dateRep = rs.getString("repDate");
                if (role == 2) {
                    Supplier supplier = daoSuppliers.GetSupplierByEmail(email);
                    vector.add(new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, supplier));
                }
                if (role == 3) {
                    Shipments shipper = daoShippers.getShipperByEmail(email);
                    vector.add(new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, shipper));
                }
                if (role == 4) {
                    Customer customer = daoCustomer.getCustomerByEmail(email);
                    vector.add(new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, customer));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Feedback getFeedbackByID(int idSearch) {
        Feedback f = null;
        DAOCustomer daoCustomer = new DAOCustomer();
        DAOSupplier daoSuppliers = new DAOSupplier();
        DAOShipments daoShippers = new DAOShipments();
        String sql = "SELECT * FROM Feedback WHERE feedbackID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, idSearch);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("feedbackId");
                int role = rs.getInt("roleId");
                String contentSend = rs.getString("Content");
                String contentRep = rs.getString("reply");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                String name = rs.getString("name");
                String subject = rs.getString("subject");
                String dateSend = rs.getString("sendDate");
                String dateRep = rs.getString("repDate");
                if (role == 2) {
                    Supplier supplier = daoSuppliers.GetSupplierByEmail(email);
                    if (supplier != null) {
                        f = new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, supplier);
                    } else {
                        f = new Feedback(id, name, subject, email, contentSend, contentRep, dateRep, dateSend, status, role);
                    }
                }
                if (role == 3) {
                    Shipments shipper = daoShippers.getShipperByEmail(email);
                    if (shipper != null) {
                        f = new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, shipper);
                    } else {
                        f = new Feedback(id, name, subject, email, contentSend, contentRep, dateRep, dateSend, status, role);
                    }
                }
                if (role == 4) {
                    Customer customer = daoCustomer.getCustomerByEmail(email);
                    if (customer != null) {
                        f = new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, customer);
                    } else {
                        f = new Feedback(id, name, subject, email, contentSend, contentRep, dateRep, dateSend, status, role);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return f;
    }

    public Vector<Feedback> getLastFeedback() {
        Vector<Feedback> vector = new Vector<>();
        DAOCustomer daoCustomer = new DAOCustomer();
        DAOSupplier daoSuppliers = new DAOSupplier();
        DAOShipments daoShippers = new DAOShipments();
        String sql = "Select TOP 1 * FROM Feedback WHERE email = ? ORDER BY sendDate DESC";

        Vector<String> listAllEmail = getAllEmailFeedback();

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            for (String string : listAllEmail) {
                pre.setString(1, string);
                ResultSet rs = pre.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("feedbackId");
                    int role = rs.getInt("roleId");
                    String name = rs.getString("name");
                    String subject = rs.getString("subject");
                    String contentSend = rs.getString("Content");
                    String contentRep = rs.getString("reply");
                    boolean status = rs.getBoolean("Status");
                    String email = rs.getString("Email");
                    String dateSend = rs.getString("sendDate");
                    String dateRep = rs.getString("repDate");
                    System.out.println(role);
                    if (role == 2) {
                        Supplier supplier = daoSuppliers.GetSupplierByEmail(email);
                        if (supplier != null) {
                            vector.add(new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, supplier));
                        } else {
                            vector.add(new Feedback(id, name, subject, email, contentSend, contentRep, dateRep, dateSend, status, role));
                        }
                    }
                    if (role == 3) {
                        Shipments shipper = daoShippers.getShipperByEmail(email);
                        if (shipper != null) {
                            vector.add(new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, shipper));
                        } else {
                            vector.add(new Feedback(id, name, subject, email, contentSend, contentRep, dateRep, dateSend, status, role));
                        }
                    }
                    if (role == 4) {

                        Customer customer = daoCustomer.getCustomerByEmail(email);
                        if (customer != null) {
                            vector.add(new Feedback(id, email, contentSend, contentRep, dateRep, dateSend, status, customer));
                        } else {
                            vector.add(new Feedback(id, name, subject, email, contentSend, contentRep, dateRep, dateSend, status, role));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<String> getAllEmailFeedback() {
        Vector<String> vector = new Vector<>();
        String sql = "SELECT Email FROM Feedback GROUP BY Email";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                vector.add(rs.getString("Email"));
            }
        } catch (Exception e) {
        }
        return vector;
    }
}
