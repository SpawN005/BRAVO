package com.example.PIDEV;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import entity.Categorie;
import entity.Oeuvre;
import javafx.embed.swing.SwingFXUtils;
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
import org.apache.commons.lang3.RandomStringUtils;
import service.LoggedInUser;
import service.ServiceCategorie;
import service.ServiceOeuvre;
import utils.DataSource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class AddOeuvreController implements Initializable {
    LoggedInUser loggedInUser = new LoggedInUser();
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
    private ServiceCategorie sc = new ServiceCategorie();
    String randomWord = RandomStringUtils.randomAlphanumeric(6);
    DefaultKaptcha captcha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Categorie> l = sc.readAll();
        for(Categorie c : l){
            categorie.setText(c.getNom());
            MenuItem menuItem = new MenuItem(c.getNom());
            categorie.getItems().add(menuItem);
            menuItem.setOnAction(event -> {
                selectedMenuItem = menuItem;
                categorie.setText(menuItem.getText());
            });
        }

        description.setWrapText(true);




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
            showAlert("Please check your captcha",false);
            System.out.println(randomWord);
        }
        else if ( CheckProfanity(description.getText())){
            showAlert("Please keep it low",false);

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
            o.setCategory(sc.readByName(categorie.getText()));
            Connection conn = DataSource.getInstance().getCnx();
            String query = "SELECT * FROM artwork WHERE title = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, title.getText());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Email already exists, show popup
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    showAlert("title already taken",false);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
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
    private Boolean CheckProfanity (String text){

        String encodedText = null;
        try {
            encodedText = URLEncoder.encode(TranslatedText(text), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://profanity-filter-by-api-ninjas.p.rapidapi.com/v1/profanityfilter?text="+encodedText))
                .header("X-RapidAPI-Key", "5663b0b24emsh9f1230312127163p13953ajsnc45c9ef48937")
                .header("X-RapidAPI-Host", "profanity-filter-by-api-ninjas.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Boolean hasProfanity = jsonNode.get("has_profanity").asBoolean();
        return  hasProfanity;
    }
    public String TranslatedText(String text) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://rapid-translate-multi-traduction.p.rapidapi.com/t"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "5663b0b24emsh9f1230312127163p13953ajsnc45c9ef48937")
                .header("X-RapidAPI-Host", "rapid-translate-multi-traduction.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"from\": \"fr\",\r\n    \"to\": \"en\",\r\n    \"e\": \"\",\r\n    \"q\": [\r\n  \""+ text +"\"\r\n    ]\r\n}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}