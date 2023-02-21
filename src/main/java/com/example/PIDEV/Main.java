package com.example.PIDEV;
import entity.CommentaireOeuvre;
import entity.Oeuvre;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceCommentaireOeuvre;
import service.ServiceOeuvre;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addOeuvre.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("tun'ART");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ServiceCommentaireOeuvre so= new ServiceCommentaireOeuvre();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Oeuvre o= new Oeuvre(2,"Wallowing Breeze","Wallowing Breeze","Faten Asker","Painting","Painting2.png");
        CommentaireOeuvre co= new CommentaireOeuvre(o,1,"Great Painting",now);
        so.insert(co);


        launch();
    };


}