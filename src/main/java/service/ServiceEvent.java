package service;

import entity.Event;
import entity.User;
import utils.DataSource;
import entity.Reservation;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceEvent implements IService<Event> {
    Connection conn;


    public ServiceEvent() {
        conn=DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Event event) {
        List<Reservation> reservations=new ArrayList<>();

        String requete="INSERT INTO event(title,description,nb_placeMax,date_beg,date_end,note,type_event,id_commentaire,url) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(requete);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setInt(3, event.getNb_placeMax());
            ps.setDate(4, Date.valueOf(event.getDate_beg()));
            ps.setDate(5,   Date.valueOf(event.getDate_end()));
            ps.setInt(6, event.getNote());
            ps.setString(7, event.getType_event());
            ps.setInt(8, event.getId_commentaire());
            ps.setString(9,event.getUrl());

            ps.executeUpdate();


        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    @Override
    public void delete(Event event) {
        String requete = "delete from event where id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,5);
            ps.executeUpdate();
        } catch (SQLException e ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);
        }


    }

    public void deleteById(Event event , int id_m) {

        String requete = "delete from event where id=" + id_m;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.executeUpdate();
            System.out.println("L'événement a été supprimé de la base de données.");

        } catch (SQLException e1) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e1);
            System.out.println("Une erreur s'est produite lors de la suppression de l'événement de la base de données  ");

        }
    }


    @Override
    public void update(Event event) {
        String query = "UPDATE event SET title=?,description=?,nb_placeMax=?,date_beg=?,date_end=?,note=?,type_event=?,id_commentaire=?,url=? WHERE id=?";
        try {

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, event.getTitle());
            ps.setString(2,event.getDescription());
            ps.setInt(3,event.getNb_placeMax());
            ps.setDate(4, Date.valueOf(event.getDate_beg()));
            ps.setDate(5,Date.valueOf(event.getDate_end()));
            ps.setInt(6,event.getNote());
            ps.setString(7,event.getType_event());
            ps.setInt(8,event.getId_commentaire());
            ps.setString(9,event.getUrl());
            ps.setInt(10, event.getId());


            ps.executeUpdate();
            System.out.println("L'événement a été mis à jour.");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("L'événement n'a pas été mis à jour.");

        }
    }
    public void updateById(Event event, int id_m) {


        String query="UPDATE event SET "
                + "`title`='"+event.getTitle()+"',"
                + "`description`='"+event.getDescription()+"',"
                + "`nb_placeMax`='"+event.getNb_placeMax()+"',"
                + "`date_beg`='"+event.getDate_beg()+"',"
                + "`date_beg`='"+event.getDate_end()+"',"
                + "`note`='"+event.getNote()+"',"
                + "`type_event`='"+event.getType_event()+"',"
                + "`id_commentaire`='"+event.getId_commentaire()+"',"
                + "`url`='"+event.getUrl()+"'"
                + " WHERE id="+id_m;
        try {

            PreparedStatement ps=conn.prepareStatement(query);
            ps.executeUpdate(query);
            System.out.println("L'événement a été mis à jour.");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("L'événement n'a pas été mis à jour.");


        }}

    @Override
    public List<Event> readAll() {
        List<Event> list=new ArrayList<>();
        String requete="select* from event";

        try {
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(requete);
            while(rs.next()) {
                Event e=new Event (rs.getInt("id"),rs.getString(2),rs.getString("description"),rs.getInt("nb_placeMax"),rs.getDate(5).toLocalDate(),rs.getDate(6).toLocalDate(),rs.getInt("note"),rs.getString("type_event"),rs.getInt("id_commentaire"),rs.getString("url"));
                list.add(e);
            }
        } catch (SQLException e1) {

            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e1);
        }

        return list;
    }

    @Override
    public Event readById(int id) {
        Event e = new Event();
        String requete="select * from event where id="+id;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ResultSet rs=ps.executeQuery(requete);

            if(rs.next()) {
                e= new Event (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5).toLocalDate(),rs.getDate(6).toLocalDate(),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getString(10));
                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }

        return e;
    }
}
