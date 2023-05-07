package service;

import entity.Blog;
import entity.NoteBlog;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceNoteBlog implements IService<NoteBlog> {
    ServiceBlog sb= new ServiceBlog();
    Blog b = new Blog();
    private Connection conn;

    public ServiceNoteBlog() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(NoteBlog nb) {
        String requete="REPLACE INTO note_blog(note,blog_id,user_id) values(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);

            pst.setDouble(1, nb.getNote());
            pst.setInt(2, nb.getId_blog().getId());
            pst.setInt(3, nb.getUser());
            pst.executeUpdate();

        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @Override
    public void delete(NoteBlog nb) {
        String requete = "delete from note_blog where blog_id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, nb.getId_blog().getId());
            pst.executeUpdate();
        } catch (SQLException e ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);
        }
    }

    public void deleteByBlog(Blog b) {
        String requete = "delete from note_blog where blog_id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, b.getId());
            pst.executeUpdate();
        } catch (SQLException e ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);
        }
    }


    @Override
    public void update(NoteBlog nb) {
        String requete= "UPDATE note_blog SET note = ? WHERE id = ? ";

        try  {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setDouble(1,nb.getNote());
            pst.setInt(2,nb.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<NoteBlog> readAll() {
        List<NoteBlog> list = new ArrayList<>();
        String requete= "select * from note_blog";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                NoteBlog nb= new NoteBlog(rs.getInt("id"),
                        rs.getDouble("note"),
                        sb.readById(rs.getInt("id_blog"))
                );
                list.add(nb);
            }
        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }


    @Override
    public NoteBlog readById(int id) {
        String requete="select * from note_blog where id=?";
        NoteBlog nb= new NoteBlog();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                nb.setId(id);
                nb.setNote(rs.getDouble("note"));
                nb.setId_blog(sb.readById(rs.getInt("id_blog")));
            }

        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return nb;
    }

    public float readAvg(Blog b) {

        String requete = "select AVG(note) from note_blog where blog_id= ?";
        float note=0;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,b.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                note= rs.getFloat(1);
            }

        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return note;
    }


}
