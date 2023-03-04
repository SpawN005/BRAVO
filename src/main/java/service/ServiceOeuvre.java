package service;

import entity.Oeuvre;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceOeuvre implements IService<Oeuvre>{
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceOeuvre() {
    }

    @Override
    public void insert(Oeuvre oeuvre) {
        String requete = "insert into artwork (title,description,owner,catégorie,url) values ('" + oeuvre.getTitle() + "','" + oeuvre.getDescription() + "','"+ oeuvre.getOwner().getId()+"','"+oeuvre.getCategory()+"','"+oeuvre.getUrl()+"')";

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }


    }

    @Override
    public void delete(Oeuvre oeuvre) {
        String requete = "delete from artwork where id="+oeuvre.getId();

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
    }

    @Override
    public void update(Oeuvre oeuvre) {
        String requete = "update artwork set title='"+oeuvre.getTitle()+"', description='"+oeuvre.getDescription()+"', owner='"+oeuvre.getOwner()+"', catégorie='"+oeuvre.getCategory()+"', url='"+oeuvre.getUrl()+"' where id="+oeuvre.getId();
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }


    }

    @Override
    public List<Oeuvre> readAll() {
        List<Oeuvre> list = new ArrayList();
        String requete = "select * from artwork";
        ServiceUser su= new ServiceUser();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                Oeuvre o= new Oeuvre(rs.getInt("id"), rs.getString("title"), rs.getString("description"), su.readById(rs.getInt("owner")), rs.getString("catégorie"), rs.getString("url"));
                list.add(o);
            }
        } catch (SQLException var6) {
            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

        return list;

    }

    @Override
    public Oeuvre readById(int id) {
        String requete = "select * from `artwork` where `id`=?";
        ServiceUser su= new ServiceUser();
            Oeuvre o =new Oeuvre();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){  o.setId(id);
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setOwner(su.readById(rs.getInt("owner")));
                o.setCategory(rs.getString("catégorie"));
                o.setUrl(rs.getString("url"));}


        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return o;

    }

    public List<Oeuvre> AfficherListeOeuvresASC() {

        String requete = "SELECT * FROM `artwork` ORDER BY title ASC";
        List<Oeuvre> list = new ArrayList();
        ServiceUser su= new ServiceUser();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Oeuvre o = new Oeuvre();
                o.setId(rs.getInt("id"));
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setOwner(su.readById(rs.getInt("owner")));
                o.setCategory(rs.getString("catégorie"));
                o.setUrl(rs.getString("url"));
                list.add(o);

            }
        } catch (SQLException ex) {
            System.out.println("erreur d'affichage");
        }

        return list;
    }


    public List<Oeuvre> AfficherListeOeuvresDES() {
        ServiceUser su= new ServiceUser();
        String requete = "SELECT * FROM `artwork` ORDER BY title DESC";
        List<Oeuvre> list = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Oeuvre o = new Oeuvre();
                o.setId(rs.getInt("id"));
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setOwner(su.readById(rs.getInt("owner")));
                o.setCategory(rs.getString("catégorie"));
                o.setUrl(rs.getString("url"));
                list.add(o);

            }
        } catch (SQLException ex) {
            System.out.println("erreur d'affichage");
        }

        return list;

    }
    public List<Oeuvre> RechercheTitre(String title) {
        ServiceUser su= new ServiceUser();
        String requete = "SELECT * FROM `artwork` where title LIKE '%"+title+"%'";
        List<Oeuvre> list = new ArrayList();
        try {
            Statement ps = conn.createStatement();

            ResultSet rs = ps.executeQuery(requete);

            while (rs.next()) {
                Oeuvre o = new Oeuvre();
                o.setId(rs.getInt("id"));
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setOwner(su.readById(rs.getInt("owner")));
                o.setCategory(rs.getString("catégorie"));
                o.setUrl(rs.getString("url"));
                list.add(o);

            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, ex);

        }

        return list;

    }
}
