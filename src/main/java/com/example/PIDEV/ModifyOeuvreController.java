package com.example.PIDEV;

import entity.Categorie;
import entity.Oeuvre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.LoggedInUser;
import service.ServiceCategorie;
import service.ServiceOeuvre;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyOeuvreController implements Initializable {
    LoggedInUser loggedInUser= new LoggedInUser();
    @FXML
    private ImageView ImageView;

    @FXML
    private Button cancelButton;

    @FXML
    private MenuButton categorie;

    @FXML
    private TextArea description;

    @FXML
    private Button submitButton;

    @FXML
    private TextField title;

    @FXML
    private Button uploadImage;
    private Stage stage;
    private File selectedFile = null;
    MenuItem selectedMenuItem = null;
    private Oeuvre oeuvre;
    private ServiceOeuvre so = new ServiceOeuvre();
    public   void SetOeuvre(Oeuvre o){
        ServiceCategorie sc = new ServiceCategorie();
        List<Categorie> l = sc.readAll();
        categorie.setText(o.getCategory().getNom());
        for(Categorie c : l){
            MenuItem menuItem = new MenuItem(c.getNom());
            categorie.getItems().add(menuItem);
            menuItem.setOnAction(event -> {
                selectedMenuItem = menuItem;
                categorie.setText(menuItem.getText());
            });
        }
        description.setText(o.getDescription());
        title.setText(o.getTitle());
        categorie.setText(o.getCategory().getNom());
        uploadImage.setText(o.getUrl());
        ImageView.setImage(new Image("file:C:/xampp/htdocs/img/"+o.getUrl()));

        selectedFile = new File("C:/xampp/htdocs/img/" + o.getUrl());
        oeuvre = new Oeuvre(o.getId(),o.getTitle(),o.getDescription(),o.getOwner(),o.getCategory(),o.getUrl());

    }
    @FXML
    void browse() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload an image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            uploadImage.setText(selectedFile.getName());


            ImageView.setImage(new Image("file:"+selectedFile));
            ImageView.setFitWidth(200);
            ImageView.setFitHeight(150);


        }

    }

    @FXML
    void cancel() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.getScene().setRoot(root);
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
    private void submit()  {
        String text = title.getText();

        if (text.isEmpty()) {
            showAlert("Please enter a title",false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your title must contain only letters and spaces",false);
        }
        else if (description.getText().isEmpty()) {
            showAlert("Please enter a description",false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your description must contain only letters and spaces",false);
        }
        else if (selectedFile==null && oeuvre.getUrl().isEmpty()){
            showAlert("Please enter an image",false);

        }else {
            Oeuvre o = new Oeuvre();
            if (!oeuvre.getUrl().isEmpty())
            {
                File newFile = new File("C:/xampp/htdocs/img/" + selectedFile.getName());


                try {
                    Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            ServiceCategorie sc = new ServiceCategorie();
            o.setTitle(title.getText());
            o.setOwner(loggedInUser.getUser());
            o.setUrl(selectedFile.getName());
            o.setDescription(description.getText());
            o.setCategory(sc.readByName(categorie.getText()));
            o.setId(oeuvre.getId());
            so.update(o);

            FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            showAlert("Registration success",true);

            title.getScene().setRoot(root);


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        description.setWrapText(true);






    }
}