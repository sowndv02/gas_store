/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAODiscount;
import com.se1715.group4.gasstore.dto.Discount;
import com.se1715.group4.gasstore.dto.Product;
import java.util.Vector;
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
public class DAODiscountTest {
    
    public DAODiscountTest() {
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
     * Test of GetProductIds method, of class DAODiscount.
     */
    
    
    @Test
    public void testGetProductIdsCorrect() {
        System.out.println("GetProductIds");
        DAODiscount instance = new DAODiscount();
        int[] expResult = {2,13,14,15};
        int supId = 1;
        int[] result = instance.GetProductIds(supId);
        assertArrayEquals(expResult, result);
    }
    
    
    
    @Test
    public void testGetProductIdsOutRange1() {
        System.out.println("GetProductIds");
        DAODiscount instance = new DAODiscount();
        int[] expResult = {};
        int supId = 10000;
        int[] result = instance.GetProductIds(supId);
        assertArrayEquals(expResult, result);
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetProductIdsOutRange2() {
        System.out.println("GetProductIds");
        DAODiscount instance = new DAODiscount();
        int[] expResult = null;
        int supId = 0;
        int[] result = instance.GetProductIds(supId);
        assertArrayEquals(expResult, result);   
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetProductIdsIsNull() {
        System.out.println("GetProductIds");
        DAODiscount instance = new DAODiscount();
        int[] expResult = null;
        int supId = 0;
        int[] result = instance.GetProductIds(supId);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of UpdateDiscountIdWithSid method, of class DAODiscount.
     */
    
    
    @Test
    public void testUpdateDiscountIdWithSidCorrect() {
        int discountid = 1;
        String supplierid = "1";
        DAODiscount instance = new DAODiscount();
        int expResult = 4;
        int result = instance.UpdateDiscountIdWithSid(discountid, supplierid);
        assertEquals(expResult, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDiscountIdWithSidEmpty() {
        int discountid = 1;
        String supplierid = "";
        DAODiscount instance = new DAODiscount();
        instance.UpdateDiscountIdWithSid(discountid, supplierid);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDiscountIdWithSidIsNull() {
        int discountid = 0;
        String supplierid = null;
        DAODiscount instance = new DAODiscount();
        instance.UpdateDiscountIdWithSid(discountid, supplierid);
    }
    
    @Test
    public void testUpdateDiscountIdWithSidOutRange1() {
        int discountid = 1000;
        String supplierid = "0";
        DAODiscount instance = new DAODiscount();
        int expResult = 0;
        int result = instance.UpdateDiscountIdWithSid(discountid, supplierid);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testUpdateDiscountIdWithSidOutRange2() {
        System.out.println("UpdateDiscountIdWithSid");
        int discountid = 1000;
        String supplierid = "0";
        DAODiscount instance = new DAODiscount();
        int expResult = 0;
        int result = instance.UpdateDiscountIdWithSid(discountid, supplierid);
        assertEquals(expResult, result);
    }

    
    
}
