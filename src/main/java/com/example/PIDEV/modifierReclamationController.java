package com.example.PIDEV;

import entity.Reclamation;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import service.ServiceReclamation;
import service.ServiceUser;
import java.io.IOException;
import javafx.scene.Parent;

import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class modifierReclamationController implements Initializable {
    @FXML
    private ComboBox<String> typeReclamationCBX;
    ObservableList<String> listtype = FXCollections.observableArrayList("oeuvre", "blogs", "dons", "Autre");

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
    private Reclamation reclamation;
    private ServiceReclamation sr = new ServiceReclamation();
    public   void SetReclamation(Reclamation r){
        User u3 = new User("malik","rmedi",22442166,"malik.rmadi@gmil.com","simple user");
        ServiceUser s =new ServiceUser();
        description.setText(r.getDescription());
        title.setText(r.getTitle());

        Date date_sql = new Date(Calendar.getInstance().getTime().getTime());

        reclamation = new Reclamation(r.getId(), r.getTitle(), r.getDescription(), date_sql, r.getEtat(), r.getOwnerID(), date_sql, r.getNote());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeReclamationCBX.setPromptText("Reclamation à propos de : ");
        typeReclamationCBX.setItems(listtype);
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
    public void modifierReclamationGUI(ActionEvent event) {
        Date date_sql = new Date(Calendar.getInstance().getTime().getTime());

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
            //titlecheck.setImage(new Image("@assets/checkMark.png"));
            //textecheckvalue.setImage(new Image("@assets/checkMark.png"));

            System.out.println("merci d'avoir bien redigé la reclamation ");
            User u2 = new User(7,"mariem","hammi",22442145,"mariem.hammi@gmil.com","simple user");
            ServiceUser s =new ServiceUser();

            Reclamation r = new Reclamation();

            r.setTitle(title.getText());
            r.setDescription(description.getText());
            r.setDate_creation(date_sql);
            r.setEtat("processing");
            r.setOwnerID(reclamation.getOwnerID());
            r.setDate_treatment(date_sql);
            r.setNote(4);
            r.setId(42);//reclamation.getId()
            sr.update(r);

            showAlert("Modification établie avec succée",true);

    }
    }






}









