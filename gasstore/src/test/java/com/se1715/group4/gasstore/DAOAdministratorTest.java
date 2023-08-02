/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAOAdministrator;
import com.se1715.group4.gasstore.dto.Administrator;
import java.io.InputStream;
import java.util.Map;
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
public class DAOAdministratorTest {

    public DAOAdministratorTest() {
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

    @Test
    public void testGetAdminExist() {
        String username = "admin";
        DAOAdministrator instance = new DAOAdministrator();
        Administrator result = instance.getAdminByUsername(username);
        assertNotNull(result);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAdminByUsernameEmpty() {
        System.out.println("getAdminByUsername");
        String username = "";
        DAOAdministrator instance = new DAOAdministrator();
        instance.getAdminByUsername(username);
        fail("IllegalArgumentException");
    }

    @Test
    public void testGetAdminByUsernameNotExist() {
        System.out.println("getAdminByUsername");
        String username = "sondv0302";
        DAOAdministrator instance = new DAOAdministrator();
        Administrator result = instance.getAdminByUsername(username);
        assertNull(result);
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetAdminIsNull() {
        System.out.println("getAdminByUsername");
        String username = "employee";
        DAOAdministrator instance = new DAOAdministrator();
        instance.getAdminByUsername(username);
        fail("NullPointerException");
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAdminByUsernameIsNull() {
        System.out.println("getAdminByUsername");
        String username = null;
        DAOAdministrator instance = new DAOAdministrator();
        instance.getAdminByUsername(username);
    }
}
