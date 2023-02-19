package service;


import entity.CommentaireEvent;
import entity.Event;
import entity.Reservation;

import entity.User;
import utils.DataSource;

import java.nio.file.FileAlreadyExistsException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceReservation implements IService<Reservation> {
    Connection conn;


    public ServiceReservation() {

        conn= DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reservation reservation) {
        String requete = "INSERT INTO reservation (id_participant, id_event, isConfirmed, nb_place) VALUES (?,?,?,?) ";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,reservation.getId_participant());
            ps.setInt(2,reservation.getId_event());
            ps.setBoolean(3, reservation.isConfirmed());
            ps.setInt(4,reservation.getNb_place());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Reservation reservation) {
        String requete = "delete from reservation where id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, reservation.getId());
            ps.executeUpdate();
        } catch (SQLException e ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);
        }

    }
    public void deleteById(Reservation reservation , int id_m) {

        String requete = "delete from reservation where id=" + id_m;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.executeUpdate();
            System.out.println("La reservation a été supprimée de la base de données.");

        } catch (SQLException e1) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e1);
            System.out.println("Une erreur s'est produite lors de la suppression de la reservation de la base de données  ");

        }
    }

    @Override
    public void update(Reservation reservation) {
        String query = "UPDATE reservation SET isConfirmed=?, nb_place=? WHERE id=?";
        try {
ServiceReservation sr= new ServiceReservation();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1,reservation.isConfirmed());
            ps.setInt(2,reservation.getNb_place());
            ps.setInt(3,2);
            if (reservation.isConfirmed() == false){
                System.out.println("La réservation n'a pas été confirmée.");

            }

            // confirmer la réservation
          else {
              reservation.setConfirmed(true);
            }
            ps.executeUpdate();

            System.out.println("La réservation a été confirmée.");


        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);

        }

    }
    public void updateById(Reservation reservation, int id_m) {

        String query = "UPDATE reservation SET  isConfirmed=?, nb_place=? where id=" + id_m;

        try {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate(query);
            System.out.println("La reservation a été mise à jour.");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("La reservation n'a pas été mise à jour.");


        }
    }

    @Override
    public List<Reservation> readAll() {
        List<Reservation> list=new ArrayList<>();
        String requete="select* from reservation";

        try {
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(requete);
            while(rs.next()) {
                Reservation r=new Reservation (rs.getInt("id"),rs.getInt(2),rs.getInt("id_event"),rs.getBoolean("isConfirmed"),rs.getInt("nb_place"));
                list.add(r);
            }
        } catch (SQLException e) {

            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }

        return list;
    }

    @Override
    public Reservation readById(int id) {
        Reservation r = new Reservation();
        String requete="select * from reservation where id="+id;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ResultSet rs=ps.executeQuery(requete);

            if(rs.next()) {
                r= new Reservation(rs.getInt(1),rs.getInt("id_participant"),rs.getInt(3),rs.getBoolean(4),rs.getInt(5));
                return r;
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }

        return r;
    }
    }
