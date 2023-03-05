package com.example.PIDEV;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.LoggedInUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {
    LoggedInUser loggedInUser= new LoggedInUser();
    @FXML
    private ImageView image;
    @FXML
    private Label logout_btn;

    @FXML
    void account(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("LoggedIn.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoggedInController controller = loader.getController();
        controller.setUser(loggedInUser.getUser());
        logout_btn.getScene().setRoot(root);

    }

    @FXML
    void blogs(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logout_btn.getScene().setRoot(root);

    }

    @FXML
    void donations(MouseEvent event) {

    }

    @FXML
    void events(MouseEvent event) {

    }

    @FXML
    void logout(MouseEvent event) {
        loggedInUser.setUser(null);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logout_btn.getScene().setRoot(root);

    }

    @FXML
    void painting(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logout_btn.getScene().setRoot(root);


    }
    @FXML
    void reclamation(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("afficherMesReclamations.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logout_btn.getScene().setRoot(root);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (loggedInUser.getUser()==null)
            logout_btn.setVisible(false);



    }
}
