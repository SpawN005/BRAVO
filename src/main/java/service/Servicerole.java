/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author Rebhy
 */public class Servicerole {
    
     private Connection conn;

    public Servicerole() {
        conn = DataSource.getInstance().getCnx();
    }
    
    public List<Role> findRolesByUserId(int userId) {
    List<Role> roles = new ArrayList<>();

    try {
        String sql = "SELECT * FROM role WHERE user_id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userId);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            Role role = new Role();
            role.setId(res.getInt("id"));
            role.setRole(res.getString("role"));
            roles.add(role);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return roles;
}

public void deleteRole(int role_ID) {
    try {
        String sql = "DELETE FROM role WHERE role_ID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, role_ID);
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
public void deleteRoleByUserId(int user_Id) {
    try {
        String query = "DELETE FROM role WHERE user_id ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, user_Id);
        statement.executeUpdate();
        System.out.println("Roles deleted successfully for user with id " + user_Id);
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
}
