package service;

import entity.Event;
import entity.NoteEvent;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceNoteEvent implements IService<NoteEvent> {
    Connection conn;

ServiceEvent SE= new ServiceEvent();
    public ServiceNoteEvent() {

        conn= DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(NoteEvent noteEvent) {
        String requete="INSERT INTO note(note,id_event) values(?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(requete);

            ps.setInt(1,noteEvent.getNote());
            ps.setInt(2,noteEvent.getId_event().getId() );


            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    @Override
    public void delete(NoteEvent noteEvent) {
        String requete = "delete from note where id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, noteEvent.getId());
            ps.executeUpdate();
        } catch (SQLException e ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);
        }

    }

    @Override
    public void update(NoteEvent noteEvent) {
        String query = "UPDATE note SET note=?, id_event=? WHERE id=?";
        try {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,noteEvent.getNote());
            ps.setInt(2,noteEvent.getId_event().getId());
            ps.setInt(3,noteEvent.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, e);

        }
    }

    public void deleteById(NoteEvent noteEvent , int id_m) {

        String requete = "delete from note where id=" + id_m;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.executeUpdate();
            System.out.println("La note a été supprimée de la base de données.");

        } catch (SQLException e1) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String) null, e1);
            System.out.println("Une erreur s'est produite lors de la suppression de la note de la base de données  ");

        }


    }
    public void updateById(NoteEvent noteEvent, int id_m) {

        String query="UPDATE note SET note=?, id_event=? where id="+id_m;

        try {

            PreparedStatement ps=conn.prepareStatement(query);
            ps.setInt(1,noteEvent.getNote());
            ps.setInt(2,noteEvent.getId_event().getId());

            ps.executeUpdate(query);
            System.out.println("La note a été mise à jour.");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("La note n'a pas été mise à jour.");


        }}



    @Override
    public List<NoteEvent> readAll() {
        List<NoteEvent> list=new ArrayList<>();
        String requete="select* from note";

        try {
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(requete);
            while(rs.next()) {
              /*  NoteEvent NE= new NoteEvent();*/

                NoteEvent c=new NoteEvent (rs.getInt("id"),rs.getInt(2),SE.readById(rs.getInt("id_event")));
                list.add(c);
            }
        } catch (SQLException e) {

            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }

        return list;
    }


    @Override
    public NoteEvent readById(int id) {
        NoteEvent NE  = new NoteEvent();
        String requete="select * from note where id="+id;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ResultSet rs=ps.executeQuery(requete);

            if(rs.next()) {



                NE.setId(rs.getInt(1));
                NE.setNote(rs.getInt(2));
                NE.setId_event(SE.readById(id));
                return NE;
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }

        return NE;
    }
}
