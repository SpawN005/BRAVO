package com.example.PIDEV;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import javafx.embed.swing.SwingFXUtils;
import org.apache.commons.lang3.RandomStringUtils;
import java.awt.image.BufferedImage;
import java.util.Properties;
import entity.Oeuvre;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.LoggedInUser;
import service.ServiceOeuvre;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class AddOeuvreController implements Initializable {
    LoggedInUser loggedInUser= new LoggedInUser();
    @FXML
    private Button cancelButton;
    @FXML
    AnchorPane pane;
    @FXML
    private MenuButton categorie;

    @FXML
    private TextArea description;

    @FXML
    private Button submitButton;

    @FXML
    private TextField title;
    @FXML
    private ImageView ImageView;
    @FXML
    AnchorPane captchaContainer;
    @FXML
    private Button uploadImage;
    @FXML
    TextField captchaField;


    private Stage stage;
    private File selectedFile = null;
    MenuItem selectedMenuItem = null;
    private ServiceOeuvre so = new ServiceOeuvre();
    String randomWord = RandomStringUtils.randomAlphanumeric(6);
    DefaultKaptcha captcha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categorie.setText("Paint");
        MenuItem menuItem1 = new MenuItem("Music");
        MenuItem menuItem2 = new MenuItem("Paint");
        MenuItem menuItem3 =new MenuItem("Sculpture");
        description.setWrapText(true);
        categorie.getItems().addAll(menuItem1,menuItem2,menuItem3);


        menuItem1.setOnAction(event -> {
            selectedMenuItem = menuItem1;
            categorie.setText(menuItem1.getText());
        });
        menuItem2.setOnAction(event -> {
            selectedMenuItem = menuItem2;
            categorie.setText(menuItem2.getText());
        });
        menuItem3.setOnAction(event -> {
            selectedMenuItem = menuItem3;
            categorie.setText(menuItem3.getText());
        });
        captcha = new DefaultKaptcha();
        captcha.setConfig(new Config(new Properties()));

        BufferedImage image = captcha.createImage(randomWord);
        Image fxImage = SwingFXUtils.toFXImage(image, null);
        ImageView imageView = new ImageView(fxImage);
        imageView.setY(25);
        imageView.setX(25);
        captchaContainer.getChildren().add(imageView);






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
    private void browse()  {
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
    private void cancel(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.getScene().setRoot(root);
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
        else if (selectedFile==null){
            showAlert("Please enter an image",false);


        }else if ( !randomWord.equals(captchaField.getText())){
            showAlert("Please check yout captcha",false);
            System.out.println(randomWord);
        }
        else {
            Oeuvre o = new Oeuvre();
            File newFile = new File("C:/xampp/htdocs/img/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            o.setTitle(title.getText());
            o.setOwner(loggedInUser.getUser());

            o.setDescription(description.getText());
            o.setUrl(selectedFile.getName());
            o.setCategory(categorie.getText());
            so.insert(o);

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



}