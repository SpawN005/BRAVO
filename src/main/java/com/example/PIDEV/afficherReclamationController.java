package com.example.PIDEV;

import javafx.scene.control.*;
import service.ServiceReclamation;
import service.ServiceUser;
import entity.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import service.ServiceReclamation;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.util.Callback;
import javafx.scene.layout.HBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.Date;
import javafx.stage.Stage;

public class afficherReclamationController implements Initializable {


    @FXML
    private TableView<Reclamation> TableViewReclamation;
    @FXML
    private Button supprimerBTN;
    @FXML
    private Button modifierBTN;
    static Reclamation selectionedReclamation;
    @FXML
    private ComboBox<String> typeRecherche;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "title", "description", "etat");

    @FXML
    private TextField RechercheTextField;
    @FXML
    private HBox hbox;
    private static ComboBox<String> typeRechercheStatus;
    private ServiceReclamation sr = new ServiceReclamation();
    Reclamation reclamation;
    ArrayList arrayList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeRecherche.setItems(listeTypeRecherche);
        typeRecherche.setValue("Tout");

        sr.readAll();
        TableColumn<Reclamation, Integer> idColumn = new TableColumn<>("id");
;
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> titleColumn = new TableColumn<>("title");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> descriptionColumn = new TableColumn<>("description");

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, Date> date_creationColumn = new TableColumn<>("date_creation");

        date_creationColumn.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        date_creationColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> etatColumn = new TableColumn<>("etat");

        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etatColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, Date> Date_treatmentColumn = new TableColumn<>("Date_treatment");

        Date_treatmentColumn.setCellValueFactory(new PropertyValueFactory<>("Date_treatment"));
        Date_treatmentColumn.setStyle("-fx-alignment: CENTER;");

        TableViewReclamation= new TableView<>();
        arrayList = (ArrayList) sr.readAll();

    ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TableViewReclamation.setItems(observableList);
        TableViewReclamation.getColumns().addAll(idColumn, titleColumn,descriptionColumn,date_creationColumn,etatColumn,Date_treatmentColumn);

        VBox root = new VBox();
        root.getChildren().add(TableViewReclamation);

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
    public void supprimerReclamation(ActionEvent event) {
            sr = new ServiceReclamation();
        if (!TableViewReclamation.getSelectionModel().isEmpty()&&!selectionedReclamation.getEtat().equals("on hold")) {
            sr.delete(reclamation);
            FXMLLoader loader=new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            TableViewReclamation.getScene().setRoot(root);
            }
        }





    public void modifierReclamationGUI(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("modifierReclamation.fxml"));
        Parent root= null;
        try {
            root = loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //modifierReclamationController mrc = loader.getController();
        //mrc.SetReclamation(reclamation);
        //TableViewReclamation.getScene().setRoot(root);

    }

    public void list() {
        ArrayList arrayList = null;
        ServiceReclamation sr = new ServiceReclamation();
        if (typeRechercheStatus == null) {
            typeRechercheStatus = new ComboBox<String>();
        }
        typeRechercheStatus.setOnAction(e -> list());

        if (typeRecherche.getValue().toString().equals("etat") && hbox.getChildren().contains(RechercheTextField)) {
            typeRechercheStatus.getItems().setAll("on hold", "processing", "treated");
            typeRechercheStatus.setValue("on hold");
            hbox.getChildren().remove(RechercheTextField);
            hbox.getChildren().add(typeRechercheStatus);
        } else if (!typeRecherche.getValue().toString().equals("etat") && !hbox.getChildren().contains(RechercheTextField)) {
            hbox.getChildren().remove(typeRechercheStatus);
            hbox.getChildren().add(RechercheTextField);
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TableViewReclamation.setItems(observableList);

    }

    public void SetReclamation(Reclamation reclamation) {

    }
}
