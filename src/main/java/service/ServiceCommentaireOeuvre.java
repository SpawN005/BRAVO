package service;

import entity.CommentaireOeuvre;
import entity.Oeuvre;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCommentaireOeuvre implements IService<CommentaireOeuvre> {
    ServiceOeuvre so=new ServiceOeuvre();
    ServiceUser su=new ServiceUser();
    public ServiceCommentaireOeuvre() {
    }
    private Connection conn = DataSource.getInstance().getCnx();

    @Override
    public void insert(CommentaireOeuvre commentaireOeuvre) {
        String requete = "INSERT INTO commentsOeuvre (oeuvre_id, user_id, comment, timestamp) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(requete);

            st.setInt(1, commentaireOeuvre.getOeuvre().getId());
            st.setInt(2, commentaireOeuvre.getUserId().getId());
            st.setString(3, commentaireOeuvre.getComment());
            st.setTimestamp(4, commentaireOeuvre.getTimestamp());
            st.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }


    }

    @Override
    public void delete(CommentaireOeuvre commentaireOeuvre) {
        String requete = "delete from commentsOeuvre where id="+commentaireOeuvre.getId();

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }


    public void deletebyOeuvre(Oeuvre oeuvre) {
        String requete = "delete from commentsOeuvre where oeuvre_id="+oeuvre.getId();

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public void update(CommentaireOeuvre commentaireOeuvre) {
        String requete= "UPDATE commentsOeuvre SET oeuvre_id = ?, comment = ?, timestamp = ? WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(requete);
            st.setInt(1, commentaireOeuvre.getOeuvre().getId());
            st.setString(2, commentaireOeuvre.getComment());
            st.setTimestamp(3, commentaireOeuvre.getTimestamp());
            st.setInt(4 , commentaireOeuvre.getId());
            st.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public List<CommentaireOeuvre> readAll() {
        List<CommentaireOeuvre> commentaireOeuvre = new ArrayList<>();
        String requete = "SELECT * FROM commentsOeuvre";
        try {

            PreparedStatement ps = conn.prepareStatement(requete);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                CommentaireOeuvre o= new CommentaireOeuvre(rs.getInt("id"),
                        so.readById(rs.getInt("oeuvre_id")),
                        su.readById(rs.getInt("user_id")),
                        rs.getString("comment"),
                        rs.getTimestamp("timestamp")
                );
                commentaireOeuvre.add(o);
            }
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

        return commentaireOeuvre;
    }

    @Override
    public CommentaireOeuvre readById(int id) {
        String requete = "select * from `commentsOeuvre` where `id`=?";
        CommentaireOeuvre co =new CommentaireOeuvre();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                co.setId(id);
                co.setComment(rs.getString("comment"));
                co.setOeuvre(so.readById(id));
                co.setUserId(rs.getInt("user_id"));
                co.setTimestamp(rs.getTimestamp("timestamp"));
            }

        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return co;
    }


    public List<CommentaireOeuvre> readByOeuvre(Oeuvre o) {
        String requete = "select * from `commentsOeuvre` where `oeuvre_id`=? ORDER BY timestamp ASC";
        List<CommentaireOeuvre> commentaireOeuvre = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,o.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                CommentaireOeuvre oeuvre= new CommentaireOeuvre(rs.getInt("id"),
                        so.readById(rs.getInt("oeuvre_id")),
                        su.readById(rs.getInt("user_id")),
                        rs.getString("comment"),
                        rs.getTimestamp("timestamp")
                );
                commentaireOeuvre.add(oeuvre);
            }

        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return commentaireOeuvre;

    }
}