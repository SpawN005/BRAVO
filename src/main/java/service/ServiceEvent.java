package service;

import entity.Event;
import entity.User;
import utils.DataSource;

import java.sql.*;
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



        String requete="INSERT INTO event(title,description,nb_place_max,date_beg,date_end,categorie_id,image) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(requete);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setInt(3, event.getNb_placeMax());
            ps.setDate(4, Date.valueOf(event.getDate_beg().toLocalDate()));
            ps.setDate(5, Date.valueOf(event.getDate_end().toLocalDate()));

            ps.setInt(6, event.getType_event().getId());
            ps.setString(7,event.getUrl());

            ps.executeUpdate();
            System.out.println("Insertion Terminée");

        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    @Override
    public void delete(Event event) {
        String requete = "delete from event where id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,event.getId());
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
        String query = "UPDATE event SET title=?,description=?,nb_place_max=?,date_beg=?,date_end=?,categorie_id=?,image=? WHERE id=?";
        try {

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, event.getTitle());
            ps.setString(2,event.getDescription());
            ps.setInt(3,event.getNb_placeMax());
            ps.setDate(4, Date.valueOf( event.getDate_beg().toLocalDate()));
            ps.setDate(5,Date.valueOf(event.getDate_end().toLocalDate()));

            ps.setInt(6,event.getType_event().getId());

            ps.setString(7,event.getUrl());
            ps.setInt(8, event.getId());


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
                + "`nb_place_max`='"+event.getNb_placeMax()+"',"
                + "`date_beg`='"+event.getDate_beg()+"',"
                + "`date_end`='"+event.getDate_end()+"',"

                + "`categorie_id`='"+event.getType_event().getId()+"',"

                + "`image`='"+event.getUrl()+"'"
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
        String requete="select * from event";
        ServiceCategorieEvent sce=new ServiceCategorieEvent();
        try {
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(requete);
            while(rs.next()) {

                Event e=new Event (rs.getInt("id"),rs.getString("title"),rs.getString("description"),rs.getInt("nb_place_max"), rs.getDate("date_beg").toLocalDate().atStartOfDay(), rs.getDate("date_end").toLocalDate().atStartOfDay(),sce.readById(rs.getInt("categorie_id")),rs.getString("image"));
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
            ServiceCategorieEvent sce=new ServiceCategorieEvent();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ResultSet rs=ps.executeQuery(requete);

            if(rs.next()) {

                e= new Event (rs.getInt("id"),rs.getString("title"),rs.getString("description"),rs.getInt("nb_place_max"), rs.getDate("date_beg").toLocalDate().atStartOfDay(), rs.getDate("date_end").toLocalDate().atStartOfDay(),sce.readById(rs.getInt("categorie_id")),rs.getString("image"));
                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }

        return e;
    }
    public List<Event> filterByType(String type) {
        List<Event> events = new ArrayList<>();
        ServiceCategorieEvent sce = new ServiceCategorieEvent();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String query = "SELECT * FROM event WHERE title LIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%"+ type+ "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setNb_placeMax(rs.getInt("nb_place_max"));
                event.setDate_beg(rs.getDate("date_beg").toLocalDate().atStartOfDay());
                event.setDate_end(rs.getDate("date_end").toLocalDate().atStartOfDay());
                event.setType_event(sce.readById(rs.getInt("categorie_id")));
                event.setUrl(rs.getString("image"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return events;
    }
}
