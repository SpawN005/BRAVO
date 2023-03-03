package service;

import entity.NoteOeuvre;
import entity.Oeuvre;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceNoteOeuvre implements IService<NoteOeuvre> {
    ServiceOeuvre so=new ServiceOeuvre();
    public ServiceNoteOeuvre() {
    }
    private Connection conn = DataSource.getInstance().getCnx();

    @Override
    public void insert(NoteOeuvre noteOeuvre) {
        String requete = "REPLACE INTO noteoeuvre (note, id_oeuvre, id_user) VALUES (?, ?, ?);";
        try {
            PreparedStatement st = conn.prepareStatement(requete);
            st.setDouble(1, noteOeuvre.getNote());
            st.setInt(2, noteOeuvre.getOeuvre().getId());
            st.setInt(3, noteOeuvre.getUser());
            st.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }


    }

    @Override
    public void delete(NoteOeuvre noteOeuvre) {
        String requete = "delete from noteoeuvre where id="+noteOeuvre.getId();

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public void update(NoteOeuvre noteOeuvre) {

    }

    @Override
    public List<NoteOeuvre> readAll() {
        return null;
    }

    @Override
    public NoteOeuvre readById(int id) {
        return null;
    }
    public float readAvg(Oeuvre oeuvre) {

        String requete = "select AVG(note) from noteoeuvre where id_oeuvre= ?";
        float note=0;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,oeuvre.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                note= rs.getFloat(1);
            }

        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return note;
    }
}
