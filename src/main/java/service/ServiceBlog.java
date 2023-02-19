package service;

import entity.Blog;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;

public class ServiceBlog implements IService <Blog> {
    private Connection conn;

    public ServiceBlog() {
        conn = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Blog b) {
        int i = b.getAuthor();
        String requete = "insert into blog (title,description,content,author) values"
                +"('" +b.getTitle() + "' ,'" + b.getDescription() +"', '" + b.getContent() +"', '" + i +"')";

        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("le blog n'est pas inséré dans la base");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("le blog est inséré avec succès");
        }
    }

    @Override
    public void delete(Blog b) {
        String requete = "delete from blog where id="+b.getId();
        try {
            PreparedStatement pst =conn.prepareStatement(requete);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("un probleme est survenu lors de la suppression du blog");
        }
        System.out.println("la suppression du blog a été effectuée avec succès ");

    }

    @Override
    public void update(Blog b) {
        String requete =" update blog set content='?' where author='?'";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Blog> readAll() {
        List<Blog> list = new ArrayList<>();
        ServiceUser su = new ServiceUser();
        User u = new User();
        Blog b = new Blog();
        String requete = "select * from blog";


        try {
            Statement st = conn.createStatement();

            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){

                b.setId(rs.getInt(1));
                b.setContent(rs.getString(4));
                b.setTitle(rs.getString(2));
                b.setDescription(rs.getString(3));
                u = su.readById(rs.getInt(5));
                b.setAuthor(u.getId());

                list.add(b);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Blog readById(int id) {
        Blog b = new Blog();
        ServiceUser su = new ServiceUser();
        User user = new User();
        String requete= "select * from blog where id="+id;
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs= pst.executeQuery();

            if(rs.next()){
                b = new Blog(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
                user = su.readById(rs.getInt(5));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("le propriétaire de ce " +b+ " est "+" email = " +user.getEmail()+", " + " first name = " + user.getFirstName()+ " , "+ " last name = " + user.getLastName()+" , "+ " role = " + user.getRole()+" , "+ " id = " + user.getId());
        return b;
    }

}
