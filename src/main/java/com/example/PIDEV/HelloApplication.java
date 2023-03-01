package com.example.PIDEV;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

import java.util.Calendar;

import entity.Reclamation;
import entity.TypeReclamation;
import service.ServiceReclamation;
import service.ServiceTypeReclamation;
import service.ServiceUser;
import utils.DataSource;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("ajouterReclamation.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 770, 770);
        stage.setTitle("envoie de reclamation!");
        stage.setScene(scene2);
        stage.show();*/

        /*FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("modifierReclamation.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 770, 770);
        stage.setTitle("envoie de reclamation!");
        stage.setScene(scene2);
        stage.show();*/

        /*FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("afficherReclamation.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1125, 600);
        stage.setTitle("affichage de reclamation !");
        stage.setScene(scene2);
        stage.show();*/

      FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("acceuilReclamation.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 770, 600);
        stage.setTitle("modification de reclamation !");
        stage.setScene(scene2);
        stage.show();

       /*FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("chartReclamation.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 770, 600);
        stage.setTitle("modification de reclamation !");
        stage.setScene(scene2);
        stage.show();*/

    }

    public static void main(String[] args) {
        launch();
        DataSource d = DataSource.getInstance();

        java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        Reclamation r = new Reclamation("Oeuvre dart","jai pas pu rechercher les ouevres",date_sql,"on hold",date_sql,2);
        Reclamation r66 = new Reclamation("service","service tres lent",date_sql,"treated",date_sql,5);

        ServiceReclamation sr =new ServiceReclamation ();

        //sr.insert(r);

        //sr.insert(r66);

        //sr.readAll().forEach(System.out::println);
        //System.out.println("la ligne suivante est "+sr.readById(17));
        //System.out.println("la ligne suivante est "+sr.readById(13));

        //sr.update2(r,17);
        //sr.update(r);
        //sr.delete2(18);
        //sr.delete(r);

        //sr.RechercheTitle("artiste").forEach(System.out::println);
        //sr.RechercheTitle("art work").forEach(System.out::println);
        //sr.RechercheReclamations("Tout","on hold").forEach(System.out::println);


        TypeReclamation T = new TypeReclamation("prestation");
        TypeReclamation T1 = new TypeReclamation("dons");
        TypeReclamation T2 = new TypeReclamation("blogs");
        TypeReclamation T3 = new TypeReclamation("oeuvre");
        TypeReclamation T4 = new TypeReclamation("artwork");

        ServiceTypeReclamation Tr= new ServiceTypeReclamation();
        //Tr.insert(T);
        //Tr.insert(T1);

        //Tr.insert(T1);
        //Tr.insert(T3);
        //Tr.deleteType2(10);
        //Tr.delete(T);
        //Tr.readAll().forEach(System.out::println);
        //System.out.println("la ligne suivante est "+Tr.readById(12));
        //Tr.update(T);
        //Tr.update2(T,13);
        //Tr.RechercheType("prestation").forEach(System.out::println);

        User u = new User("feriel","sahbi",25222421,"feriel.sahbi@gmil.com","simple user");
        User u2 = new User("mariem","hammi",22442145,"mariem.hammi@gmil.com","simple user");

       User u3 = new User("malik","rmedi",22442166,"malik.rmadi@gmil.com","simple user");
        ServiceUser s =new ServiceUser();
        //s.insert(u3);
        //s.insert(u2);
        //System.out.println("la ligne suivante est "+s.readById(8));
      Reclamation r44 =new Reclamation("don","tres peu",date_sql,"on hold",s.readById(6),date_sql,0);
        Reclamation rx =new Reclamation(50,"test","test",date_sql,"on hold",s.readById(8),date_sql,4);
       // sr.insert(rx);
      // sr.update(rx);
       //sr.delete(rx);

      //sr.insert(r44);
      //sr.update(r44);




        //System.out.println("la ligne suivante est "+sr.readById(61));


        //sr.RechercheReclamations("Tout","on hold").forEach(System.out::println);
        //sr.RechercheTitle("art work").forEach(System.out::println);
        //sr.readAll().forEach(System.out::println);
        //sr.AfficherListeReclamationDESC().forEach(System.out::println);
        //sr.AfficherListeReclamationASC().forEach(System.out::println);

        //sr.update(r44);
        //sr.delete2(48);











    }





}