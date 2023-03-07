package com.example.PIDEV;

import entity.PasswordHasher;
import entity.User;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.LoggedInUser;
import service.ServiceUser;

public class LoggedInController implements Initializable {
    @FXML
    private TextField firstnamefield;

    @FXML
    private Label fnamelabel1;

    @FXML
    private TextField lastnamefield;

    @FXML
    private Label lnamelabel;

    @FXML
    private ImageView pdpImage;

    @FXML
    private TextField phonefield;

    @FXML
    private TextField pwdfield;

    @FXML
    private Button savebtn;
       
    @FXML
    private Button editBtn;

    
    @FXML
    private Button savebtn1;


    private User user;
    File selectedFile=null;
    @FXML
    private HBox events;

    @FXML
    private HBox feed;
    @FXML
    private HBox stats;
    @FXML
    private HBox reclamation;
    @FXML
    private HBox users;
    @FXML
    private VBox leftAnchor;

    @FXML
    private HBox logout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoggedInUser loggedInUser = new LoggedInUser();
        if (!loggedInUser.getUser().getRole().equalsIgnoreCase("admin")){
            reclamation.setVisible(false);
            users.setVisible(false);
            events.setVisible(false);
            stats.setVisible(false);

        }else
        {leftAnchor.getChildren().remove(feed);

        }
        
        logout.setOnMouseClicked(e->{
            Stage stage = new Stage();
            loggedInUser.setUser(null);
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Main.fxml"));

            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1125, 770);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            stage.setTitle("tun'ART");
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            Media audio = new Media(Main.class.getResource("audio/audio.mp3").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
            Stage s =(Stage) logout.getScene().getWindow();
            s.close();
            stage.setScene(scene);
            stage.show();
        });

        reclamation.setOnMouseClicked(e->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });
        stats.setOnMouseClicked(e->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chartReclamation.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });
        feed.setOnMouseClicked(e->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Feed.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            logout.getScene().setRoot(root);
        });
        users.setOnMouseClicked(e->
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInterface.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        events.setOnMouseClicked(e->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            logout.getScene().setRoot(root);
        });
      
    }

            void setUser(User user) {
        this.user = user;   
        firstnamefield.setText(user.getFirstName());
        lastnamefield.setText(user.getLastName());
        phonefield.setText(String.valueOf(user.getPhoneNumber()));
                fnamelabel1.setText(user.getFirstName());
                lnamelabel.setText(user.getLastName());
                pdpImage.setImage(new Image("file:C:/xampp/htdocs/img/"+user.getimage()));
                selectedFile= new File("file:C:/xampp/htdocs/img/"+user.getimage());
            }
            
            

                        @FXML
            private void browseImage() {
                // Create a new FileChooser object
                FileChooser fileChooser = new FileChooser();

                // Set the file chooser title
                fileChooser.setTitle("Select Image File");

                // Set the initial directory to the user's home directory
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

                // Add a filter to only show image files
                FileChooser.ExtensionFilter imageFilter =
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
                fileChooser.getExtensionFilters().add(imageFilter);

                // Show the file chooser dialog and wait for user input
                 selectedFile = fileChooser.showOpenDialog(savebtn.getScene().getWindow());

                // If the user selected a file, update the ImageView with the new image
                if (selectedFile != null) {
                    Image newImage = new Image(selectedFile.toURI().toString());
                    pdpImage.setImage(newImage);
                }
            }
            
                    @FXML
                    private void saveChanges() {
                        
                        PasswordHasher hasher = new PasswordHasher();

                                                   
                        // TODO: Save changes to database
                        User updatedUser = new User(user.getId(), firstnamefield.getText(), lastnamefield.getText(),Integer.parseInt(phonefield.getText()),hasher.hashPassword(pwdfield.getText()),selectedFile.getName());

                        ServiceUser service = new ServiceUser();
                        service.update(updatedUser);
                        
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setHeaderText("Your account has been Updated!");
                                        alert.showAndWait();
                        File newFile = new File("C:/xampp/htdocs/img/" + selectedFile.getName());
                        try {
                            Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
}
                    
                    @FXML
                    private void deleteacc() throws IOException {
                        ServiceUser service = new ServiceUser();
                        service.delete(user);
                        
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setHeaderText("Your account has been deleted!");
                                        alert.showAndWait();
                                       Stage currentStage = (Stage) savebtn1.getScene().getWindow();
                                       currentStage.close();
                                        
                                        Stage stage =new Stage();
                                        
                                 Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                                        Scene scene = new Scene(root);
                                        scene.setFill(Color.TRANSPARENT);
                                        stage.setScene(scene);
                                        stage.initStyle(StageStyle.TRANSPARENT);
                                        stage.show();
                                       
                                      
                                      
                                         savebtn1.getScene().setRoot(root);
                                    
                                          }
                    
                    
                 

            
            



    

    
}
