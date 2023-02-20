package service;

import entity.Commentaire;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCommentaireBlog implements IService<Commentaire> {
    ServiceBlog sb= new ServiceBlog();
    ServiceUser su = new ServiceUser();
    private Connection conn;

    public ServiceCommentaireBlog() {
        conn = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Commentaire c) {
        String requete = "INSERT INTO commentaire (id, content,id_blog, user_id,) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);

            pst.setInt(1, c.getId());
            pst.setString(2, c.getContent());
            pst.setInt(3, c.getId_blog().getId());
            pst.setInt(4, c.getId_user().getId());
            pst.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String) null, var4);

        }
    }

    @Override
    public void delete(Commentaire c) {
        String requete = "delete from commentsOeuvre where id="+c.getId();

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }


    @Override
    public void update(Commentaire c) {
        String requete= "UPDATE commentsOeuvre SET content = ?, is_blog = ?, id_user = ? WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(requete);

            st.setString(1, c.getContent());
            st.setInt(2, c.getId_blog().getId());
            st.setInt(3, c.getId_user().getId());
            st.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public List<Commentaire> readAll() {
        List<Commentaire> c = new ArrayList<>();
        String requete = "SELECT * FROM commentaire";

        try {

            PreparedStatement pst = conn.prepareStatement(requete);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                Commentaire c1 = new Commentaire(rs.getInt("id"),
                        rs.getString("content"),
                        sb.readById(rs.getInt("id_blog")),
                        su.readById(rs.getInt("id_user"))
                );

                c.add(c1);
            }
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

        return c;

    }

    @Override
    public Commentaire readById(int id) {
        String requete = "select * from commentaire where `id`=?";
        Commentaire cb =new Commentaire();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cb.setId(id);
                cb.setContent(rs.getString("content"));
                cb.setId_blog(sb.readById(id));
                cb.setId_user(su.readById(id));
            }

        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return cb;
    }
}
