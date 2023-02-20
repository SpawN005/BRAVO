package com.example.PIDEV;
import entity.Event;
import entity.NoteEvent;
import entity.Reservation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceEvent;
import service.ServiceNoteEvent;
import service.ServiceReservation;
import service.ServiceUser;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application  {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddEvent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("Feed Page!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        LocalDate d1 = LocalDate.of(2023, 02, 18);
        LocalDate d2 = LocalDate.of(2023, 03, 20);
        LocalDate d3 = LocalDate.of(1998, 9, 29);
        LocalDate d4 = LocalDate.of(2000, 11, 07);




        ServiceNoteEvent SN = new ServiceNoteEvent();
        ServiceEvent SE= new ServiceEvent();

        NoteEvent  NE1= new NoteEvent(5,SE.readById(8));
        SN.insert(NE1);




       Event e1=new Event( "ahla wasahla","hhhhh",0, d1, d1, 10 , "ttt" ,"https://dev8tvkyziqz.cloudfront.net/1040x350/5e4a62f90ea94_Atawa_decoration_evenementielle_6_67fc28837a.jpg");
        Event e2=new Event( "MARIOUMAAAA","kouchou kouchou",0, d2 ,d2,5, "dance" ,"Urbain Dance");
        Event e3=new Event( "hjhj","hello ",5, d3 ,d3,20, "musique" ,"sama3ni");
        Event e4 = new Event("dj", "hello ", 5, d4, d4, 20, "musique", "sama3ni");


        ServiceEvent sv = new ServiceEvent();
        // sv.insert(e1);
       // sv.insert(e2);
        // sv.delete(e2);
        // sv.deleteById(e2,0);
        // sv.updateById(e2,8);
        //sv.update(e1);
        sv.readAll().forEach(System.out::println);
        // System.out.println(sv.readById(7));*/


              ServiceReservation SR= new ServiceReservation();
              ServiceUser SU = new ServiceUser();

        Reservation r1=new Reservation(SU.readById(1),SE.readById(3),true,8);
        Reservation r2=new Reservation(SU.readById(2),SE.readById(6), false,60);
        Reservation r3=new Reservation(SU.readById(5),SE.readById(8),false,7);
        Reservation r4=new Reservation(SU.readById(6),SE.readById(3), true,50);
        ServiceReservation sr=new ServiceReservation();
        sr.insert(r1);
        sr.insert(r2);
        sr.insert(r3);

       // sr.insert(r4);
        //sr.readAll().forEach(System.out::println);
       // sr.update(r2);


    };

}
