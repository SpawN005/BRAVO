package com.example.PIDEV;

import entity.User;
import javafx.scene.control.*;
import service.ServiceReclamation;
import service.ServiceUser;
import entity.Reclamation;
import java.util.Optional;
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

import javafx.fxml.FXML;
import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.control.cell.PropertyValueFactory;

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
    Stage window;
    private Stage primaryStage;

    public ObservableList<Reclamation> getReclamation(){
        java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ObservableList <Reclamation> Reclamations  = FXCollections.observableArrayList();
        User u3 = new User("malik","rmedi",22442166,"malik.rmadi@gmil.com","simple user");
        ServiceUser s =new ServiceUser();

        Reclamations.add(new Reclamation(14,"oeuvre","oeuvre non conforme",date_sql,"on hold",s.readById(8),date_sql,6));
        Reclamations.add(new Reclamation(15,"dons","dons non conforme",date_sql,"on hold",s.readById(8),date_sql,5));
        Reclamations.add(new Reclamation(16,"blogs","blog tres lent",date_sql,"on hold",s.readById(8),date_sql,5));
        
        return Reclamations ;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeRecherche.setItems(listeTypeRecherche);
        typeRecherche.setValue("Tout");

        
        window=primaryStage;
        TableColumn<Reclamation, Integer> idColumn = new TableColumn<>("id");

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

        TableColumn<Reclamation, Integer> noteColumn = new TableColumn<>("note");

        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        noteColumn.setStyle("-fx-alignment: CENTER;");

        supprimerBTN.setOnAction(e->supprimerReclamation());
        modifierBTN.setOnAction(e-> {
            try {
                modifierReclamationGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        TableViewReclamation.getColumns().addAll(idColumn, titleColumn,descriptionColumn,date_creationColumn,etatColumn,Date_treatmentColumn,noteColumn);

        ObservableList<Reclamation> re=FXCollections.observableArrayList(sr.readAll());

        TableViewReclamation.setItems(re);


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
    public void supprimerReclamation() {
        ObservableList<Reclamation> reclamationSelected,allreclamation;
        allreclamation=TableViewReclamation.getItems();
        reclamationSelected=TableViewReclamation.getSelectionModel().getSelectedItems();
        //reclamationSelected.forEach(allreclamation::remove);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous  vraiment supprimer cette reclamation");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            new ServiceReclamation().delete2(allreclamation.get(0).getId());
            //System.out.println(allreclamation.get(0).getId());
            reclamationSelected.forEach(allreclamation::remove);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            showAlert("Reclamation envoyée avec succee",true);

            //title.getScene().setRoot(root);
        }
        }




    public void modifierReclamationGUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierReclamation.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

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
        TableViewReclamation.setItems(getReclamation());

    }


}
