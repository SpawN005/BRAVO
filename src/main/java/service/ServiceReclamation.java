package service;

import entity.Reclamation;
import entity.TypeReclamation;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceReclamation implements IService<Reclamation>{
    ServiceUser su=new ServiceUser();
    private Connection conn;


    public ServiceReclamation() {
        conn=DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reclamation reclamation) {

      String requete = "SELECT * FROM Reclamation WHERE title= ? AND description= ? AND date_creation= ? AND etat=? AND ownerID=? AND date_treatment=? AND note=?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, reclamation.getTitle());
            pst.setString(2, reclamation.getDescription());
            pst.setDate(3, reclamation.getDate_creation());
            pst.setString(4, reclamation.getEtat());
            pst.setInt(5, reclamation.getOwnerID().getId());
            pst.setDate(6, reclamation.getDate_treatment());
            pst.setInt(7, reclamation.getNote());

            ResultSet rs =pst.executeQuery();
            if (rs.next()) {
                System.out.println("desole, ces valeurs sont deja existantes");
            } else {
                // des valeurs non existantes, vous pouvez procéder à l'insertion
                String requete2 = "insert into Reclamation(title,description,date_creation,etat,ownerID,date_treatment,note,typereclamation) values(?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(requete2);

                ps.setString(1, reclamation.getTitle());
                ps.setString(2, reclamation.getDescription());
                ps.setDate(3, reclamation.getDate_creation());
                ps.setString(4, reclamation.getEtat());
                ps.setInt(5, reclamation.getOwnerID().getId());
                ps.setDate(6, reclamation.getDate_treatment());
                ps.setInt(7, reclamation.getNote());
                ps.setInt(8, reclamation.getTypereclamation().getId());

                ps.executeUpdate();
                System.out.println("Reclamation Ajoutée");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Reclamation reclamation) {
        String req="DELETE FROM `Reclamation` WHERE id=?";
        ServiceTypeReclamation str = new ServiceTypeReclamation();
        TypeReclamation tr = new TypeReclamation(reclamation.getId());
        str.delete(tr);
        try {

            PreparedStatement pst =  conn.prepareStatement(req);
            pst.setInt(1, reclamation.getId());

            pst.executeUpdate();
            System.out.println("une reclamation existante a été supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete2(Integer r) {
        try {
            String requete = "delete from `reclamation` where id=?";
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, r);

            ps.executeUpdate();
            System.out.println("une reclamation existante a été supprimé selon id ");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void update(Reclamation reclamation) {
        String requete = "UPDATE Reclamation SET title=?, description=?,date_creation=?, etat=?,  date_treatment=?,note=? WHERE id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, reclamation.getTitle());
            ps.setString(2, reclamation.getDescription());
            ps.setDate(3, reclamation.getDate_creation());
            ps.setString(4, reclamation.getEtat());
           // ps.setInt(5, reclamation.getOwnerID().getId());
            ps.setDate(5, reclamation.getDate_treatment());
            ps.setInt(6,reclamation.getNote());
            ps.setInt(7,reclamation.getId());

            ps.executeUpdate();
            System.out.println("une reclamation existante a été mise à jour ");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void update2(Reclamation r, int id) {

        try {
            String requete =  "UPDATE Reclamation SET title=?, description=?,date_creation=?, etat=?, ownerID=?, date_treatment=?,note=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.setString(1, r.getTitle());
            ps.setString(2,r.getDescription() );
            ps.setDate(3, r.getDate_creation());
            ps.setString(4, r.getEtat());
            ps.setInt(5, r.getOwnerID().getId());
            ps.setDate(6, r.getDate_treatment());
            ps.setInt(7,r.getNote());
            ps.setInt(8,r.getId() );

            ps.executeUpdate();
            System.out.println("une reclamation existante a été mise à jour selon id ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public List<Reclamation> readAll() {
        List<Reclamation> list=new ArrayList<>();
        String requete="select * from Reclamation";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Reclamation r=new Reclamation(rs.getInt("id"), rs.getString("title"),
                        rs.getString("description"), rs.getDate("date_creation"),
                        rs.getString("etat"),su.readById(rs.getInt("ownerID")),rs.getDate("date_treatment"),rs.getInt("note"));
                list.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Reclamation readById(int id) {
        String requete = "SELECT * from Reclamation WHERE id="+id;
        Reclamation r = new Reclamation();

        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.executeQuery(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setDate_creation(rs.getDate("date_creation"));
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(id));
                r.setDate_treatment(rs.getDate("date_treatment"));
                r.setNote(rs.getInt("note"));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return r;
    }
    public List<Reclamation> RechercheTitle(String title) {
        String requete = "SELECT * from Reclamation where title like '%" + title + "%'";
        List<Reclamation> list = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setDate_creation(rs.getDate("date_creation"));
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(rs.getInt("ownerID")));
               // r.setDate_treatment(rs.getDate("date_treatment"));
                if (rs.getDate("date_treatment") != null) {
                    java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    r.setDate_treatment(date_sql);
                } else {
                    r.setDate_treatment(rs.getDate("date_treatment"));
                }
                r.setNote(rs.getInt("note"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    public List<Reclamation> RechercheReclamations(String type, String valeur) {
        List<Reclamation> list = new ArrayList<Reclamation>();
        String requete = null;
        try {
            if (type.equals("title")) {
                requete = "SELECT * from Reclamation where title like '%" + valeur + "%'";
            } else if (type.equals("description")) {
                requete = "SELECT * from Reclamation where description like '%" + valeur + "%'";
            } else if (type.equals("etat")) {
                requete = "SELECT * from Reclamation where etat like '%" + valeur + "%'";
            } else if (type.equals("Tout")) {
                requete = "SELECT * from Reclamation where title like '%" + valeur + "%' or description like '%" + valeur + "%' or etat like '%" + valeur + "%' ";
            }

            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                //r.setDate_creation(rs.getDate("date_creation"));
                if (rs.getDate("date_creation") != null) {
                    java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    r.setDate_treatment(date_sql);
                } else {
                    r.setDate_treatment(rs.getDate("date_treatment"));
                }
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(rs.getInt("ownerID")));

                //r.setDate_treatment(rs.getDate("date_treatment"));
                if (rs.getDate("date_treatment") != null) {
                    java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    r.setDate_treatment(date_sql);
                } else {
                    r.setDate_treatment(rs.getDate("date_treatment"));
                }
                r.setNote(rs.getInt("note"));
                list.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }
    public List<Reclamation> AfficherListeReclamationASC() {
        String requete= "SELECT * FROM `Reclamation` ORDER BY date_creation ASC";
        //Reclamation r = new Reclamation();
        List<Reclamation> list = new ArrayList();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setDate_creation(rs.getDate("date_creation"));
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(rs.getInt("ownerID")));
                r.setDate_treatment(rs.getDate("date_treatment"));
                r.setNote(rs.getInt("note"));
                list.add(r);

            }
        } catch (SQLException ex) {
            System.out.println("erreur d'affichage");
        }

        return list;
    }

    public List<Reclamation> AfficherListeReclamationDESC() {
        String requete= "SELECT * FROM `Reclamation` ORDER BY date_creation DESC";
        List<Reclamation> list = new ArrayList();
       // Reclamation r = new Reclamation();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
             while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setDate_creation(rs.getDate("date_creation"));
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(rs.getInt("ownerID")));
                r.setDate_treatment(rs.getDate("date_treatment"));
                r.setNote(rs.getInt("note"));
                list.add(r);

            }
        } catch (SQLException ex) {
            System.out.println("erreur d'affichage");
        }

        return list;
    }
    public List<Reclamation> AfficherListeReclamationByownerId(int ownerid) {
        String requete = "SELECT * from Reclamation where ownerID="+ownerid;
        List<Reclamation> list = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setDate_creation(rs.getDate("date_creation"));
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(rs.getInt("ownerID")));
                // r.setDate_treatment(rs.getDate("date_treatment"));
                if (rs.getDate("date_treatment") != null) {
                    java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    r.setDate_treatment(date_sql);
                } else {
                    r.setDate_treatment(rs.getDate("date_treatment"));
                }
                r.setNote(rs.getInt("note"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    public List<Reclamation> rechercheReclamationsOwnerId(String type, String valeur,int ownerId) {
        List<Reclamation> list = new ArrayList<Reclamation>();
        String requete = null;
        try {
            if (type.equals("title")) {
                requete = "SELECT * from Reclamation where title like '%" + valeur + "%' and ownerId=" + ownerId + ";";
            } else if (type.equals("description")) {
                requete = "SELECT * from Reclamation where description like '%" + valeur + "%'and ownerId=" + ownerId + ";";
            } else if (type.equals("etat")) {
                requete = "SELECT * from Reclamation where etat like '%" + valeur + "%'and ownerId=" + ownerId + ";";
            } else if (type.equals("Tout")) {
                requete = "SELECT * from Reclamation where title like '%" + valeur + "%' or description like '%" + valeur + "%' or etat like '%" + valeur + "%'and ownerId=" + ownerId + "; ";
            }

            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                //r.setDate_creation(rs.getDate("date_creation"));
                if (rs.getDate("date_creation") != null) {
                    java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    r.setDate_treatment(date_sql);
                } else {
                    r.setDate_treatment(rs.getDate("date_treatment"));
                }
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(rs.getInt("ownerID")));

                //r.setDate_treatment(rs.getDate("date_treatment"));
                if (rs.getDate("date_treatment") != null) {
                    java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    r.setDate_treatment(date_sql);
                } else {
                    r.setDate_treatment(rs.getDate("date_treatment"));
                }
                r.setNote(rs.getInt("note"));
                list.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }
    public Reclamation afficherLastRec() {
        String requete= "SELECT * FROM reclamation ORDER BY id DESC LIMIT 1;";

        Reclamation  r = new Reclamation();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setDate_creation(rs.getDate("date_creation"));
                r.setEtat(rs.getString("etat"));
                r.setOwnerID(su.readById(rs.getInt("ownerID")));
                r.setDate_treatment(rs.getDate("date_treatment"));
                r.setNote(rs.getInt("note"));


            }
        } catch (SQLException ex) {
            System.out.println("erreur d'affichage");
        }

        return r;
    }
    public ArrayList<TypeReclamation> afficherTypes() {
        String requete= "SELECT * FROM typereclamation ";
        ArrayList<TypeReclamation> list = new ArrayList<>();

        TypeReclamation  r ;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                r = new TypeReclamation();
                r.setId(rs.getInt("id"));
                r.setTypeReclamation(rs.getString("typeReclamation"));

                list.add(r);

            }
        } catch (SQLException ex) {
            System.out.println("erreur d'affichage");
        }

        return list;
    }
    public TypeReclamation readByName(String title) {
        String query = "SELECT * FROM typereclamation WHERE typeReclamation = ?";
        TypeReclamation tr = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tr = new TypeReclamation();
                tr.setId(resultSet.getInt(1));
                tr.setTypeReclamation(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tr;
    }

}


