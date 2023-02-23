package com.example.PIDEV;
import entity.CommentaireOeuvre;
import entity.Dons;
import entity.Oeuvre;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceCommentaireOeuvre;
import service.ServiceDons;
import service.ServiceOeuvre;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addDons.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 500);
        stage.setTitle("tun'ART");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
 /*      ServiceCommentaireOeuvre so= new ServiceCommentaireOeuvre();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Oeuvre o= new Oeuvre(2,"Wallowing Breeze","Wallowing Breeze","Faten Asker","Painting","Painting2.png");
        CommentaireOeuvre co= new CommentaireOeuvre(o,1,"Great Painting",now);
        so.insert(co); */
   /*     Dons d = new Dons(12,"pidev0.1","behya chwaya",Timestamp.valueOf("2018-09-01 09:01:15") ,Timestamp.valueOf("2018-09-01 09:01:15"),150,"majj");
        ServiceDons so= new ServiceDons();
        so.insert(d); */

     /*   ServiceDons so= new ServiceDons();
        Dons d= so.readById(6);
        d.setAmount(10);
        so.delete(d);   */


        launch();
    };


}