/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Shipments;
import com.se1715.group4.gasstore.util.DBUtil;
import com.se1715.group4.gasstore.util.SendMail;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Shipments;
import com.se1715.group4.gasstore.dto.TypeBlog;
import com.se1715.group4.gasstore.dto.UserGoogle;
import com.se1715.group4.gasstore.util.AES;
import com.se1715.group4.gasstore.util.DBUtil;
import com.se1715.group4.gasstore.util.SendMail;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOCustomer {

    private final Connection connection = DBUtil.makeConnection();

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
    }
    
    public double rateAccountActive() {
        double rate = 0;
        int total = TotalAccounts();
        int active = AccountActive();
        if (total != 0) {
            rate = (double) total / active;
        }
        return rate;
    }
    
    public int AccountActive() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.Customer WHERE Status = 1 ";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return number;
    }
    
    public int TotalAccounts() {
        String sql = "SELECT COUNT(*) AS Number FROM dbo.Customer";
        int total = 0;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                total = rs.getInt("Number");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    
    public double rateNewAccount() {
        double rate = 0;

        int total = TotalAccounts();
        if (total != 0) {
            rate = (double) newUserInMonth() / total;
        }
        return rate;
    }

    public int newUserInMonth() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.Customer WHERE MONTH(created) = MONTH(GETDATE()) ";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return number;
    }
    
    public int TotalOrdersByCustomers(int id) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.[Order] INNER JOIN dbo.Customer ON Customer.CustomerID = [Order].CustomerID\n"
                + "WHERE Customer.CustomerID = ? AND ([Order].Status = 1 OR [Order].Status = 0)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int AddLoginGoogle(UserGoogle user) {
        int number = 0;
        String sql = "INSERT INTO dbo.Customer(googleId, created, status, firstName, lastName, email, Image) "
                + "VALUES(?, GETDATE(), 1, ?, ?, ?, (SELECT * FROM OPENROWSET(BULK N'C:/images/customers/default.png', SINGLE_BLOB) as T1))";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getId());
            pre.setString(2, user.getGiven_name());
            pre.setString(3, user.getFamily_name());
            pre.setString(4, user.getEmail());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
        }

        return number;
    }

    public Customer checkLoginGoogle(String googleId) {
        if(googleId == null || googleId.isEmpty()) throw new IllegalArgumentException("Cannot null or empty");
        Customer customer = null;
        String sql = "SELECT CustomerId From customer Where googleid = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, googleId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("customerId");
                customer = new Customer(customerId, googleId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public double rateOrders(int id) {
        double rate = 0;

        String sql = "SELECT [Order].Status, COUNT(*) AS Number FROM dbo.[Order] INNER JOIN dbo.Customer ON Customer.CustomerID = [Order].CustomerID\n"
                + "WHERE Customer.CustomerID = ?\n"
                + "GROUP BY [Order].Status";

        HashMap<Integer, Integer> numbers = new HashMap<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int status = rs.getInt("Status");
                Integer number = rs.getInt("Number");
                numbers.put(status, number);
            }
            int total = TotalOrdersByCustomers(id);
            System.out.println(total);
            if (total != 0) {
                for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
                    Integer key = entry.getKey();
                    Integer val = entry.getValue();
                    System.out.println(key);
                    System.out.println(val);
                    if (key == 1) {
                        rate = (double) val / total;
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rate;
    }

    public Vector<Order> getOrdersByCustomerID(int cid) {
        DAOShipments daoShippers = new DAOShipments();
        Vector<Order> vector = new Vector<>();
        String sql = "SELECT * FROM [Order] WHERE CustomerID = ? ORDER BY OrderDate DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                int status = rs.getInt("Status");
                Shipments shipper = daoShippers.getShipById(shipVia);
                double totalMoney = rs.getDouble("TotalMoney");
                vector.add(new Order(orderID, totalMoney, orderDate, shippedDate, requiredDate, shipAddress, status, shipper));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Customer> getAllCustomersByAdmin() {
        Vector<Customer> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Customer";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerId");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                boolean status = rs.getBoolean("Status");
                vector.add(new Customer(customerID, status, gender, fName, lName, address, phone, email));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Vector<Customer> getListByPage(Vector<Customer> vector,
            int start, int end) {
        Vector<Customer> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(vector.get(i));
        }
        return arr;
    }

    public Vector<Customer> getTop5Customers() {
        Vector<Customer> vector = new Vector<>();
        String sql = "SELECT TOP 5 customerId, firstName, lastName, gender, phone, email, address, totalMoney FROM dbo.Customer ORDER BY TotalMoney DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                double totalMoney = rs.getDouble("TotalMoney");
                vector.add(new Customer(customerID, gender, gender, firstName, lastName, address, phone, email, totalMoney));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

    public Vector<Customer> getNewCustomers() {
        Vector<Customer> vector = new Vector<>();
        String sql = "SELECT TOP 5 customerId, firstName, lastName, gender, phone, email, address FROM dbo.Customer ORDER BY created DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                vector.add(new Customer(customerID, gender, firstName, lastName, address, phone, email));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

    /**
     * The function is used to update the customer's information in the database
     *
     * @param customer is a Customer object
     * @param file the image file
     * @return The number of rows affected by the update.
     */
    public int UpdateCustomer(Customer customer, InputStream file) {
        int number = 0;
        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, Phone = ?, Email = ?, Address = ?, Gender = ? WHERE customerID = ?";
        try {
            if (file != null) {
                sql = "UPDATE Customer SET firstName = ?, lastName = ?, Phone = ?, Email = ?, Address = ?,Gender = ?, Image = ? WHERE customerID = ?";
            }
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, customer.getFirstName());
            pre.setString(2, customer.getLastName());
            pre.setString(3, customer.getPhone());
            pre.setString(4, customer.getEmail());
            pre.setString(5, customer.getAddress());
            pre.setBoolean(6, customer.isGender());
            if (file != null) {
                pre.setBlob(7, file);
                pre.setInt(8, customer.getCustomerID());
                number = pre.executeUpdate();
            } else {
                pre.setInt(7, customer.getCustomerID());
                number = pre.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public Customer getCustomerByEmail(String email) {
        Customer cus = null;
        String sql = "SELECT * FROM Customer WHERE Email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("customerId");
                String username = rs.getString("userName");
                String password = rs.getString("password");
                String created = rs.getString("created");
                String lastLogin = rs.getString("lastLogin");
                boolean status = rs.getBoolean("status");
                boolean gender = rs.getBoolean("gender");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Blob blob = rs.getBlob("Image");

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
                    cus = new Customer(customerId, username, password, created, lastLogin, status, gender, firstName, lastName, address, phone, email, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    public int ChangePassword(Customer c) {
        int number = 0;
        String sql = "UPDATE dbo.Customer SET password = ? WHERE customerId = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, AES.encrypt(c.getPassword()));
            pre.setInt(2, c.getCustomerID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public String getGoogleId(int id) {
        String gId = null;
        String sql = "Select GoogleId FROM Customer Where customerId = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                gId = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gId;
    }
    
    public Customer getCustomerInfor(int cId){
        Customer c = null;
        String sql = "SELECT * FROM Customer WHERE customerId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean status = rs.getBoolean("status");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                
                c = new Customer(cId, firstName, lastName, null, status, email, phone);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public Customer GetCustomerById(int cId) {
        Customer c = null;
        String sql = "SELECT * FROM Customer WHERE customerId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean status = rs.getBoolean("status");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Blob blob = rs.getBlob("Image");

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
                    c = new Customer(cId, firstName, lastName, base64Image, status, email, phone);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public int LockCustomers(Customer c) {
        SendMail send = new SendMail();
        int number = 0;
        String sql = "UPDATE Customer SET Status = ? WHERE CustomerID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, c.isStatus());
            pre.setInt(2, c.getCustomerID());
            number = pre.executeUpdate();
//            if (number > 0 && !c.isStatus()) {
//                send.sendLockAccount(c.getEmail());
//            }
//            if (number > 0 && c.isStatus()) {
//                send.sendUnLockAccount(c.getEmail());
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int InsertNewCustomer(Customer customer) {
        int number = 0;
        String sql = "INSERT INTO dbo.Customer(userName, password, created, lastLogin, status, gender, firstName, lastName, address, phone, email, Image) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT * FROM OPENROWSET(BULK N'C:/images/customers/default.png', SINGLE_BLOB) as T1))";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, customer.getUserName());
            pre.setString(2, AES.encrypt(customer.getPassword()));
            pre.setString(3, customer.getCreated());
            pre.setString(4, customer.getLastLogin());
            pre.setBoolean(5, customer.isStatus());
            pre.setBoolean(6, customer.isGender());
            pre.setString(7, customer.getFirstName());
            pre.setString(8, customer.getLastName());
            pre.setString(9, customer.getAddress());
            pre.setString(10, customer.getPhone());
            pre.setString(11, customer.getEmail());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public int checkCustomer(String data, String attribute) {
        String sql = "SELECT COUNT(*) FROM Customer WHERE " + attribute + " = ?";
        Customer c = null;
        int number = 0;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, data);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Customer getCustomerByUserName(String username) {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE username = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("customerId");
                String created = rs.getString("created");
                String lastLogin = rs.getString("lastLogin");
                String password = rs.getString("password");
                boolean status = rs.getBoolean("status");
                boolean gender = rs.getBoolean("gender");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                Blob blob = rs.getBlob("Image");

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
                    customer = new Customer(customerId, username, password, created, lastLogin, status, gender, firstName, lastName, address, phone, email, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    public Customer LoginCustomer(String username, String password) throws IOException {
        try {
            Customer c = getCustomerByUserName(username);
            if (password.equals(AES.decrypt(c.getPassword()))) {
                return c;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getProfileCustomerById(int cid) {
        Customer cus = null;
        String sql = "SELECT * FROM Customer WHERE customerId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String username = rs.getString("userName");
                String password = rs.getString("password");
                String created = rs.getString("created");
                String lastLogin = rs.getString("lastLogin");
                boolean status = rs.getBoolean("status");
                boolean gender = rs.getBoolean("gender");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                Blob blob = rs.getBlob("Image");

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
                    cus = new Customer(cid, username, password, created, lastLogin, status, gender, firstName, lastName, address, phone, email, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    public Map<Integer, String> getMapCustomerUsername() {
        Map<Integer, String> mapCustomerUsername = new HashMap<>();
        String sql = "select * from Customer";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String userName = rs.getString("username");
                mapCustomerUsername.put(customerId, userName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return mapCustomerUsername;
    }
}
