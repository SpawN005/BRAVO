//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package service;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Oeuvre;
import entity.User;
import utils.DataSource;

public class ServiceUser implements IService<User> {
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceUser() {
    }

    public void insert(User user) {
        String requete ="insert into User (firstName,lastName,phoneNumber,email,role) values (?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setInt(3, user.getPhoneNumber());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getRole());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(User user) {
        String requete = "delete from user where id="+user.getId();
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(User user) {
        String requete =" update user set firstName=? AND lastName=? AND phoneNumber=? AND email=? AND role=? where id=? ";
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<User> readAll() {
        List<User> list = new ArrayList<>();
        String requete = "select * from user";
        try {
            Statement st = conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){
                User u= new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public User readById(int id) {

        String requete = "select * from user where id="+id;
        User u =new User();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                u.setId(id);
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setPhoneNumber(rs.getInt("phoneNumber"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return u;
    }

}
