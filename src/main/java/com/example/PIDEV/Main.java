package com.example.PIDEV;
import entity.DonsUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceDonsUser;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuDonsUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1050, 576);
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

        ServiceDonsUser so= new ServiceDonsUser();
        DonsUser d = new DonsUser(7,100,139,400);
        so.update(d);

     /*   ServiceDons so= new ServiceDons();
        Dons d= so.readById(6);
        d.setAmount(10);
        so.delete(d);   */


        launch();
    };


}