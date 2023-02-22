package com.example.PIDEV;


import entity.Blog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceBlog;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class AddBlogController implements Initializable {

    @FXML
    private TextField txtcontenu;

    @FXML
    private Button buttoncancel;

    @FXML
    private TextField txttitre;

    @FXML
    private TextField txtdesc;

    @FXML
    private Button buttonadd;

    ServiceBlog sb = new ServiceBlog();
    ServiceUser su = new ServiceUser();
    private Connection cnx = null;
    Blog b = new Blog();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txttitre.getText();
        txtdesc.getText();
        txtcontenu.getText();
    }

    private void showAlert(String message ,boolean b) {
        Alert alert;
        if (b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);


        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    @FXML
    void ajouter(ActionEvent event) {
        String text = txttitre.getText();

        if (text.isEmpty()){
            showAlert("Please entre a title", false);
        }else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your title must contain only letters and spaces",false);
        }
        else if (txtdesc.getText().isEmpty()){
            showAlert("Please enter a description",false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your description must contain only letters and spaces",false);
        }

        Blog b = new Blog(txttitre.getText(),txtdesc.getText(),txtcontenu.getText());
        b.setAuthor(su.readById(26));
        sb.insert(b);
  }

    @FXML
    void annuler() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }








}



