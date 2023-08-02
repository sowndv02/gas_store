/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.se1715.group4.gasstore.dao.DAO;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author ADMIN
 */
public class DAOTest {

    public DAOTest() {
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
     * Test of CheckCompanyAndEmail method, of class DAO.
     */
    /*
        
    
    
    
        
    
    
        
        
    
    
        
        
    
     */
    
    @Test
    public void testCheckCompanyAndEmailTableIsCorrect() {
        System.out.println("CheckCompanyAndEmail");
        String table = "Supplier";
        String company = "PV GAS";
        String email = "pvgas@pvgas.com.vn";
        String phone = "+84 28 3781 6777";
        String homepage = "https://www.pvgas.com.vn";
        DAO instance = new DAO();
        int expResult = 1;
        assertEquals(expResult, instance.CheckCompanyAndEmail(table, company, email, phone, homepage));
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckCompanyAndEmailTableIsNotCorrect() {
        System.out.println("CheckCompanyAndEmail");
        String table = "Student";
        String company = "PV GAS";
        String email = "pvgas@pvgas.com.vn";
        String phone = "+84 28 3781 6777";
        String homepage = "https://www.pvgas.com.vn";
        DAO instance = new DAO();
        instance.CheckCompanyAndEmail(table, company, email, phone, homepage);
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckCompanyAndEmailTableIsNullAndCompanyEmpty() {
        System.out.println("CheckCompanyAndEmail");
        String table = null;
        String company = "";
        String email = "pvgas@pvgas.com.vn";
        String phone = "090xxxxxxx";
        String homepage = "https://www.pvgas.com.vn";
        DAO instance = new DAO();
        instance.CheckCompanyAndEmail(table, company, email, phone, homepage);
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckCompanyAndEmailTableIsNull() {
        System.out.println("CheckCompanyAndEmail");
        String table = null;
        String company = "";
        String email = "pvgas@pvgas.com.vn";
        String phone = "090xxxxxxx";
        String homepage = "https://www.pvgas.com.vn";
        DAO instance = new DAO();
        instance.CheckCompanyAndEmail(table, company, email, phone, homepage);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckCompanyAndEmailCompanyIsEmpty() {
        System.out.println("CheckCompanyAndEmail");
        String table = "Shipper";
        String company = "";
        String email = "pvgas@pvgas.com.vn";
        String phone = "090xxxxxxx";
        String homepage = "";
        DAO instance = new DAO();
        instance.CheckCompanyAndEmail(table, company, email, phone, homepage);
    }

}
