package com.example.PIDEV;


import entity.Blog;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.NewsletterService;
import utils.DataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import service.ServiceBlog;
import service.ServiceUser;

import static java.lang.Integer.parseInt;
import static javafx.fxml.FXMLLoader.load;

public class AddBlogController implements Initializable {

    @FXML
    private TextField txtcontenu;

    @FXML
    private TextField txttitre;

    @FXML
    private Button button_image;
    private File selectedFile = null;
    private Stage stage;

    @FXML
    private ImageView ImageView;

    @FXML
    private TextField txtdesc;

    @FXML
    private Button buttonadd;


    ServiceBlog sb = new ServiceBlog();
    ServiceUser su = new ServiceUser();

    User u = new User();
    private Connection cnx = null;
    Blog b = new Blog();
    NewsletterService newsletterService = new NewsletterService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txttitre.getText();
        txtdesc.getText();
        txtcontenu.getText();
        newsletterService = new NewsletterService();
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
    void browse(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload an image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            button_image.setText(selectedFile.getName());


            ImageView.setImage(new Image("file:" + selectedFile));
            ImageView.setFitWidth(200);
            ImageView.setFitHeight(150);

        }
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
        }else if (selectedFile==null){
            showAlert("Please enter an image",false);

        }else {
            File newFile = new File("src/main/resources/com/example/PIDEV/assets/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        Blog b = new Blog(txttitre.getText(),txtdesc.getText(),txtcontenu.getText(),selectedFile.getName());
        b.setAuthor(su.readById(30));
        System.out.println(b);
        sb.insert(b);

            newsletterService.sendEmail("tasnim.benhamouda@esprit.tn");

        }
    }
}



