package service;


import entity.CategorieBlog;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCategorieBlog implements IService<CategorieBlog>{
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceCategorieBlog() {

    }



    @Override
    public void insert(CategorieBlog categorieBlog) {

    }

    @Override
    public void delete(CategorieBlog categorieBlog) {

    }

    @Override
    public void update(CategorieBlog categorieBlog) {

    }

    @Override
    public List<CategorieBlog> readAll() {
        List<CategorieBlog> list = new ArrayList<>();
        CategorieBlog cat = null;
        String requete = "select * from categorie_blog";

        try {
            Statement st = conn.createStatement();

            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){
                cat= new CategorieBlog();
                cat.setId(rs.getInt(1));
                cat.setNom(rs.getString(2));







                list.add(cat);



            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }

    @Override
    public CategorieBlog readById(int id) {
        String query = "SELECT * FROM categorie_blog WHERE id=?";
        CategorieBlog cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new CategorieBlog();
                cat.setId(id);
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    public CategorieBlog readByName(String title) {
        String query = "SELECT * FROM categorie_blog WHERE nom_categorie = ?";
        CategorieBlog cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new CategorieBlog();
                cat.setId(resultSet.getInt(1));
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

}
