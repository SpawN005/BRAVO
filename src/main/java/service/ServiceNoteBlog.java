package service;

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
    private Connection conn;

    public ServiceNoteBlog() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(NoteBlog nb) {
        String requete="INSERT INTO note(note,id_blog) values(?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);

            pst.setInt(1, nb.getNote());
            pst.setInt(2, nb.getId_blog().getId());
            pst.executeUpdate();

        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @Override
    public void delete(NoteBlog nb) {
        String requete = "delete from note where id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, nb.getId());
            pst.executeUpdate();
        } catch (SQLException e ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);
        }
    }


    @Override
    public void update(NoteBlog nb) {
        String requete= "UPDATE note SET note = ? WHERE id = ? ";

        try  {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,nb.getNote());
            pst.setInt(2,nb.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<NoteBlog> readAll() {
        List<NoteBlog> list = new ArrayList<>();
        String requete= "select * from note";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                NoteBlog nb= new NoteBlog(rs.getInt("id"),
                        rs.getInt("note"),
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
        String requete="select * from note where id=?";
        NoteBlog nb= new NoteBlog();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                nb.setId(id);
                nb.setNote(rs.getInt("note"));
                nb.setId_blog(sb.readById(rs.getInt("id_blog")));
            }

        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return nb;
    }
}
