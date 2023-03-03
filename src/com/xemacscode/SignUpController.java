package com.xemacscode;

import entite.PasswordHasher;
import entite.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import service.ServicePersonne;
import utils.DataSource;

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
        
        RadioButton selectedRole = (RadioButton) tgrole.getSelectedToggle();
                        String role = selectedRole.getText();
        

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
        }else{
                    


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
        } catch (NumberFormatException e) {
            System.err.println("Error: Phone number must be a Number");
            Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Invalid Phone Number");
                    alert.setContentText("Phone number must be a Number. Please try again.");
                    alert.showAndWait();           
                    return;
           
        }
        Connection conn = DataSource.getInstance().getCnx();

        // create a new user object
        User user = new User(firstName, lastName, phone, email, role, password);
        ServicePersonne service = new ServicePersonne();


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
            String query = "INSERT INTO user (firstName, lastName, phoneNumber, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
                    PasswordHasher hasher = new PasswordHasher();

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
              statement.setString(5, hasher.hashPassword(user.getPassword()));
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
    }
}
         




}
