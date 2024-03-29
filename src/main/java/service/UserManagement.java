package service;

import entity.PasswordHasher;
import entity.User;
import utils.DataSource;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserManagement {
    private Connection conn = DataSource.getInstance().getCnx();

    public UserManagement(Connection conn) {
        this.conn = conn;
    }

    public UserManagement() {conn = DataSource.getInstance().getCnx();

    }


    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        User user = null;
        PasswordHasher hasher = new PasswordHasher();

        String query = "SELECT * FROM user WHERE email = ? AND password= ? ";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, hasher.hashPassword(password).toString());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {

                    user = new User(
                            rs.getInt("id"),
                            rs.getString("first_Name"),
                            rs.getString("last_Name"),
                            rs.getInt("phone"),
                            rs.getString("email"),
                            rs.getString("roles"),
                            rs.getString("password"),
                            rs.getString("image"),
                            rs.getInt("banned"),
                            rs.getInt("is_verified")
                    );
                }




            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    
    
    public User getUserByEmail(String email) throws SQLException {
    String query = "SELECT * FROM user WHERE email=?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, email);
    ResultSet result = statement.executeQuery();

    if (result.next()) {
        User user = new User(
            result.getInt("id"),
            result.getString("first_name"),
            result.getString("last_name"),
            result.getInt("phone"),
            result.getString("email"),
            result.getString("password"),
            result.getString("roles")
                
        );
        return user;
    } else {
        return null;
    }
}
public String resetPassword(String email) throws SQLException, NoSuchAlgorithmException {
    // Generate a new password
    String newPassword = generateRandomPassword();

    // Hash the new password
    PasswordHasher hasher = new PasswordHasher();
    String hashedPassword = hasher.hashPassword(newPassword).toString();

    // Update the user's password in the database
    String query = "UPDATE user SET password = ? WHERE email = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setString(1, hashedPassword);
        statement.setString(2, email);
        statement.executeUpdate();
    }

    // Send the new password to the user's email
    MailService mailService = new MailService();
    mailService.envoyer(email, newPassword);

    // Return the new password
    return newPassword;
}


            private String generateRandomPassword() {
                // Generate a random alphanumeric string of length 8
                int length = 8;
                String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    int index = (int) (Math.random() * allowedChars.length());
                    sb.append(allowedChars.charAt(index));
                }
                return sb.toString();
            }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {


            String query = "SELECT id, first_name, last_name, phone, email, roles FROM user";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("email") + " " + rs.getInt("phone") + " " +  rs.getString("roles") );
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("phone"),
                        rs.getString("roles")

                );
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }


    public List<User> getUsersByRole(String role) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : getAllUsers()) {
            if (user.getRole().equals(role)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
}


