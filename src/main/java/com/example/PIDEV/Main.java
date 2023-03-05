package com.example.PIDEV;
import entity.CommentaireOeuvre;
import entity.NoteOeuvre;
import entity.Oeuvre;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ServiceCommentaireOeuvre;
import service.ServiceNoteOeuvre;
import service.ServiceOeuvre;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.time.Instant;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addOeuvre.fxml"));
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
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {
        launch();
    }
    };

