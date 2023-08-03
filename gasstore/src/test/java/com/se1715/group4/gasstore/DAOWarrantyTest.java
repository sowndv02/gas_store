/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAOWarranty;
import com.se1715.group4.gasstore.dto.Warranty;
import com.se1715.group4.gasstore.dto.WarrantyImg;
import com.se1715.group4.gasstore.dto.WarrantyPolicy;
import jakarta.servlet.http.Part;
import java.util.List;
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
public class DAOWarrantyTest {
    
    public DAOWarrantyTest() {
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
     * Test of getWarrantyById method, of class DAOWarranty.
     */
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetWarrantyByIdIsNotCorrect() {
        System.out.println("getWarrantyById");
        int id = 0;
        DAOWarranty instance = new DAOWarranty();
        instance.getWarrantyById(id);
    }
    
    
    
    @Test
    public void testGetWarrantyByIdOutRange() {
        System.out.println("getWarrantyById");
        int id = 1000;
        DAOWarranty instance = new DAOWarranty();
        Warranty result = instance.getWarrantyById(id);
        assertNull(result);
    }

   
    
}
