/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAOOrder;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.IntPair;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.OrderDetail;
import com.se1715.group4.gasstore.dto.WarrantyWarning;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADMIN
 */
public class DAOOrderTest {

    public DAOOrderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of SearchOrders method, of class DAOOrder.
     */
    /*
        
    
        
     */
    
    
    @Test
    public void testSearchOrdersWithDateIsCorrectAndStatusDone() {
        System.out.println("SearchOrders");
        String from = "2023/06/10";
        String to = "2023/06/26";
        String type = "done";
        DAOOrder instance = new DAOOrder();
        int expResult = 0;
        Vector<Order> result = instance.SearchOrders(from, to, type);
        assertSame(expResult, result.size());
    }
    
    
    @Test
    public void testSearchOrdersWithDateIsErrorStatusIsProcess() {
        System.out.println("SearchOrders");
        String from = "2023/07/03";
        String to = "2023/07/02";
        String type = "process";
        DAOOrder instance = new DAOOrder();
        int expResult = 0;
        Vector<Order> result = instance.SearchOrders(from, to, type);
        assertSame(expResult, result.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSearchOrdersWithDateIsNull() {
        System.out.println("SearchOrders");
        String from = null;
        String to = null;
        String type = "";
        DAOOrder instance = new DAOOrder();
        instance.SearchOrders(from, to, type);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testSearchOrdersWithDateFromIsEmpty() {
        System.out.println("SearchOrders");
        String from = "";
        String to = "2023/06/26";
        String type = "";
        DAOOrder instance = new DAOOrder();
        instance.SearchOrders(from, to, type);
    }
    
    
    
    @Test
    public void testSearchOrdersWithTypeDoneAndDateDone() {
        System.out.println("SearchOrders");
        String from = "2023/07/03";
        String to = "2023/07/01";
        String type = "done";
        DAOOrder instance = new DAOOrder();
        int expResult = 0;
        Vector<Order> result = instance.SearchOrders(from, to, type);
        assertSame(expResult, result.size());
    }
    
    
    
    @Test
    public void testSearchOrdersWithDateError() {
        System.out.println("SearchOrders");
        String from = "2023/07/03";
        String to = "2023/07/01";
        String type = "done";
        DAOOrder instance = new DAOOrder();
        int expResult = 0;
        Vector<Order> result = instance.SearchOrders(from, to, type);
        assertSame(expResult, result.size());
    }

    /**
     * Test of getNextOrdersByStatus method, of class DAOOrder.
     */
    
    
    
    @Test
    public void testGetNextOrdersByStatusDoneAmountIsOne(){
        System.out.println("getNextOrdersByStatus");
        int amount = 1;
        String type = "done";
        DAOOrder instance = new DAOOrder();
        int expResult = 0;
        Vector<Order> result = instance.getNextOrdersByStatus(amount, type);
        assertNotEquals(expResult, result.size());
    }
    
    
    @Test
    public void testGetNextOrdersByStatusProcessAmountIsZero(){
        System.out.println("getNextOrdersByStatus");
        int amount = 0;
        String type = "process";
        DAOOrder instance = new DAOOrder();
        int expResult = 0;
        Vector<Order> result = instance.getNextOrdersByStatus(amount, type);
        assertNotEquals(expResult, result.size());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetNextOrdersByStatusAmountNegative(){
        System.out.println("getNextOrdersByStatus");
        int amount = -1;
        String type = "ship";
        DAOOrder instance = new DAOOrder();
        instance.getNextOrdersByStatus(amount, type);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetNextOrdersByStatusTypeIsEmpty(){
        System.out.println("getNextOrdersByStatus");
        int amount = 1;
        String type = "";
        DAOOrder instance = new DAOOrder();
        instance.getNextOrdersByStatus(amount, type);

    }

}
