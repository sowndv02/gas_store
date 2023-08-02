/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.IntPair;
import com.se1715.group4.gasstore.dto.Item;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.OrderDetail;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Shipments;
import com.se1715.group4.gasstore.dto.UserGoogle;
import com.se1715.group4.gasstore.dto.WarrantyWarning;
import com.se1715.group4.gasstore.util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOOrder {

    private Connection connection = DBUtil.makeConnection();

    public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
        Vector<Order> l = dao.SearchOrders("2023/06/10", "2023/06/26", "done");
        for (Order order : l) {
            System.out.println(order);
        }
    }

    
    
    public int TotalOrdersFail() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM [Order] WHERE Status = 0";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public int TotalOrdersProcess() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM [Order] WHERE Status = 3";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public int TotalOrder() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM [Order]";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public int TotalOrders() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM [Order] WHERE Status = 1";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public double rateNewOrders() {
        double rate = 0;
        int total = 0;
        String sql = "SELECT COUNT(*) AS Number FROM [Order] ";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                total = rs.getInt("Number");
            }
            if (total != 0) {
                rate = (double) newOrdersInMonth() / total;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rate;
    }

    public int newOrdersInMonth() {
        int number = 0;

        String sql = "SELECT COUNT(*) AS Number FROM [Order] WHERE MONTH(OrderDate) = MONTH(GETDATE()) AND Status = 1";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                number = rs.getInt("Number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    public Vector<IntPair> NumberOrdersByMonth(int year, String s) {
        Vector<IntPair> vector = new Vector<>();

        String sql = "SELECT MONTH(OrderDate) as month,COUNT(OrderID) AS Number FROM [Order] WHERE YEAR(OrderDate) = ? GROUP BY MONTH(OrderDate) ORDER BY MONTH(OrderDate) ASC";

        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, year);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int first = rs.getInt("Month");
                int second = rs.getInt("Number");
                vector.add(new IntPair(first, second));
            }
            int currentYear = LocalDate.now().getYear();
            int start = 1;
            int end = 12;
            if (year == currentYear) {
                end = LocalDate.now().getMonthValue();
            }
            HashSet<Integer> existingInts = new HashSet<>();
            for (IntPair obj : vector) {
                IntPair pair = (IntPair) obj;
                existingInts.add(pair.getFirst());
            }

            for (int i = end; i >= start; i--) {
                if (!existingInts.contains(i)) {
                    vector.add(new IntPair(i, 0)); // add the missing IntPair to the list
                }
            }
            Collections.sort(vector, new Comparator<IntPair>() {
                public int compare(IntPair p1, IntPair p2) {
                    return Integer.compare(p1.getFirst(), p2.getFirst());
                }
            });
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Vector<IntPair> TotalMoneyByMonth(int year) {
        Vector<IntPair> vector = new Vector<>();

        String sql = "SELECT MONTH(OrderDate) as month,SUM(TotalMoney) AS Number FROM [Order] WHERE YEAR(OrderDate) = ? AND Status = 1 GROUP BY MONTH(OrderDate) ORDER BY MONTH(OrderDate) ASC";

        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, year);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int first = rs.getInt("Month");
                int second = (int)rs.getDouble("Number");
                vector.add(new IntPair(first, second));
            }
            int currentYear = LocalDate.now().getYear();
            int start = 1;
            int end = 12;
            if (year == currentYear) {
                end = LocalDate.now().getMonthValue();
            }
            HashSet<Integer> existingInts = new HashSet<>();
            for (IntPair obj : vector) {
                IntPair pair = (IntPair) obj;
                existingInts.add(pair.getFirst());
            }

            for (int i = end; i >= start; i--) {
                if (!existingInts.contains(i)) {
                    vector.add(new IntPair(i, 0)); // add the missing IntPair to the list
                }
            }
            Collections.sort(vector, new Comparator<IntPair>() {
                public int compare(IntPair p1, IntPair p2) {
                    return Integer.compare(p1.getFirst(), p2.getFirst());
                }
            });
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Map<Integer, Integer> NumberOrdersByMonth(int year) {
        Map<Integer, Integer> map = new HashMap<>();

        String sql = "SELECT MONTH(OrderDate) as month,COUNT(OrderID) AS Number FROM [Order] WHERE YEAR(OrderDate) = ? GROUP BY MONTH(OrderDate) ORDER BY MONTH(OrderDate) ASC";

        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, year);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("Month");
                int number = rs.getInt("Number");
                map.put(month, number);
            }
            int currentYear = LocalDate.now().getYear();
            int start = 1, end = 12;
            if (year == currentYear) {
                end = LocalDate.now().getMonthValue();
            }
            for (int i = start; i <= end; i++) {
                map.putIfAbsent(i, 0); // add the missing month with 0 orders
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return map;
    }

    public Vector<Order> SearchOrders(String from, String to, String type) {
        if(type == null || type.isEmpty() || from == null || to == null || to.isEmpty() || from.isEmpty()) throw new IllegalArgumentException("Cannot empty or null");
        
        DAOCustomer daoCustomers = new DAOCustomer();
        DAOShipments daoShip = new DAOShipments();
        Vector<Order> vector = new Vector<>();
        String sql = "SELECT * FROM [Order] WHERE OrderDate Between ? AND ?";
        if (type.equalsIgnoreCase("shipping")) {
            sql = "SELECT * FROM [Order] WHERE Status = 3 AND OrderDate Between ? AND ?";
        }
        if (type.equals("process")) {
            sql = "SELECT * FROM [Order] WHERE Status = 2 AND OrderDate Between ? AND ?";
        }
        if (type.equalsIgnoreCase("done")) {
            sql = "SELECT * FROM [Order] WHERE (Status = 1 OR Status = 0) AND OrderDate Between ? AND ?";
        }

        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            pre.setString(1, from);
            pre.setString(2, to);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderId");
                int customerID = rs.getInt("customerID");
                String orderDate = rs.getString("orderDate");
                String requiredDate = rs.getString("requiredDate");
                int shipVia = rs.getInt("ShipVia");
                int status = rs.getInt("status");
                int trackingNumber = rs.getInt("trackingNumber");
                String address = rs.getString("ShipAddress");
                double totalMoney = rs.getDouble("TotalMoney");
                String note = rs.getString("notes");
                int payment = rs.getInt("payment");
                Shipments ship = daoShip.getShipById(shipVia);
                Customer customer = daoCustomers.GetCustomerById(customerID);
                vector.add(new Order(id, totalMoney, status, orderDate, requiredDate, address, customer, note, ship, trackingNumber, payment));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Order> getNextOrdersByStatus(int amount, String type){
        Vector<Order> vector = new Vector<>();
        if(type == null || type.isEmpty()) throw new IllegalArgumentException("Cannot null or empty");
        if(amount < 0) throw new IllegalArgumentException("Amount cannot < 0");
        DAOCustomer daoCustomers = new DAOCustomer();
        int status = 1;
        String sql;
        if (type.equalsIgnoreCase("done")) {
            sql = "SELECT * FROM [Order] WHERE Status = ? OR Status = 0 ORDER BY OrderDate DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        } else {
            sql = "SELECT * FROM [Order] WHERE Status = ? ORDER BY OrderDate DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        }
        if (type.equalsIgnoreCase("process")) {
            status = 2;
        }
        if (type.equalsIgnoreCase("ship")) {
            status = 3;
        }
        DAOShipments daoShip = new DAOShipments();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, amount);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderId");
                int customerID = rs.getInt("customerID");
                String orderDate = rs.getString("orderDate");
                String requiredDate = rs.getString("requiredDate");
                int shipVia = rs.getInt("ShipVia");
                int trackingNumber = rs.getInt("trackingNumber");
                String address = rs.getString("ShipAddress");
                double totalMoney = rs.getDouble("TotalMoney");
                String note = rs.getString("notes");
                int payment = rs.getInt("payment");
                Shipments ship = daoShip.getShipById(shipVia);
                Customer customer = daoCustomers.GetCustomerById(customerID);
                vector.add(new Order(id, totalMoney, status, orderDate, requiredDate, address, customer, note, ship, trackingNumber, payment));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<OrderDetail> getAllOrderDetailsByOrderID(int oID, String date) {
        Vector<OrderDetail> vector = new Vector<>();
        DAOProduct daoProducts = new DAOProduct();
        String sql = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, oID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int productID = rs.getInt("ProductID");
                double unitPrice = rs.getDouble("UnitPrice");
                int quantity = rs.getInt("Quantity");
                double discount = rs.getDouble("Discount");
                int warranty = rs.getInt("warranty");
                Product product = daoProducts.getInformationProduct(productID);
                vector.add(new OrderDetail(orderID, quantity, unitPrice, discount, product, warranty, date));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int getNewOrderId(int customerId) {
        int orderId = 0;
        String sql = "select top 1 OrderID from [Order] where customerId = ? order by orderid desc";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, customerId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderId;
    }

    public int addOrder(Customer c, Cart cart, int shipperID, String required, String notes, int payment) {
        int number = 0;
        try {
            //add order
            String sql = "INSERT INTO [Order] (CustomerID, OrderDate, requiredDate, shippedDate, ShipVia, shipAddress, Payment, Process, Status, totalMoney, notes) "
                    + " VALUES (?, GETDATE(), ?, NULL,  ?, ?, ?, 2, 2, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getCustomerID());
            st.setString(2, required);
            st.setInt(3, shipperID);
            st.setString(4, c.getAddress());
            st.setInt(5, payment);
            st.setDouble(6, cart.getTotalMoney());
            st.setString(7, notes);
            st.executeUpdate();

            //add bang OrderDetail
            int oid = getNewOrderId(c.getCustomerID());
            System.out.println("oid:" + oid);
            for (Item i : cart.getItems()) {
                String sql2 = "INSERT INTO dbo.OrderDetails (OrderID, ProductID, UnitPrice, Quantity, Discount, Warranty) VALUES (?, ?,  ?, ?, ?, ?)";
                PreparedStatement st2 = connection.prepareStatement(sql2);
                st2.setInt(1, oid);
                st2.setInt(2, i.getProduct().getProductId());
                st2.setDouble(3, i.getUnitPrice());
                st2.setInt(4, i.getQuantity());
                st2.setDouble(5, i.getProduct().getDiscount().getDiscount());
                st2.setInt(6, i.getProduct().getWarranty());
                st2.executeUpdate();
            }
            //cap nhat lai so luong san pham
            String sql3 = "update product set stockQuantity = stockQuantity-?, unitOnOrders = unitOnOrders + ? where ProductID=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getQuantity());
                st3.setInt(3, i.getProduct().getProductId());
                st3.executeUpdate();
            }
        } catch (SQLException e) {

        }
        return number;
    }

    public Order getOrdersByOrderID(int oID) {
        DAOShipments daoShippers = new DAOShipments();
        DAOCustomer daoCustomers = new DAOCustomer();
        Order order = null;
        String sql = "SELECT * FROM [Order] WHERE OrderID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, oID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                int status = rs.getInt("Status");
                double totalMoney = rs.getDouble("TotalMoney");
                int trackingNumber = rs.getInt("trackingNumber");
                String notes = rs.getString("notes");
                Vector<OrderDetail> listOrderDetail = getAllOrderDetailsByOrderID(orderID, shippedDate);
                Shipments shipper = daoShippers.getShipById(shipVia);
                Customer customer = daoCustomers.GetCustomerById(customerID);
                order = new Order(orderID, trackingNumber, totalMoney, orderDate, shippedDate, requiredDate, shipAddress, status, listOrderDetail, customer, notes, shipper);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    public Vector<Order> getNewOrders(String type) throws IOException {
        Vector<Order> vector = new Vector<>();
        DAOCustomer daoCustomers = new DAOCustomer();
        DAOShipments daoShip = new DAOShipments();
        int status = 1;
        String sql;
        if (type.equalsIgnoreCase("done")) {
            sql = "SELECT TOP 5 * FROM [Order] WHERE Status = ? OR Status = 0 ORDER BY OrderDate DESC";
        } else {
            sql = "SELECT TOP 5 * FROM [Order] WHERE Status = ? ORDER BY OrderDate DESC";
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
                int id = rs.getInt("orderId");
                int customerID = rs.getInt("customerID");
                String orderDate = rs.getString("orderDate");
                String requiredDate = rs.getString("requiredDate");
                int shipVia = rs.getInt("ShipVia");
                int trackingNumber = rs.getInt("trackingNumber");
                String address = rs.getString("ShipAddress");
                double totalMoney = rs.getDouble("TotalMoney");
                String note = rs.getString("notes");
                Shipments ship = daoShip.getShipById(shipVia);
                int payment = rs.getInt("payment");
                Customer customer = daoCustomers.GetCustomerById(customerID);
                vector.add(new Order(id, totalMoney, status, orderDate, requiredDate, address, customer, note, ship, trackingNumber, payment));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Vector<Order> getOrdersByCustomerID(int oID){
        DAOShipments daoShippers = new DAOShipments();
        DAOCustomer daoCustomers = new DAOCustomer();
        Vector<Order> order = new Vector<>();
        String sql = "SELECT * FROM [Order] where CustomerID = ? ORDER BY OrderDate DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, oID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                int status = rs.getInt("Status");
                int payment = rs.getInt("payment");
                int process = rs.getInt("process");
                double totalMoney = rs.getDouble("TotalMoney");
                int trackingNumber = rs.getInt("trackingNumber");
                String notes = rs.getString("notes");
                Vector<OrderDetail> listOrderDetail = getAllOrderDetailsByOrderID(orderID, shippedDate);
                Shipments shipper = daoShippers.getShipById(shipVia);
                Customer customer = daoCustomers.GetCustomerById(customerID);
                order.add(new Order(orderID, trackingNumber, totalMoney, orderDate, shippedDate, requiredDate, shipAddress, status, payment,process, listOrderDetail, customer, notes, shipper));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    public int updateStatusPayment(int orderId, int status) {
        int number = 0;

        String sql = "Update [Order] SET Payment = ? WHERE orderId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, orderId);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    public int UpdateOrders(int orderID, int status) {
        Order order = getOrdersByOrderID(orderID);
        Vector<OrderDetail> listOrderDetail = (Vector<OrderDetail>) order.getOrderDetails();
        int number = 0;
        String sql = "UPDATE [Order] SET Status = ?, Process = 3 WHERE OrderID = ?";
        String sql2 = "UPDATE Product Set stockQuantity = stockQuantity + ?, unitOnOrders = unitOnOrders - ? WHERE ProductID = ?";

        try {
            if (status == 1) {
                sql = "UPDATE [Order] SET Status = ?, shippedDate = GETDATE() WHERE OrderID = ?";
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, status);
                pre.setInt(2, orderID);
                for (OrderDetail orderDetails : listOrderDetail) {
                    addWarrantyWarning(orderID, orderDetails.getProductId(), orderDetails.getWarranty());
                }
                number = pre.executeUpdate();

            }
            if (status > 1) {
                sql = "UPDATE [Order] SET Status = ?, shippedDate = GETDATE(), process = ? WHERE OrderID = ?";
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, status);
                pre.setInt(2, status);
                pre.setInt(3, orderID);
                number = pre.executeUpdate();

            }
            if (status == 0) {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, status);
                pre.setInt(2, orderID);
                pre.executeUpdate();
                PreparedStatement preProduct = connection.prepareStatement(sql2);
                for (OrderDetail orderDetails : listOrderDetail) {
                    preProduct.setInt(1, orderDetails.getQuantity());
                    preProduct.setInt(2, orderDetails.getQuantity());
                    preProduct.setInt(3, orderDetails.getProduct().getProductId());
                    number += preProduct.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int addWarrantyWarning(int o, int p, int od) {
        int number = 0;
        try {
            //add order
            String sql = "INSERT INTO [dbo].[MailWarranty]([isSend],[dueDate],[orderID],[productID])VALUES(0,GETDATE()+ ?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, od * 15);
            st.setInt(2, o);
            st.setInt(3, p);
            st.executeUpdate();
        } catch (SQLException e) {

        }
        return number;
    }

    public int updateWarrantyWarning(int pid, int oid) {
        int number = 0;
        try {
            //add order
            String sql = "UPDATE [dbo].[MailWarranty] SET [isSend]= 1 where orderID = ? and productID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setInt(2, pid);
            st.executeUpdate();
        } catch (SQLException e) {

        }
        return number;
    }

    public Vector<WarrantyWarning> getAllWarrantyWarning() {
        Vector<WarrantyWarning> vector = new Vector<>();
        DAOOrder daoo = new DAOOrder();
        DAOProduct daop = new DAOProduct();
        String sql = "SELECT * FROM [MailWarranty] where isSend = 0 and dueDate <= GETDATE()";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int warrantyPolicyID = rs.getInt("isSend");
                String policy1 = rs.getString("dueDate");
                int policy2 = rs.getInt("orderID");
                Order o = daoo.getOrdersByOrderID(policy2);
                int policy3 = rs.getInt("productID");
                Product p = daop.getInformationProduct(policy3);
                vector.add(new WarrantyWarning(warrantyPolicyID, policy1, policy2, policy3, p, o));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

}
