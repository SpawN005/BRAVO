package service;

import entity.*;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDons implements IService<Dons> {
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceDons() {

    }

    @Override
    public void insert(Dons dons) {
        String requete = "insert into Donation (title,description,date_creation,date_expiration,amount,owner,categorie ) values(?,?,?,?,?,?,?)";

        try {
      ;
            PreparedStatement pstmt = this.conn.prepareStatement(requete);
            pstmt.setString(1, dons.getTitle());
            pstmt.setString(2, dons.getDescription());
            pstmt.setDate(3, java.sql.Date.valueOf(dons.getDate_creation()));
            pstmt.setDate(4, java.sql.Date.valueOf(dons.getDate_expiration()));
            pstmt.setInt(5, dons.getAmount());
            pstmt.setInt(6, dons.getOwner().getId());
            pstmt.setInt(7, dons.getCat().getId());


            pstmt.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public void delete(Dons dons) {
        ServiceDonsUser sdu = new ServiceDonsUser();
        ServiceUser su = new ServiceUser();
        sdu.deleteByDonation(dons);
        String requete = "delete from `donation` where `id`="+dons.getId();
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public void update(Dons dons) {
        String requete = "UPDATE donation SET title =?,description=?,date_creation=?,date_expiration=?,amount=?,categorie=? WHERE id=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(requete);
            pstmt.setString(1, dons.getTitle());
            pstmt.setString(2, dons.getDescription());
            pstmt.setDate(3, java.sql.Date.valueOf(dons.getDate_creation()));
            pstmt.setDate(4, java.sql.Date.valueOf(dons.getDate_expiration()));
            pstmt.setInt(5, dons.getAmount());
            pstmt.setInt(6, dons.getCat().getId());
            pstmt.setInt(7, dons.getId());

            pstmt.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
    }

    @Override
    public List<Dons> readAll() {
        List<Dons> list = new ArrayList();
        String requete = "select * from donation";
        ServiceUser su= new ServiceUser();
        ServiceDons sd = new ServiceDons();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                Dons o= new Dons(rs.getInt("id"), rs.getString("title"),  rs.getString("description"), rs.getTimestamp("date_creation").toLocalDateTime().toLocalDate(), rs.getTimestamp("date_expiration").toLocalDateTime().toLocalDate(),  rs.getInt("amount"), su.readById(rs.getInt("owner")),sd.readCatById(rs.getInt("categorie")));
                list.add(o);
            }
        } catch (SQLException var6) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

        return list;
    }

    @Override
    public Dons readById(int id) {
        String requete = "select * from `donation` where `id`=?";
        Dons o =new Dons();
        ServiceUser su= new ServiceUser();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){  o.setId(id);
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setDate_creation(rs.getTimestamp("date_creation").toLocalDateTime().toLocalDate());
                o.setDate_expiration(rs.getTimestamp("date_expiration").toLocalDateTime().toLocalDate());
                o.setAmount(rs.getInt("amount"));
                o.setOwner(su.readById(rs.getInt("owner")));}


        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return o;
    }
    public List<Dons> searchByTitle(String title) {
        List<Dons> searchResults = new ArrayList<>();
        String requete = "SELECT * FROM donation WHERE title LIKE ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(requete);
            pstmt.setString(1, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();
            ServiceUser su = new ServiceUser();
            ServiceDons sd = new ServiceDons();
            while (rs.next()) {
                Dons dons = new Dons(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("date_creation").toLocalDateTime().toLocalDate(),
                        rs.getTimestamp("date_expiration").toLocalDateTime().toLocalDate(),
                        rs.getInt("amount"),
                        su.readById(rs.getInt("owner")),
                        sd.readCatById(rs.getInt("categorie"))
                );
                searchResults.add(dons);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDons.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResults;
    }




    public void updateAmount(DonsUser dons) {
        String requete = "UPDATE donation SET amount= amount - ? WHERE id= ?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(requete);
            pstmt.setInt(1, dons.getAmout());
            pstmt.setInt(2, dons.getId_don());
            pstmt.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
    }
    public int calculateTotalAmountByTitle(String title) {
        int totalAmount = 0;
        String donationQuery = "SELECT id FROM donation WHERE title = ?";
        String donationAmountQuery = "SELECT SUM(amount) as total FROM donater WHERE id_donation = ?";

        try (PreparedStatement pstmtDonation = conn.prepareStatement(donationQuery);
             PreparedStatement pstmtDonationAmount = conn.prepareStatement(donationAmountQuery)) {

            // Get the id of the donation with the given title
            pstmtDonation.setString(1, title);
            ResultSet rsDonation = pstmtDonation.executeQuery();

            if (rsDonation.next()) {
                int donationId = rsDonation.getInt("id");

                // Get the sum of amounts donated for the donation with the given id
                pstmtDonationAmount.setInt(1, donationId);
                ResultSet rsDonationAmount = pstmtDonationAmount.executeQuery();

                if (rsDonationAmount.next()) {
                    totalAmount = rsDonationAmount.getInt("total");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDons.class.getName()).log(Level.SEVERE, null, ex);
        }

        return totalAmount;
    }

    public List<CategorieDon> readCat() {
        List<CategorieDon> list = new ArrayList<>();
        CategorieDon cat = null;
        String requete = "select * from categorie_donation";

        try {
            Statement st = conn.createStatement();

            ResultSet rs=st.executeQuery(requete);
            while (rs.next()){
                cat= new CategorieDon();
                cat.setId(rs.getInt(1));
                cat.setNom(rs.getString(2));







                list.add(cat);



            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }


    public CategorieDon readCatById(int id) {
        String query = "SELECT * FROM categorie_donation WHERE id=?";
        CategorieDon cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new CategorieDon();
                cat.setId(id);
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    public CategorieDon readCatByName(String title) {
        String query = "SELECT * FROM categorie_donation WHERE nomCategorie = ?";
        CategorieDon cat = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cat = new CategorieDon();
                cat.setId(resultSet.getInt(1));
                cat.setNom(resultSet.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }


    public List<Dons>  readByUser(User user) {
        String requete = "select * from `donation` where `owner`=?";
        Dons o =new Dons();
        List<Dons> list = new ArrayList();
        ServiceUser su= new ServiceUser();
        ServiceDons sd= new ServiceDons();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,user.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){  o.setId(rs.getInt("id"));
                o.setTitle(rs.getString("title"));
                o.setDescription(rs.getString("description"));
                o.setDate_creation(rs.getTimestamp("date_creation").toLocalDateTime().toLocalDate());
                o.setDate_expiration(rs.getTimestamp("date_expiration").toLocalDateTime().toLocalDate());
                o.setAmount(rs.getInt("amount"));
                o.setOwner(su.readById(rs.getInt("owner")));
            o.setCat(sd.readCatById(rs.getInt("categorie")));
            list.add(o);
            }

        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }
        return list;
    }
}


