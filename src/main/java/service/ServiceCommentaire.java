package service;

import entity.CommentaireEvent;
import entity.Event;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceCommentaire implements IService<CommentaireEvent> {
    Connection conn;


    public ServiceCommentaire() {

        conn= DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(CommentaireEvent commentaireEvent) {
        String requete="INSERT INTO commentaire(id,content,event.id,user.id) values(?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(requete);
            ps.setInt(1 , commentaireEvent.getId());
            ps.setString(2,commentaireEvent.getContent());
            ps.setInt(3,commentaireEvent.getId_event() );
            ps.setInt(4, commentaireEvent.getId_user());

            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    @Override
    public void delete(CommentaireEvent commentaireEvent) {
        String requete = "delete from commentaire where id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, commentaireEvent.getId());
            ps.executeUpdate();
        } catch (SQLException e ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);
        }

    }
    public void deleteById(CommentaireEvent commentaireEvent , int id_m) {

        String requete = "delete from commentaire where id=" + id_m;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.executeUpdate();
            System.out.println("Le commentaire a été supprimé de la base de données.");

        } catch (SQLException e1) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e1);
            System.out.println("Une erreur s'est produite lors de la suppression du commentaire de la base de données  ");

        }
    }

    @Override
    public void update(CommentaireEvent commentaireEvent) {
        String query = "UPDATE commentaire SET content=? WHERE id=?";
        try {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,commentaireEvent.getContent());

            ps.executeUpdate();

    } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);

        }}
    public void updateById(CommentaireEvent commentaireEvent, int id_m) {

        String query="UPDATE commentaire SET content=? where id="+id_m;

        try {

            PreparedStatement ps=conn.prepareStatement(query);
            ps.executeUpdate(query);
            System.out.println("Le commentaire a été mis à jour.");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Le commentaire n'a pas été mis à jour.");


        }}

        @Override
    public List<CommentaireEvent> readAll() {
            List<CommentaireEvent> list=new ArrayList<>();
            String requete="select* from commentaire";

            try {
                Statement st = conn.createStatement();
                ResultSet rs =st.executeQuery(requete);
                while(rs.next()) {
                    Event e= new Event();
                   /* User u = new User();*/
                    CommentaireEvent c=new CommentaireEvent (rs.getInt("id"),rs.getString(2),rs.getInt(3),rs.getInt(4));
                    list.add(c);
                }
            } catch (SQLException e) {

                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
            }

            return list;
        }



    @Override
    public CommentaireEvent readById(int id) {
        CommentaireEvent c = new CommentaireEvent();
        String requete="select * from `commentaire` where id="+id;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ResultSet rs=ps.executeQuery(requete);

            if(rs.next()) {
                Event e= new Event();
               /* User u = new User();*/
                c.setId_user(rs.getInt(4));
                c.setId(rs.getInt(1));
                c.setContent(rs.getString(2));
                c.setId_event(rs.getInt(3));
                return c;
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }

        return c;
    }
    }

