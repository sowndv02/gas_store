/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.OrderDetail;
import com.se1715.group4.gasstore.dto.Supplier;
import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DAOOrderDetail {
    private Connection connection = DBUtil.makeConnection();

    public List<Supplier> filterSuppliers(Vector<OrderDetail> orderDetailsList) {
//        List<Suppliers> s = new ArrayList<>();
//        for (OrderDetails o : orderDetailsList) {
//            s.add(o.getProduct().getSupplier());
//        }
//        List<Suppliers> filteredSuppliers = s.stream()
//                .collect(Collectors.groupingBy(Suppliers::getSupplierID))
//                .values()
//                .stream()
//                .map(supplierList -> supplierList.get(0))
//                .collect(Collectors.toList());
//        return filteredSuppliers;
        List<Supplier> s = new ArrayList<>();
        for (OrderDetail o : orderDetailsList) {
            s.add(o.getProduct().getSupplier());
        }

        Map<Integer, Supplier> supplierMap = new HashMap<>();
        for (Supplier supplier : s) {
            supplierMap.put(supplier.getSupplierId(), supplier);
        }

        List<Supplier> filteredSuppliers = new ArrayList<>(supplierMap.values());
        return filteredSuppliers;

    }
    
    public int TotalProductsSaleBySupplier(int supplierID) {
        int number = 0;
        String sql = "SELECT COUNT(OD.ProductID) FROM dbo.OrderDetails AS OD JOIN dbo.Product AS P ON OD.ProductID = P.ProductID WHERE P.SupplierID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
}
