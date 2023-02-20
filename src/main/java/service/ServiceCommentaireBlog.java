package service;

import entity.CommentaireBlog;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCommentaireBlog implements IService<CommentaireBlog> {
    ServiceBlog sb= new ServiceBlog();
    ServiceUser su = new ServiceUser();
    private Connection conn;

    public ServiceCommentaireBlog() {
        conn = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(CommentaireBlog c) {
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
    public void delete(CommentaireBlog c) {
        String requete = "delete from commentsOeuvre where id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, c.getId());
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }


    @Override
    public void update(CommentaireBlog c) {
        String requete= "UPDATE commentsOeuvre SET content = ?, id_blog = ?, id_user = ? WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);

            pst.setString(1, c.getContent());
            pst.setInt(2, c.getId_blog().getId());
            pst.setInt(3, c.getId_user().getId());
            pst.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public List<CommentaireBlog> readAll() {
        List<CommentaireBlog> c = new ArrayList<>();
        String requete = "SELECT * FROM commentaire";

        try {

            PreparedStatement pst = conn.prepareStatement(requete);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                CommentaireBlog c1 = new CommentaireBlog(rs.getInt("id"),
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
    public CommentaireBlog readById(int id) {
        String requete = "select * from commentaire where `id`=?";
        CommentaireBlog cb =new CommentaireBlog();
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
