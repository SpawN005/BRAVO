package com.example.PIDEV;

import entity.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import service.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class TraiterReclamationController implements Initializable{
    @FXML
    private ComboBox<String> etatReclamationCBX;
    ObservableList<String> listetat = FXCollections.observableArrayList("on hold", "processing", "treated");
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
    private Reclamation localreclamation;

    public   void SetReclamation(Reclamation r){
        localreclamation=r;
        System.out.println("reclamtion---------------------"+localreclamation);
        title.setText(r.getTitle());
        description.setText(r.getDescription());
        Date date_sql = new Date(Calendar.getInstance().getTime().getTime());

        reclamation = new Reclamation(localreclamation.getId(), r.getTitle(), r.getDescription(), date_sql, r.getEtat(), r.getOwnerID(), date_sql, r.getNote());

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
    public void TraiterReclamation(ActionEvent event)  {
        Date date_sql = new Date(Calendar.getInstance().getTime().getTime());
        Reclamation r = new Reclamation();
        localreclamation.setTitle(title.getText());
        localreclamation.setDescription(description.getText());
        // r.setDate_creation(date_sql);
        localreclamation.setEtat(etatReclamationCBX.getValue());
        //r.setOwnerID(u3);
        localreclamation.setDate_treatment(date_sql);
        localreclamation.setNote(1);
        //.setId(55);
        sr.update(localreclamation);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        showAlert("traitement  réussie", true);

        title.getScene().setRoot(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etatReclamationCBX.setPromptText("L'etat de la reclamation est : ");
        etatReclamationCBX.setItems(listetat);
    }
}
