package com.example.PIDEV;

import entity.Blog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ServiceBlog;
import service.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class ModifierBlogController implements Initializable {

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView ImageView;

    @FXML
    private TextField description;

    @FXML
    private TextField title;

    @FXML
    private TextArea content;

    @FXML
    private Button uploadImage;

    private File selectedFile = null;
    private Stage stage;

    ServiceBlog sb= new ServiceBlog();
    ServiceUser su = new ServiceUser();
    private Blog blog ;




    public void SetBlog(Blog blog) {
        Blog b = new Blog();
        title.setText(b.getTitle());
        description.setText(b.getDescription());
        content.setText(b.getContent());
        uploadImage.setText(b.getUrl());

        ImageView.setImage(new Image("file:src/main/resources/com/example/PIDEV/assets/" + b.getUrl()));
        blog = new Blog(b.getId(), b.getTitle(), b.getDescription(), b.getContent(), b.getUrl(),b.getAuthor());


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


            ImageView.setImage(new Image("file:" + selectedFile));
            ImageView.setFitWidth(200);
            ImageView.setFitHeight(150);

        }
    }

    @FXML
    void cancel() {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root = null;

        try {
            root= loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.getScene().setRoot(root);
    }

    private void showAlert(String message, boolean b){
        Alert alert;
        if(b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void submit() {
        String text = title.getText();

        if (text.isEmpty()) {
            showAlert("Please enter a title", false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your title must contain only letters and spaces", false);
        } else if (description.getText().isEmpty()) {
            showAlert("Please enter a description", false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your title must contain only letters and spaces", false);

        } else if (selectedFile == null) {
            showAlert("Please enter an image", false);

        } else {
            File newFile = new File("src/main/resources/com/example/PIDEV/assets/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            Blog b = new Blog();

            b.setTitle(title.getText());
            b.setDescription(description.getText());
            b.setContent(content.getText());
            b.setUrl(uploadImage.getText());
            b.setAuthor(su.readById(30));


            sb.update(b);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            showAlert("Modification réussie", true);

            title.getScene().setRoot(root);


        }
    }



        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            title.getText();
            description.getText();
            content.getText();

        }
    }
