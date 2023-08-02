/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se1715.group4.gasstore;

import com.se1715.group4.gasstore.dao.DAOBlog;
import com.se1715.group4.gasstore.dto.Blog;
import java.util.ArrayList;
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
public class DAOBlogTest {
    
    public DAOBlogTest() {
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
     * Test of getBlogsByMonth method, of class DAOBlog.
     */
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBlogsByMonthOutRange1() {
        System.out.println("getBlogsByMonth");
        int month = 0;
        DAOBlog instance = new DAOBlog();
        instance.getBlogsByMonth(month);
        fail("IllegalArgumentException");
    }

    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBlogsByMonthOutRange2() {
        System.out.println("getBlogsByMonth");
        int month = 13;
        DAOBlog instance = new DAOBlog();
        instance.getBlogsByMonth(month);
        fail("IllegalArgumentException");
    }
    
    
    @Test
    public void testGetBlogsByMonthCorrect() {
        int month = 2;
        DAOBlog instance = new DAOBlog();
        int expResult = 0;
        ArrayList<Blog> result = instance.getBlogsByMonth(month);
        assertNotSame(expResult, result.size());
    }
}
