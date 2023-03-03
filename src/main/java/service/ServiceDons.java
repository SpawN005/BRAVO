package service;

import entity.CommentaireOeuvre;
import entity.Dons;
import entity.Oeuvre;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDons implements IService<Dons> {
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceDons() {

    }

    @Override
    public void insert(Dons dons) {
        String requete = "insert into Donation (id,title,description,date_creation,date_expiration,amount,owner ) values ('" + dons.getId() + "','" + dons.getTitle() + "','"+ dons.getDescription()+"','"+ dons.getDate_creation()+"','"+dons.getDate_expiration()+"','"+dons.getAmount()+"','"+dons.getOwner()+"')";

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public void delete(Dons dons) {
        String requete = "delete from `donation` where `id`="+dons.getId();
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public void update(Dons dons) {
        String requete = "UPDATE donation SET title=?, description=?, date_creation=?, date_expiration=?, amount=?, owner=? WHERE id=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(requete);
            pstmt.setString(1, dons.getTitle());
            pstmt.setString(2, dons.getDescription());
            pstmt.setDate(3, new java.sql.Date(dons.getDate_creation().getTime()));
            pstmt.setDate(4, new java.sql.Date(dons.getDate_expiration().getTime()));
            pstmt.setInt(5, dons.getAmount());
            pstmt.setString(6, dons.getOwner());
            pstmt.setInt(7, dons.getId());
            pstmt.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
    }

    @Override
    public List<Dons> readAll() {
        List<Dons> list = new ArrayList();
        String requete = "select * from donation";

        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                Dons o= new Dons(rs.getInt("id"), rs.getString("title"),  rs.getString("description"), rs.getTimestamp("date_creation"), rs.getTimestamp("date_expiration"),  rs.getInt("amount"), rs.getString("owner"));
                list.add(o);
            }
        } catch (SQLException var6) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

        return list;
    }

    @Override
    public Dons readById(int id) {
        String requete = "select * from `donation` where `id`=?";
        Dons o =new Dons();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){  o.setId(id);
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setDate_creation(rs.getTimestamp("date_creation"));
                o.setDate_expiration(rs.getTimestamp("date_expiration"));
                o.setAmount(rs.getInt("amount"));
                o.setOwner(rs.getString("owner"));}


        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return o;
    }



}


