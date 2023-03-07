package com.example.PIDEV;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.LoggedInUser;
import service.MailService;
import service.UserManagement;
import utils.DataSource;

public class SignInController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private TextField pswField;

    @FXML
    private Button signinBtn;

    @FXML
    private Label fnamelabel1;

    @FXML
    private Label lnamelabel;
    
     @FXML
    private Label forgotlabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
    }

public void signIn() throws IOException {
    String email = emailField.getText();
    String password = pswField.getText();
    
    if (email.isEmpty() || password.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing credentials");
        alert.setContentText("Please enter both email and password.");
        alert.showAndWait();
        return;
    }

    Connection conn = DataSource.getInstance().getCnx();
    UserManagement userManager = new UserManagement(conn);
    User user = null;   

    try {
        user = userManager.getUserByEmailAndPassword(email, password);

    } catch (SQLException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Database Error");
        alert.setContentText("There was a problem accessing the database. Please try again later.");
        alert.showAndWait();
        return;
    }

    if (user == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid credentials");
        alert.setContentText("The email or password is incorrect.");
        alert.showAndWait();
    }
    else if (user.getChecker().equals("banned")) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Account Suspended");
        alert.setContentText("Your account has been banned for violating our user agreement policy.\nFor more informations, contact us on jasser.bouzidi@esprit.tn ");
        alert.showAndWait();


    }
    else {
        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setUser(user);
        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Welcome");
        successAlert.setHeaderText("Welcome " + loggedInUser.getUser().getFirstName() + " " + loggedInUser.getUser().getLastName() + "!");
        successAlert.setContentText("You have successfully logged in. Your role is : " + loggedInUser.getUser().getRole()+ ".");
        successAlert.showAndWait();
        FXMLLoader loader = null;
        Parent root= null;

       if (!(Objects.equals(loggedInUser.getUser().getRole(), "admin")))
       {
            loader = new FXMLLoader(getClass().getResource("Feed.fxml"));
           try {
               root = loader.load();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           Scene scene = new Scene(root);
           Stage newStage = new Stage();
           newStage.setScene(scene);
           newStage.show();
           Stage currentStage = (Stage) signinBtn.getScene().getWindow();
           currentStage.close();

       }else {
            loader=new FXMLLoader(getClass().getResource("LoggedIn.fxml"));

           try {
               root = loader.load();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           LoggedInController controller = loader.getController();
           controller.setUser(user);
           Scene scene = new Scene(root);
           Stage newStage = new Stage();
           newStage.setScene(scene);
           newStage.show();
           Stage currentStage = (Stage) signinBtn.getScene().getWindow();
           currentStage.close();

       }





        


        //Set the first name and last name labels


    }
}



public void handleForgotPassword() throws SQLException {
    String email = emailField.getText();

    if (email.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing credentials");
        alert.setContentText("Please enter your email.");
        alert.showAndWait();
        return;
    }

    Connection conn = DataSource.getInstance().getCnx();
    UserManagement userManager = new UserManagement(conn);
    User user = userManager.getUserByEmail(email);

    if (user == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid email");
        alert.setContentText("There is no user with this email address.");
        alert.showAndWait();
        return;
    }

    String newPassword = userManager.resetPassword(email); // reset the user's password

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText("Password sent");
    alert.setContentText("Your password has been sent to your email.");
    alert.showAndWait();

    MailService mailService = new MailService();
    //mailService.envoyer(email, newPassword); // send the new password to the user's email address
}


            }

