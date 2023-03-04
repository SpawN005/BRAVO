package com.example.PIDEV;

import entity.Reclamation;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.LoggedInUser;
import service.ServiceReclamation;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

public class afficherMesReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> TableViewReclamation;
    @FXML
    private Button addReclamation;
    @FXML
    private Button supprimerBTN;
    @FXML
    private Button modifierBTN;
    static Reclamation selectionedReclamation;
    @FXML
   // private ComboBox<String> typeRecherche;
   // ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "title", "description", "etat");


   // private TextField RechercheTextField;

  //  private HBox hbox;
   // private static ComboBox<String> typeRechercheStatus;
    private ServiceReclamation sr = new ServiceReclamation();
    Reclamation reclamation;
    Stage window;
    private Stage primaryStage;
    private Reclamation localreclamation;
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
       // typeRecherche.setItems(listeTypeRecherche);
       // typeRecherche.setValue("Tout");

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
        LoggedInUser loggedInUser = new LoggedInUser();
        ObservableList<Reclamation> re=FXCollections.observableArrayList(sr.AfficherListeReclamationByownerId(loggedInUser.getUser().getId()));

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
            new ServiceReclamation().delete(localreclamation);
            //System.out.println(allreclamation.get(0).getId());
            System.out.println(localreclamation);
            reclamationSelected.forEach(allreclamation::remove);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherMesReclamations.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            showAlert("Reclamation supprimée avec succée",true);

        }
    }

    public void modifierReclamationGUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierReclamation.fxml"));
        Parent root = loader.load();
        modifierReclamationController controller=loader.getController();
        controller.SetReclamation(localreclamation);
        System.out.println("reclamation ::::::::::::::::::"+localreclamation);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();



    }
    public void ajouterReclamation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
        Parent root = loader.load();
        System.out.println("reclamation ::::::::::::::::::"+localreclamation);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();



    }
    public void refresh() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherMesReclamations.fxml"));
        Parent root = loader.load();
        System.out.println("reclamation ::::::::::::::::::"+localreclamation);
        TableViewReclamation.getScene().setRoot(root);



    }

    @FXML
    void backArrow(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TableViewReclamation.getScene().setRoot(root);
    }

    /*public void list() {
        ArrayList arrayList = null;
        ServiceReclamation sr = new ServiceReclamation();
        //combo box relative si on clique sur  l'etat
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
        //TableViewReclamation.setItems(getReclamation());
        if ((typeRecherche.getValue().toString().equals("etat") )) {
            arrayList = (ArrayList) sr.rechercheReclamationsOwnerId(typeRecherche.getValue().toString(), typeRechercheStatus.getValue().toString(),8);
        }
        else if ((RechercheTextField.getText().equals("")) ) {
            arrayList = (ArrayList) sr.AfficherListeReclamationByownerId(8);}
        else {
            arrayList = (ArrayList) sr.rechercheReclamationsOwnerId(typeRecherche.getValue().toString(), RechercheTextField.getText(),8);
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TableViewReclamation.setItems(observableList);}*/

    public void getselectedreclamation(MouseEvent mouseEvent) {
        localreclamation=TableViewReclamation.getSelectionModel().getSelectedItem();
    }
}
