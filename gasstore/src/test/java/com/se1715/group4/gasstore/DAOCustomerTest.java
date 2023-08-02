/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAOCustomer;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.UserGoogle;
import java.io.InputStream;
import java.util.Map;
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
public class DAOCustomerTest {

    public DAOCustomerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        DAOCustomer instance = new DAOCustomer();
        if (instance.checkLoginGoogle("111174407274520862894") == null) {
            UserGoogle user = new UserGoogle();
            user.setId("111174407274520862894");
            instance.AddLoginGoogle(user);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkLoginGoogle method, of class DAOCustomer.
     */
    @Test
    public void testCheckLoginGoogleCorrect() {
        System.out.println("checkLoginGoogle");
        String googleId = "111174407274520862894";
        DAOCustomer instance = new DAOCustomer();
        Customer result = instance.checkLoginGoogle(googleId);
        assertNotNull(result);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLoginGoogleIdIsNull() {
        System.out.println("checkLoginGoogle");
        String googleId = null;
        DAOCustomer instance = new DAOCustomer();
        instance.checkLoginGoogle(googleId);
        fail("IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLoginGoogleIdIsEmpty() {
        System.out.println("checkLoginGoogle");
        String googleId = "";
        DAOCustomer instance = new DAOCustomer();
        instance.checkLoginGoogle(googleId);
        fail("IllegalArgumentException");
    }

}
