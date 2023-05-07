/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PasswordHasher;
import entity.User;
import utils.DataSource;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wiemhjiri
 */
public class ServiceUser implements IService<User> {

    private Connection conn;

    public ServiceUser() {
        conn = DataSource.getInstance().getCnx();
    }


    @Override
    public void insert(User t) {
        String requete = "insert into user (first_name,last_name,phone,email,roles,PASSWORD) values "
                + "('" + t.getFirstName() + "','" + t.getLastName() + "'," + t.getPhoneNumber() + "'," + t.getEmail() + "'," + t.getRole() + "'," + t.getPassword()+")";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertPst(User user) {
        try  {
            conn.setAutoCommit(false); // start transaction

            // Insert new user and role
            String query = "INSERT INTO user (first_name, last_name, phone, email, password, roles) VALUES (?, ?, ?, ?, ?, ?)";
            PasswordHasher hasher = new PasswordHasher();

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, hasher.hashPassword(user.getPassword()).toString());
            statement.setString(6, user.getRole());
            int rows = statement.executeUpdate();
            if (rows != 1) {
                throw new SQLException  ("User and role insert failed");
            }

            // Get the generated id for the user
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                // Insert new role for user
                String roleQuery = "INSERT INTO role (role, user_id) VALUES (?, ?)";
                PreparedStatement roleStatement = conn.prepareStatement(roleQuery);
                roleStatement.setString(1, user.getRole());
                roleStatement.setInt(2, userId);
                int roleRows = roleStatement.executeUpdate();
                if (roleRows != 1) {
                    throw new SQLException("Role insert failed");
                }
            } else {
                throw new SQLException("User insert failed to return ID");
            }

            conn.commit(); // end transaction

        } catch (SQLException e) {
            System.err.println("Error inserting new user and role: " + e.getMessage());
            try {
                conn.rollback(); // undo changes
            } catch (SQLException ex) {
                System.err.println("Error rolling back transaction: " + ex.getMessage());
            }


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public User readById(int id) {
        String query = "SELECT * FROM user WHERE id=?";
        User user = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPhoneNumber(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("roles"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPhoneNumber(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("roles"));
                user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }



    @Override
    public void update(User t) {
        String requete = "UPDATE user SET first_name=?, last_name=?, phone=?, password=?, image=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(requete)) {
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setInt(3, t.getPhoneNumber());
            ps.setString(4, t.getPassword());
            ps.setString(5, t.getimage());
            System.out.println(t.getimage());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void delete(User t) {
        try {
            // Delete user's role(s)
            String deleteRoleQuery = "DELETE FROM role WHERE user_id = ?";
            PreparedStatement deleteRoleStatement = conn.prepareStatement(deleteRoleQuery);
            deleteRoleStatement.setInt(1, t.getId());
            deleteRoleStatement.executeUpdate();

            // Delete user
            String deleteUserQuery = "DELETE FROM user WHERE id = ?";
            PreparedStatement deleteUserStatement = conn.prepareStatement(deleteUserQuery);
            deleteUserStatement.setInt(1, t.getId());
            deleteUserStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    public boolean deleteUser(int userId) {
        boolean success = false;
        Servicerole servicerole = new Servicerole();
        servicerole.deleteRoleByUserId(userId);

        try {
            Connection conn = DataSource.getInstance().getCnx();

            String sql = "DELETE FROM user WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void deleteRoleByUserId(int user_Id) {
        try {
            String query = "DELETE FROM role WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, user_Id);
            statement.executeUpdate();
            System.out.println("Roles deleted successfully for user with id " + user_Id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void deleteUserByEmail(String email) throws SQLException {
        try {
            // Disable auto-commit mode to run the two statements in a transaction
            conn.setAutoCommit(false);

            // Delete the user's roles first
            try (PreparedStatement deleteRolesStmt = conn.prepareStatement("DELETE FROM role WHERE user_id = (SELECT id FROM user WHERE email = ?)")) {
                deleteRolesStmt.setString(1, email);
                int numDeletedRoles = deleteRolesStmt.executeUpdate();
                System.out.println(numDeletedRoles + " roles deleted for user with email " + email);
            }

            // Delete the user itself
            try (PreparedStatement deleteUserStmt = conn.prepareStatement("DELETE FROM user WHERE email = ?")) {
                deleteUserStmt.setString(1, email);
                int numDeletedUsers = deleteUserStmt.executeUpdate();
                System.out.println(numDeletedUsers + " users deleted with email " + email);
            }

            // Commit the transaction
            conn.commit();
        } catch (SQLException ex) {
            // Rollback the transaction if there was an error
            conn.rollback();
            ex.printStackTrace();
        }
    }



    public List<User> RandomArtists()  {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user where roles='[\"ROLE_ARTISTE\"]' ORDER BY RAND() LIMIT 3 ;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_Name"));
                user.setLastName(resultSet.getString("last_Name"));
                user.setPhoneNumber(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("roles"));
                user.setPassword(resultSet.getString("password"));
                user.setimage(resultSet.getString("image"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;

    }
    public void blockUser(User user) {
        try {
            // Create a prepared statement
            String sql = "UPDATE user SET banned = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters
            statement.setInt(1, 1);
            statement.setInt(2, user.getId());

            // Execute the update statement
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " has been blocked.");
            } else {
                System.out.println("Failed to block user " + user.getFirstName() + " " + user.getLastName() + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void VerifyUser(User user) {
        try {
            // Create a prepared statement
            String sql = "UPDATE user SET is_Verified = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameters
            statement.setInt(1, 1);
            statement.setInt(2, user.getId());

            // Execute the update statement
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " has been Verified.");
            } else {
                System.out.println("Failed to verify user " + user.getFirstName() + " " + user.getLastName() + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}