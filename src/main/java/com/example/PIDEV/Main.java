package com.example.PIDEV;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("tun'ART");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        Media audio = new Media(Main.class.getResource("audio/audio.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(audio);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.4);
        mediaPlayer.play();
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
    };

