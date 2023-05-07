package service;


import entity.Reservation;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ServiceReservation implements IService<Reservation> {
    Connection conn;
ServiceUser SU = new ServiceUser();
ServiceEvent SE = new ServiceEvent();

    public ServiceReservation() {

        conn= DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reservation reservation) {
        String requete = "INSERT INTO reservation (id_participant_id, id_event_id, is_confirmed, nb_place) VALUES (?,?,?,?) ";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,reservation.getId_participant().getId());
            ps.setInt(2,reservation.getId_event().getId());
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
        String query = "UPDATE reservation SET id_participant_id=?, id_event_id=?, is_confirmed=?, nb_place=? WHERE id=?";
        try {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,reservation.getId_participant().getId());
            ps.setInt(2,reservation.getId_event().getId());
            ps.setBoolean(3,reservation.isConfirmed());
            ps.setInt(4,reservation.getNb_place());
            ps.setInt(5,reservation.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);

        }

    }
    public void updateById(Reservation reservation, int id_m) {

        String query = "UPDATE reservation SET id_participant_id=?, id_event_id=?,is_confirmed=?, nb_place=? where id=" + id_m;

        try {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,reservation.getId_participant().getId());
            ps.setInt(2,reservation.getId_event().getId());
            ps.setBoolean(3,reservation.isConfirmed());
            ps.setInt(4,reservation.getNb_place());
            ps.executeUpdate();

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


                Reservation r=new Reservation (rs.getInt("id"),SU.readById(rs.getInt(2)),SE.readById(rs.getInt(3)),rs.getBoolean("is_confirmed"),rs.getInt("nb_place"));
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

                r= new Reservation(rs.getInt(1),SU.readById(rs.getInt(2)),SE.readById(rs.getInt(3)),rs.getBoolean(4),rs.getInt(5));
                return r;
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }

        return r;
    }



}

