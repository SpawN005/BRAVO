package com.example.PIDEV;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private Label blog;

    @FXML
    private Label don;
    @FXML
    private Label paint;

    @FXML
    private Label ticket;
    @FXML
    private Label ev;

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
if( loggedInUser.getUser().getRole().equalsIgnoreCase("artist")){
    FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuDons.fxml"));
    Parent root= null;
    try {
        root = loader.load();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    logout_btn.getScene().setRoot(root);
}else{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuDonsUser.fxml"));
    Parent root= null;
    try {
        root = loader.load();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    logout_btn.getScene().setRoot(root);
}
    }
    @FXML
    void reservation(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageReservation.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logout_btn.getScene().setRoot(root);
    }

    @FXML
    void events(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logout_btn.getScene().setRoot(root);

    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        loggedInUser.setUser(null);
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);

        stage.setTitle("tun'ART");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        Media audio = new Media(Main.class.getResource("audio/audio.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(audio);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        Stage s =(Stage) logout_btn.getScene().getWindow();
        s.close();
        stage.setScene(scene);
        stage.show();

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


        if (loggedInUser.getUser().getRole().equalsIgnoreCase("admin"))
        {
            ev.setVisible(false);
            paint.setVisible(false);
            blog.setVisible(false);
            ticket.setVisible(false);
            don.setVisible(false);
        }




    }
}
