package com.example.PIDEV;
import entity.CommentaireEvent;
import entity.Event;
import entity.Reservation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.ServiceCommentaire;
import service.ServiceEvent;
import service.ServiceReservation;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("Feed Page!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        LocalDate d1=LocalDate.of(2023 , 02 , 18);
        LocalDate d2=LocalDate.of(2023 , 03 , 20);
        LocalDate d3=LocalDate.of(1998 , 9 , 29);


        Event e1=new Event( "ahla wasahla","hhhhh",0, d1, d1, 10 , "ttt" ,50,"https://dev8tvkyziqz.cloudfront.net/1040x350/5e4a62f90ea94_Atawa_decoration_evenementielle_6_67fc28837a.jpg");
        Event e2=new Event( "AAC","kouchou kouchou",0, d2 ,d2,5, "dance" , 100 ,"Urbain Dance");
        Event e3=new Event( "hjhj","hello ",5, d3 ,d3,20, "musique" , 50 ,"sama3ni");


        ServiceEvent sv= new ServiceEvent();
       // sv.insert(e1);
          sv.insert(e3);
       // sv.delete(e2);
       // sv.deleteById(e2,4);
       // sv.updateById(e2,8);
        //sv.update(e1);
        sv.readAll().forEach(System.out::println);
       // System.out.println(sv.readById(7));

        CommentaireEvent c1= new CommentaireEvent("Mercii", 3,2);
        CommentaireEvent c2= new CommentaireEvent("aaichek", 6,5);
        ServiceCommentaire sc= new ServiceCommentaire();
       // sc.insert(c1);
        //sc.insert(c2);
        sc.readAll().forEach(System.out::println);
       // sc.delete(c2);
        // sc.deleteById(c2,4);
        // sc.updateById(c2,8);
        //sc.update(c1);
        // System.out.println(sc.readById(7));


        Reservation r1=new Reservation(1,3,true,8);
        Reservation r2=new Reservation(2,6, false,60);
        Reservation r3=new Reservation(5,7,false,7);
        Reservation r4=new Reservation(6,8, true,50);
        ServiceReservation sr=new ServiceReservation();
        //sr.insert(r1);
       // sr.insert(r2);
        //sr.insert(r3);

        //sr.insert(r4);
        sr.readAll().forEach(System.out::println);
        sr.update(r2);








    };


}