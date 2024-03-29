package com.example.PIDEV;

import entity.PasswordHasher;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import service.ServiceUser;
import utils.DataSource;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;

public class SignUpController implements Initializable{

  
    
        private Connection conn;


    @FXML
    private Button btn_SignUp;

    @FXML
    private RadioButton rbArtist;

    @FXML
    private RadioButton rbGuest;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfPhone;

    @FXML
    private ToggleGroup tgrole;
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        rbGuest.setSelected(true);
    }

    @FXML
    void SubmitSignUp(ActionEvent event) {
        // get the selected role
        String role;
        RadioButton selectedRole = (RadioButton) tgrole.getSelectedToggle();
                        if(selectedRole.getText().equalsIgnoreCase("artist"))
                        {
                             role="[\"ROLE_ARTISTE\"]";
                        }else {
                             role="[\"ROLE_CLIENT\"]";
                        }
        System.out.println(role);
        // get user input
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String phoneString = tfPhone.getText();
        String email = tfEmail.getText();
        String password = tfPassword.getText();

        // validate input
        if (firstName.isEmpty() || lastName.isEmpty() || phoneString.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Make sure you typed everything");
                    alert.showAndWait();           
                    return;
        }

        String regex = "^[a-zA-Z]+$";

        if (!firstName.matches(regex) || !lastName.matches(regex)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Invalid Input");
            alert.setContentText("The first name and last name fields can only \ncontain letters. Please try again.");
            alert.showAndWait();
            return;
        }
        
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText("Invalid Email");
    alert.setContentText("Please enter a valid email address.");
    alert.showAndWait();
    return;
}



        int phone;
        try {
            phone = Integer.parseInt(phoneString);
            if (phoneString.length() != 8) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: Phone number must be a Number");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Invalid Phone Number");
            alert.setContentText("Phone number must be a combination of 8 digits. \nPlease try again.");
            alert.showAndWait();
            return;

        }
        Connection conn = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Email already exists, show popup
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Email already in use");

                alert.setContentText("The email address you entered is already in use. \nPlease use a different email address.");
                alert.showAndWait();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // create a new user object
        User user = new User(firstName, lastName, phone, email, role, password);
        ServiceUser service = new ServiceUser();


        // insert the new user into the database
        insertPst(user);
        
        
                Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Sign up completed!");
        alert.setContentText("Welcome " + firstName + " " + lastName + "!");
        alert.showAndWait();
        
        
    }     
         public void insertPst(User user) {
             
             Connection conn = DataSource.getInstance().getCnx();

         
         
        try {
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
                throw new SQLException("User and role insert failed");
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
         




}
