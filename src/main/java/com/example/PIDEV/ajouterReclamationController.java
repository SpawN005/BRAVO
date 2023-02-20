package com.example.PIDEV;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.ServiceReclamation;
import entity.Reclamation;



public class ajouterReclamationController implements Initializable {

    @FXML
    private ComboBox<String> typeReclamationCBX;
    ObservableList<String> listtype = FXCollections.observableArrayList("oeuvre", "blogs", "dons", "Autre");

    @FXML
    private TextField title;
    @FXML
    private TextArea description;

    @FXML
    private Button btn;
    //private Stage stage;
    private ServiceReclamation sr = new ServiceReclamation();

    @FXML
    private void selectionReclamation(ActionEvent event) {
        if (typeReclamationCBX.getValue().toString() == "oeuvre") {
            //Stage stage = new Stage();
            //rechercheBonPlansTFL.setOnKeyReleased((KeyEvent a) -> {listBonPlan();});
    }
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
        java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
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
            System.out.println("merci d'avoir bien redigé la reclamation ");

            r = new Reclamation(title.getText(), description.getText(),date_sql,"on hold",date_sql,0);
            sr.insert(r);
            System.out.println("reclamation ajoutée avec succes");
        }
    }
    }
