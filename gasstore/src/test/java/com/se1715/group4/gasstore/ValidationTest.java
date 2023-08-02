/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.util.Validation;
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
public class ValidationTest {
    
    public ValidationTest() {
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
     * Test of checkUsernamePassword method, of class Validation.
     */
    
    
    @Test
    public void testCheckUsernamePassword1() {
        System.out.println("checkUsernamePassword");
        String username = "sondvabc#";
        String password = "daovanson03";
        boolean result = Validation.checkUsernamePassword(username, password);
        assertFalse(result);
    }
    
    
    @Test
    public void testCheckUsernamePassword2() {
        System.out.println("checkUsernamePassword");
        String username = "sond";
        String password = "sndv091";
        boolean result = Validation.checkUsernamePassword(username, password);
        assertFalse(result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckUsernamePassword3() {
        System.out.println("checkUsernamePassword");
        String username = null;
        String password = null;
        Validation.checkUsernamePassword(username, password);
    }
    
    @Test
    public void testCheckUsernamePassword4() {
        System.out.println("checkUsernamePassword");
        String username = "son";
        String password = "sondv091";
        boolean result = Validation.checkUsernamePassword(username, password);
        assertFalse(result);
    }
    
    @Test
    public void testCheckUsernamePassword5() {
        System.out.println("checkUsernamePassword");
        String username = "sondvabc#";
        String password = "daovanson03";
        boolean result = Validation.checkUsernamePassword(username, password);
        assertFalse(result);
    }
    
    @Test
    public void testCheckUsernamePassword6() {
        System.out.println("checkUsernamePassword");
        String username = "sondvabcxyz123456789";
        String password = "Sondv091";
        boolean result = Validation.checkUsernamePassword(username, password);
        assertTrue(result);
    }
    
    @Test
    public void testCheckUsernamePassword7() {
        System.out.println("checkUsernamePassword");
        String username = "sondv02";
        String password = "daovanson03";
        boolean result = Validation.checkUsernamePassword(username, password);
        assertTrue(result);
    }

}
