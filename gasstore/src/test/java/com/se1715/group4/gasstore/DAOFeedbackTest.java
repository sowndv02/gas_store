/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAOFeedback;
import com.se1715.group4.gasstore.dto.Feedback;
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
public class DAOFeedbackTest {
    
    public DAOFeedbackTest() {
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
     * Test of getAllFeedbackByEmail method, of class DAOFeedback.
     */
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAllFeedbackByEmailWithFeedbackIsNull() {
        System.out.println("getAllFeedbackByEmail");
        Feedback f = null;
        DAOFeedback instance = new DAOFeedback();
        instance.getAllFeedbackByEmail(f);
    }
    
    
    @Test
    public void testGetAllFeedbackByEmailWithFeedbackEmpty() {
        Feedback f = new Feedback();
        f.setEmail("sample1234@gmail.com");
        DAOFeedback instance = new DAOFeedback();
        int expResult = 0;
        Vector<Feedback> result = instance.getAllFeedbackByEmail(f);
        assertSame(expResult, result.size());
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAllFeedbackByEmailWithEmailEmpty() {
        System.out.println("getAllFeedbackByEmail");
        Feedback f = new Feedback();
        DAOFeedback instance = new DAOFeedback();
        instance.getAllFeedbackByEmail(f);
    }
    
    @Test
    public void testGetAllFeedbackByEmailWithEmailExist() {
        System.out.println("getAllFeedbackByEmail");
        Feedback f = new Feedback();
        f.setEmail("daoson03112002@gmail.com");
        DAOFeedback instance = new DAOFeedback();
        int expResult = 0;
        Vector<Feedback> result = instance.getAllFeedbackByEmail(f);
        assertNotSame(expResult, result.size());
        
    }
    
    
    @Test
    public void testGetAllFeedbackByEmailWithEmailNotExist() {
        System.out.println("getAllFeedbackByEmail");
        Feedback f = new Feedback();
        f.setEmail("a@gmail.com");
        DAOFeedback instance = new DAOFeedback();
        int expResult = 0;
        Vector<Feedback> result = instance.getAllFeedbackByEmail(f);
        assertSame(expResult, result.size());
    }
    
    
    
    @Test
    public void testGetAllFeedbackByEmailFeedbackNotEmpty() {
        System.out.println("getAllFeedbackByEmail");
        Feedback f = new Feedback();
        f.setEmail("daoson03112002@gmail.com");
        DAOFeedback instance = new DAOFeedback();
        int expResult = 0;
        Vector<Feedback> result = instance.getAllFeedbackByEmail(f);
        assertNotSame(expResult, result.size());
    }

    
}
