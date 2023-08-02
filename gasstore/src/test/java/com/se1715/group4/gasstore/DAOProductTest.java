/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Product;
import java.util.ArrayList;
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
public class DAOProductTest {
    
    public DAOProductTest() {
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
     * Test of CheckProductInList method, of class DAOProduct.
     */
    
    
    @Test
    public void testCheckProductInListCorrect() {
        System.out.println("CheckProductInList");
        int index = 1;
        ArrayList<Product> products = new ArrayList<>();
        Product p2 = new Product(2, "Product 2", 112.34);
        Product p1 = new Product(1, "Product 1", 10.21);
        Product p3 = new Product(3,"Product 3", 1112.563);
        
        products.add(p1);
        products.add(p2);
        products.add(p3);
                
        int id = 2;
        DAOProduct instance = new DAOProduct();
        Product expResult = p2;
        Product result = instance.CheckProductInList(index, products, id);
        assertSame(expResult, result);
    }
    
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckProductInListIndexIsNegative() {
        System.out.println("CheckProductInList");
        int index = -1;
        ArrayList<Product> products = new ArrayList<>();
        Product p2 = new Product(2, "Product 2", 112.34);
        Product p1 = new Product(1, "Product 1", 10.21);
        Product p3 = new Product(3,"Product 3", 1112.563);
        
        products.add(p1);
        products.add(p2);
        products.add(p3);
                
        int id = 2;
        DAOProduct instance = new DAOProduct();
        instance.CheckProductInList(index, products, id);
    }
    
    
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckProductInListIndexOutRange() {
        System.out.println("CheckProductInList");
        int index = 13;
        ArrayList<Product> products = new ArrayList<>();
        Product p2 = new Product(2, "Product 2", 112.34);
        Product p1 = new Product(1, "Product 1", 10.21);
        Product p3 = new Product(3,"Product 3", 1112.563);
        
        products.add(p1);
        products.add(p2);
        products.add(p3);
                
        int id = 2;
        DAOProduct instance = new DAOProduct();
        instance.CheckProductInList(index, products, id);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckProductInListIdIsZero() {
        System.out.println("CheckProductInList");
        int index = 1;
        ArrayList<Product> products = new ArrayList<>();
        Product p2 = new Product(2, "Product 2", 112.34);
        Product p1 = new Product(1, "Product 1", 10.21);
        Product p3 = new Product(3,"Product 3", 1112.563);
        
        products.add(p1);
        products.add(p2);
        products.add(p3);
                
        int id = 0;
        DAOProduct instance = new DAOProduct();
        instance.CheckProductInList(index, products, id);
    }
    
    
    @Test(expected = NullPointerException.class)
    public void testCheckProductInListIsNull() {
        System.out.println("CheckProductInList");
        int index = 1;
        ArrayList<Product> products = null;
        int id = 0;
        DAOProduct instance = new DAOProduct();
        instance.CheckProductInList(index, products, id);
    }

    

    /**
     * Test of getProductsBySuppliers method, of class DAOProduct.
     */
   
    
    @Test
    public void testGetProductsBySuppliersStatusDiscountIsEmpty() {
        System.out.println("getProductsBySuppliers");
        int[] id = new int[]{1,2,3};
        int cId = 1;
        String idorder = "1";
        double from = 10000;
        double to = 2000000;
        String statusDiscount = "";
        int expectedResult = 0;
        DAOProduct instance = new DAOProduct();
        Vector<Product> result = instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        assertNotSame(expectedResult,result.size());
    }
    
    
    
    @Test
    public void testGetProductsBySuppliersIdsSizeIsZero() {
        System.out.println("getProductsBySuppliers");
        int[] id = new int[0];
        int cId = 1;
        String idorder = "1";
        double from = 0;
        double to = 2000000;
        String statusDiscount = "";
        int expectedResult = 0;
        DAOProduct instance = new DAOProduct();
        Vector<Product> result = instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        assertNotSame(expectedResult,result.size());
    }
    
    
    
    
    @Test
    public void testGetProductsBySuppliersIdsNull() {
        System.out.println("getProductsBySuppliers");
        int[] id = null;
        int cId = 1;
        String idorder = "0 ";
        double from = 0;
        double to = 2000000;
        String statusDiscount = "Yes";
        int expectedResult = 0;
        DAOProduct instance = new DAOProduct();
        Vector<Product> result = instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        assertNotSame(expectedResult,result.size());
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetProductsBySuppliersCorrect() {
        System.out.println("getProductsBySuppliers");
        int[] id = new int[]{1,2,3};
        int cId = 0;
        String idorder = "1";
        double from = 10000;
        double to = 2000000;
        String statusDiscount = "";
        DAOProduct instance = new DAOProduct();
        instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        fail("IllegalArgumentException");
    }
    
    
    
    @Test
    public void testGetProductsBySuppliersFromAndToIsOutRange() {
        System.out.println("getProductsBySuppliers");
        int[] id = new int[]{1,2,3};
        int cId = 1000;
        String idorder = "7";
        double from = 10000;
        double to = 2000000;
        String statusDiscount = "";
        int expectedResult = 0;
        DAOProduct instance = new DAOProduct();
        Vector<Product> result = instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        assertSame(expectedResult,result.size());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetProductsBySuppliersFromAndToIsNegative() {
        System.out.println("getProductsBySuppliers");
        int[] id = new int[]{1,2,3};
        int cId = 1;
        String idorder = "1";
        double from = -10000;
        double to = -10000;
        String statusDiscount = "";
        DAOProduct instance = new DAOProduct();
        instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        fail("IllegalArgumentException");
    }
    
    @Test
    public void testGetProductsBySuppliersFromAndToIsNotCorrect() {
        System.out.println("getProductsBySuppliers");
        int[] id = new int[]{1,2,3};
        int cId = 1;
        String idorder = "1";
        double from = 99999999;
        double to = 2000000;
        String statusDiscount = "";
        DAOProduct instance = new DAOProduct();
        Vector<Product> result = instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        int expectedResult = 0;
        assertEquals(expectedResult, result.size());
    }
    
    
    @Test(expected = NullPointerException.class)
    public void testGetProductsBySuppliersIsCorrect() {
        System.out.println("getProductsBySuppliers");
        int[] id = new int[]{1,2,3};
        int cId = 1;
        String idorder = "1";
        double from = 10000;
        double to = 2000000;
        String statusDiscount = null;
        DAOProduct instance = new DAOProduct();
        instance.getProductsBySuppliers(id, cId, idorder, from, to, statusDiscount);
        fail("NullPointerException");
    }

    
    
}
