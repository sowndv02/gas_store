/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Blog;
import com.se1715.group4.gasstore.dto.Member;
import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DAOMember extends DBUtil {

    private final Connection connection = DBUtil.makeConnection();

    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "select * from Member";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int memberId = rs.getInt("memberId");
                String name = rs.getString("name");
                String title = rs.getString("title");
                String img = rs.getString("img");
                String facebook = rs.getString("facebook");
                String twitter = rs.getString("twitter");
                members.add(new Member(memberId, name, title, img, facebook, twitter));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return members;
    }
    
    public static void main(String[] args) {
        DAOMember dao = new DAOMember();
        System.out.println(dao.getAllMembers().get(1).getName());
    }
}
