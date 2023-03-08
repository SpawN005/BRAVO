package service;

import entity.Dons;
import entity.User;
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
        String requete = "insert into Donation (id,title,description,date_creation,date_expiration,amount,owner ) values ('" + dons.getId() + "','" + dons.getTitle() + "','"+ dons.getDescription()+"','"+ dons.getDate_creation()+"','"+dons.getDate_expiration()+"','"+dons.getAmount()+"','"+dons.getOwner()+"')";

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    @Override
    public void delete(Dons dons) {
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
        String requete = "UPDATE donation SET title =?,description=?,date_creation=?,date_expiration=?,amount=?,owner=? WHERE id=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(requete);
            pstmt.setString(1, dons.getTitle());
            pstmt.setString(2, dons.getDescription());
            pstmt.setDate(3, java.sql.Date.valueOf(dons.getDate_creation()));
            pstmt.setDate(4, java.sql.Date.valueOf(dons.getDate_expiration()));
            pstmt.setInt(5, dons.getAmount());
            pstmt.setString(6, dons.getOwner());
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

        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                Dons o= new Dons(rs.getInt("id"), rs.getString("title"),  rs.getString("description"), rs.getTimestamp("date_creation").toLocalDateTime().toLocalDate(), rs.getTimestamp("date_expiration").toLocalDateTime().toLocalDate(),  rs.getInt("amount"), rs.getString("owner"));
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
                o.setOwner(rs.getString("owner"));}


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

            while (rs.next()) {
                Dons dons = new Dons(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("date_creation").toLocalDateTime().toLocalDate(),
                        rs.getTimestamp("date_expiration").toLocalDateTime().toLocalDate(),
                        rs.getInt("amount"),
                        rs.getString("owner")
                );
                searchResults.add(dons);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDons.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResults;
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










}


