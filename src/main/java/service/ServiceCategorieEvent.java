package service;


import entity.CategorieEvent;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCategorieEvent implements IService<CategorieEvent>{
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceCategorieEvent() {

    }
    @Override
    public void insert(CategorieEvent categorieEvent) {

    }

    @Override
    public void delete(CategorieEvent categorieEvent) {

    }

    @Override
    public void update(CategorieEvent categorieEvent) {

    }

    @Override
    public List<CategorieEvent> readAll() {
        List<CategorieEvent> list = new ArrayList<>();
        CategorieEvent cat = null;
        String requete = "select * from event_categorie";

        try {
            Statement st = conn.createStatement();

            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){
                cat= new CategorieEvent();
                cat.setId(rs.getInt(1));
                cat.setNom(rs.getString(2));







                list.add(cat);



            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }

    @Override
    public CategorieEvent readById(int id) {
        String query = "SELECT * FROM event_categorie WHERE id=?";
        CategorieEvent cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new CategorieEvent();
                cat.setId(id);
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    public CategorieEvent readByName(String title) {
        String query = "SELECT * FROM event_categorie WHERE nom = ?";
        CategorieEvent cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new CategorieEvent();
                cat.setId(resultSet.getInt(1));
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

}
