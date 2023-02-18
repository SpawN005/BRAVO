package service;

import entity.TypeReclamation;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.TypeReclamation;
public class ServiceTypeReclamation implements IService<TypeReclamation>{
    private Connection conn;

    public ServiceTypeReclamation() {
        conn=DataSource.getInstance().getCnx();	}

    public void insert(TypeReclamation t) {
        String requete = "SELECT * FROM TypeReclamation WHERE typeReclamation= ? ";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1,t.getTypeReclamation());

            ResultSet rs =pst.executeQuery();
            if (rs.next()) {
                System.out.println("desole, ce type de reclamation est deja existant");
            } else {
                // type non existant, vous pouvez procéder à l'insertion
                String requete2 = "insert into TypeReclamation (typeReclamation) Values (?)";
                PreparedStatement ps = conn.prepareStatement(requete2);
                ps.setString(1, t.getTypeReclamation());

                ps.executeUpdate();
                System.out.println("un nouveau type de reclamation Ajouté");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(TypeReclamation t) {
        String req="DELETE FROM `TypeReclamation` WHERE id=?";
        try {

            PreparedStatement pst =  conn.prepareStatement(req);
            pst.setInt(1,t.getId());

            pst.executeUpdate();
            System.out.println("un type de reclamation existant a été supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteType2(Integer r) {
        try {
            String requete = "delete from `TypeReclamation` where id=?";
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, r);

            ps.executeUpdate();
            System.out.println("un type de  reclamation existant a été supprimé selon id ");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(TypeReclamation typeReclamation) {
        String requete = "UPDATE TypeReclamation SET typeReclamation=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, typeReclamation.getTypeReclamation());
            ps.setInt(2, typeReclamation.getId());
            ps.executeUpdate();
            System.out.println("un type de  reclamation existant a été mis à jour ");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void update2(TypeReclamation typeReclamation,int id) {
        String requete = "UPDATE TypeReclamation SET typeReclamation=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, "service");
            ps.setInt(2,id);
            ps.executeUpdate();
            System.out.println("un type de  reclamation existant a été mis à jour ");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<TypeReclamation> readAll() {
        List<TypeReclamation> list=new ArrayList<>();
        String requete="select * from TypeReclamation";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                TypeReclamation r=new TypeReclamation(rs.getInt("id"), rs.getString("typeReclamation"));
                list.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public TypeReclamation readById(int id) {
        TypeReclamation Tr = new TypeReclamation();

        try {
            String requete = "SELECT * from TypeReclamation WHERE id="+id;
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.executeQuery(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Tr.setId(rs.getInt(1));
                Tr.setTypeReclamation(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return Tr;
    }
    public List<TypeReclamation> RechercheType(String typeReclamation) {
        String requete = "SELECT * from TypeReclamation where typeReclamation like '%" + typeReclamation + "%'";
        List<TypeReclamation> list = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                TypeReclamation Tr = new TypeReclamation();
                Tr.setId(rs.getInt("id"));
                Tr.setTypeReclamation(rs.getString("typeReclamation"));
                list.add(Tr);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    }


