package service;


import entity.Categorie;
import utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCategorie implements IService <Categorie>{
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceCategorie() {

    }

    @Override
    public void insert(Categorie categorie) {

    }

    @Override
    public void delete(Categorie categorie) {

    }

    @Override
    public void update(Categorie categorie) {

    }

    @Override
    public List<Categorie> readAll() {
        List<Categorie> list = new ArrayList<>();
        Categorie cat = null;
        String requete = "select * from categorie";

        try {
            Statement st = conn.createStatement();

            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){
                cat= new Categorie();
                cat.setId(rs.getInt(1));
                cat.setNom(rs.getString(2));







                list.add(cat);



            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }

    @Override
    public Categorie readById(int id) {
        String query = "SELECT * FROM categorie WHERE id=?";
        Categorie cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new Categorie();
                cat.setId(id);
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    public Categorie readByName(String title) {
        String query = "SELECT * FROM categorie WHERE NomCategorie = ?";
        Categorie cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new Categorie();
                cat.setId(resultSet.getInt(1));
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }
}
