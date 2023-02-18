package com.example.PIDEV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
import utils.DataSource;

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
        launch();
        DataSource d = DataSource.getInstance();
        java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        Reclamation r = new Reclamation("Oeuvre dart","jai pas pu rechercher les ouevres",date_sql,"on hold",date_sql,2);
        Reclamation r66 = new Reclamation("service","service tres lent",date_sql,"treated",date_sql,5);
        ServiceReclamation sr =new ServiceReclamation ();
        //sr.insert(r);
        //sr.insert2(r);
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
        ServiceTypeReclamation Tr= new ServiceTypeReclamation();
        //Tr.insert(T);
        Tr.insert(T1);
        //Tr.deleteType2(10);
        //Tr.delete(T);
        //Tr.readAll().forEach(System.out::println);
        //System.out.println("la ligne suivante est "+Tr.readById(12));
        //Tr.update(T);
        //Tr.update2(T,13);
        //Tr.RechercheType("prestation").forEach(System.out::println);













    }





}