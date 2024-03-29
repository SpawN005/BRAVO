package service;

import entity.Blog;
import utils.DataSource;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceBlog implements IService <Blog> {
    ServiceUser su = new ServiceUser();

    private Connection conn;

    public ServiceBlog() {
        conn = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Blog b) {
        String requete ="insert into blog (id,title,description,content,image,author_id,categorie_id) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,b.getId());
            pst.setString(2, b.getTitle());
            pst.setString(3, b.getDescription());
            pst.setString(4, b.getContent());
            pst.setString(5, b.getUrl());
            pst.setInt(6,b.getAuthor().getId());
            pst.setInt(7,b.getCat().getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @Override
    public void delete(Blog b) {

        String requete = "delete from blog where id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,b.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("un probleme est survenu lors de la suppression du blog");
        }
        System.out.println("la suppression du blog a été effectuée avec succès ");
    }

    @Override
    public void update(Blog b) {
        String requete =" update blog set title=? , description=? , content=?, image=? , categorie_id= ? where id=? ";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, b.getTitle());
            pst.setString(2, b.getDescription());
            pst.setString(3, b.getContent());
            pst.setString(4, b.getUrl());
            pst.setInt(5, b.getCat().getId());
            pst.setInt(6, b.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Blog> readAll() {
        List<Blog> list = new ArrayList<>();
        ServiceUser su = new ServiceUser();
        ServiceCategorieBlog scb = new ServiceCategorieBlog();
        String requete = "select * from blog";

        try {
            Statement st = conn.createStatement();

            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){
                Blog b = new Blog(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("image"),
                        su.readById(rs.getInt("author_id")),
                        scb.readById(rs.getInt("categorie_id"))




                );


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
        String requete= "select * from blog where id="+id;
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
//            pst.setInt(1, id);
            ResultSet rs= pst.executeQuery();

            while(rs.next()){

                b.setId(id);
                b.setTitle(rs.getString("title"));
                b.setDescription(rs.getString("description"));
                b.setContent(rs.getString("content"));
                b.setUrl(rs.getString("url"));
                b.setAuthor(su.readById(rs.getInt("author")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }


    public List<Blog> recherchePartitre(String title){
        List<Blog> list = new ArrayList<>();
        String requete = "select * from blog where title like '%"+title+"%'";
        try {
            Statement st= conn.createStatement();

            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){
                Blog b = new Blog(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("url"),
                        su.readById(rs.getInt("author")));
                list.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }



}
