package service;

import entity.DonsUser;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDonsUser implements IService<DonsUser> {
    private Connection conn = DataSource.getInstance().getCnx();
    public ServiceDonsUser() {

    }

    @Override
    public void insert(DonsUser donsUser){
        String requete = " insert into  donater (id, id_user, id_donation, amount) VALUES ('" + donsUser.getId() + "','" + donsUser.getId_user() + "','" + donsUser.getId_don() + "','" + donsUser.getAmout() + "')";

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
    }

    @Override
    public void delete(DonsUser donsUser) {
        String requete = "DELETE FROM `donater` WHERE 0 ="+donsUser.getId();
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String) null, var4);
        }
        }

    @Override
    public void update(DonsUser donsUser) {
        String requete = "UPDATE donater SET id_user=?,id_donation=?,amount=? WHERE id=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(requete);
            pstmt.setInt(1, donsUser.getId_user());
            pstmt.setInt(2, donsUser.getId_don());
            pstmt.setInt(3, donsUser.getAmout());
            pstmt.setInt(4, donsUser.getId());
            pstmt.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);

        }
    }





    @Override
    public List<DonsUser> readAll() {
        List<DonsUser> list = new ArrayList();
        String requete = "Select * from donater";

        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                DonsUser o = new DonsUser(rs.getInt("id"), rs.getInt("id_user"), rs.getInt("id_donation"), rs.getInt("amount"));
                list.add(o);
            }
        } catch (SQLException var6) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, (String)null, var6);


        }
        return null;
    }

    @Override
    public DonsUser readById(int id) {
        String requete = "select * from `donation` where `id`=?";
        DonsUser o = new DonsUser();
        try{
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                o.setId(id);
                o.setId_user(rs.getInt("id_user"));
                o.setId_don(rs.getInt("id_donation"));
                o.setAmout(rs.getInt("amount"));
            }
        } catch (SQLException var4){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);


        }
        return o;
    }
}
