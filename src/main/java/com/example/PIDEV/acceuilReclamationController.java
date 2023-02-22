package com.example.PIDEV;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import entity.Oeuvre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.ServiceReclamation;
import entity.Reclamation;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class acceuilReclamationController implements Initializable {
    @FXML
    private AnchorPane mainAnchorPane;
    private Button envoyer;
    private Button afficher;
    private Button stat;
    private Stage stage=new Stage();
    private ServiceReclamation sr;
    private Reclamation reclamation;
    Stage window;
    private Stage primaryStage;


        public void listerReclamation() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }

        public void envoyerReclamation() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }

        public void afficherStatistique(ActionEvent event) {

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //envoyer.setOnMouseClicked(e->envoyerReclamation());
        //afficher.setOnMouseClicked(e->listerReclamation());


    }
    public   void SetReclamation(Reclamation r){

    }
}
