package com.example.PIDEV;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entity.PasswordHasher;
import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Button verifyBtn;
    private Stage stage;
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
        if (loggedInUser.getUser().getIs_Verified()==1){
            verifyBtn.setVisible(false);
        }
        if (!loggedInUser.getUser().getRole().equalsIgnoreCase("[\"ROLE_ADMIN\"]")){
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
                pdpImage.setImage(new Image("C:/xampp/htdocs/img/"+user.getimage()));
                selectedFile= new File("C:/xampp/htdocs/img/"+user.getimage());
                System.out.println(selectedFile);
            }
            
            

                        @FXML
            private void browseImage() {
                            FileChooser fileChooser = new FileChooser();
                            fileChooser.setTitle("Upload an image");
                            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
                            fileChooser.getExtensionFilters().add(extFilter);
                            selectedFile = fileChooser.showOpenDialog(stage);
                            pdpImage.setImage(new Image(selectedFile.getPath()));




            }
            
                    @FXML
                    private void saveChanges() throws NoSuchAlgorithmException {
                        
                        PasswordHasher hasher = new PasswordHasher();
                        LoggedInUser loggedInUser = new LoggedInUser();

                                                   
                        // TODO: Save changes to database
                        User updatedUser = new User(user.getId(), firstnamefield.getText(), lastnamefield.getText(),Integer.parseInt(phonefield.getText()),hasher.hashPassword(pwdfield.getText()).toString(),selectedFile.getName());
                        String role = loggedInUser.getUser().getRole();
                        loggedInUser.setUser(updatedUser);
                        loggedInUser.getUser().setRole(role);

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


    @FXML
    private void handleVerifyButtonClick() {
        String accountSid = "AC722e32116c6083cff1c7e8898c7a1dd5";
        String authToken = "7a9334e17663891b9f651c3fdcbef544";
        String twilioNumber = "+15076088911";

        String phoneNumber = "+216" + phonefield.getText();

        int verificationCode = (int) (Math.random() * 900000) + 100000;

        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(phoneNumber),
                        new com.twilio.type.PhoneNumber(twilioNumber),
                        "To verify your Tun'ART account enter the following code : " + verificationCode)
                .create();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Verify Phone Number");
        dialog.setHeaderText("Enter the verification code that was sent to your phone.");
        dialog.setContentText("Verification Code:");
        Optional<String> result = dialog.showAndWait();

        // Verify the code
        if (result.isPresent() && result.get().equals(Integer.toString(verificationCode))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Verification Successful!");
            alert.setContentText("Your phone number has been verified.");
            alert.showAndWait();
            ServiceUser su = new ServiceUser();
            su.VerifyUser(user);
            verifyBtn.setVisible(false);

        } else {
            // Code is incorrect
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Verification Failed!");
            alert.setContentText("The verification code you entered is incorrect.");
            alert.showAndWait();
        }
    }

            
            



    

    
}
