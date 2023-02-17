package service;

import entity.Oeuvre;
import entity.User;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String requete = "insert into artwork (title,description,owner,catégorie,url) values ('" + oeuvre.getTitle() + "','" + oeuvre.getDescription() + "','"+ oeuvre.getOwner()+"','"+oeuvre.getCategory()+"','"+oeuvre.getUrl()+"')";

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
        String requete = "update artwork set title="+oeuvre.getTitle()+", description="+oeuvre.getDescription()+", owner"+oeuvre.getOwner()+", catégorie="+oeuvre.getCategory()+", url="+oeuvre.getUrl()+"where id="+oeuvre.getId();
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

        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                Oeuvre o= new Oeuvre(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("owner"), rs.getString("catégorie"), rs.getString("url"));
                list.add(o);
            }
        } catch (SQLException var6) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

        return list;

    }

    @Override
    public Oeuvre readById(int id) {
        String requete = "select from artwork where id="+id;
        Oeuvre o =new Oeuvre();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);st.executeUpdate(requete);
            o.setId(id);
            o.setTitle(rs.getString("title"));
            o.setDescription(rs.getString("description"));
            o.setOwner(rs.getString("owner"));
            o.setCategory(rs.getString("catégorie"));
            o.setUrl(rs.getString("url"));
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return o;

    }

    public List<Oeuvre> AfficherListeOeuvresASC() {

        String requete = "SELECT * FROM `artwork` ORDER BY title ASC";
        List<Oeuvre> list = new ArrayList();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Oeuvre o = new Oeuvre();
                o.setId(rs.getInt("id"));
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setOwner(rs.getString("owner"));
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
                o.setOwner(rs.getString("owner"));
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

        String requete = "SELECT * FROM `artwork` where title LIKE %"+title+"%";
        List<Oeuvre> list = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Oeuvre o = new Oeuvre();
                o.setId(rs.getInt("id"));
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setOwner(rs.getString("owner"));
                o.setCategory(rs.getString("catégorie"));
                o.setUrl(rs.getString("url"));
                list.add(o);

            }
        } catch (SQLException ex) {
            System.out.println("erreur d'affichage");
        }

        return list;

    }
}
