package com.example.PIDEV;


import entity.Blog;
import entity.CategorieBlog;
import entity.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBlogController implements Initializable {

    @FXML
    private TextArea txtcontenu;
    @FXML
    private ComboBox categorie;
    @FXML
    private TextField txttitre;

    @FXML
    private Button button_image;
    private File selectedFile = null;
    private Stage stage;

    @FXML
    private ImageView ImageView;

    @FXML
    private TextArea txtdesc;

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
        ServiceCategorieBlog scb = new ServiceCategorieBlog();
        List<CategorieBlog> l =scb.readAll();
        List<String> l1= new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {

            l1.add(l.get(i).getNom());

        }
        categorie.setItems(FXCollections.observableArrayList(l1));
        categorie.setValue(categorie.getItems().get(0));
        txttitre.getText();
        txtdesc.getText();
        txtcontenu.getText();
        txtcontenu.setWrapText(true);
        txtdesc.setWrapText(true);
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
            File newFile = new File("C:\\xampp\\htdocs\\img" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        ServiceCategorieBlog scb = new ServiceCategorieBlog();
        Blog b = new Blog(txttitre.getText(),txtdesc.getText(),txtcontenu.getText(),selectedFile.getName());
            LoggedInUser loggedInUser = new LoggedInUser();
        b.setAuthor(loggedInUser.getUser());
        b.setCat(scb.readByName(categorie.getValue().toString()));
        sb.insert(b);

            newsletterService.sendEmail("jasserbouzidi22@gmail.com");

        }
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageView.getScene().setRoot(root);
    }

    public void logoBack(MouseEvent mouseEvent) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageView.getScene().setRoot(root);
    }
}



