package com.example.PIDEV;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

import entity.TypeReclamation;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.ServiceReclamation;
import entity.Reclamation;
import service.ServiceTypeReclamation;
import service.ServiceUser;


public class ajouterReclamationController implements Initializable {

    @FXML
    private ComboBox<String> typeReclamationCBX;
    ObservableList<String> listtype = FXCollections.observableArrayList("oeuvre", "blogs", "dons","event", "Autre");

    @FXML
    private TextField title;
    @FXML
    private TextArea description;

    @FXML
    private Button btn;
    @FXML
    private ImageView titlecheck;
    @FXML
    private ImageView textecheckvalue;

    //private Stage stage;
    private ServiceReclamation sr = new ServiceReclamation();
    private ServiceTypeReclamation tr=new ServiceTypeReclamation();

    @FXML
    private void selectionReclamation(ActionEvent event) {
       // if (typeReclamationCBX.getValue().toString() == "oeuvre") {
         //   TypeReclamation t= new TypeReclamation(typeReclamationCBX.getValue());
          //  tr.insert(t);


    }


    private void showAlert(String message ,boolean b) {
        Alert alert;
        if (b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeReclamationCBX.setPromptText("Reclamation à propos de : ");
        typeReclamationCBX.setItems(listtype);

    }


    public void ajouterReclamationGUI(ActionEvent event) {
        Date date_sql = new Date(Calendar.getInstance().getTime().getTime());
        Reclamation r;
        String text = title.getText();
        if (text.isEmpty()) {
            showAlert("Veuillez ajouter un titre",false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Le titre ne peut contenir ques des lettres et des espaces",false);
        }
        if (description.getText().isEmpty()) {
            showAlert("Veuillez ajouter une description",false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("La description ne peut contenir que des lettres et des espaces",false);
        }else {
           //titlecheck.setImage(new Image("C:\Users\ferie\OneDrive\Bureau\Nouveau dossier\BRAVO\src\main\resources\com\example\PIDEV\assets\checkMark.png"));
           //textecheckvalue.setImage(new Image("C:\Users\ferie\OneDrive\Bureau\Nouveau dossier\BRAVO\src\main\resources\com\example\PIDEV\assets\checkMark.png"));

            System.out.println("merci d'avoir bien redigé la reclamation ");
            User u2 = new User("mariem","hammi",22442145,"mariem.hammi@gmil.com","simple user");
            User u3 = new User("malik","rmedi",22442166,"malik.rmadi@gmil.com","simple user");
            ServiceUser s =new ServiceUser();

            r = new Reclamation(title.getText(), description.getText(),date_sql,"on hold",s.readById(8),date_sql,8);

            sr.insert(r);
            //recuperation de la valeur du combo box et l'inserer dans la table type reclamation
            TypeReclamation t= new TypeReclamation(typeReclamationCBX.getValue());
            tr.insert(t);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            showAlert("Reclamation envoyée avec succee",true);

            title.getScene().setRoot(root);
        }
    }
    }
