package com.example.PIDEV;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import entity.Reclamation;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ServiceReclamation;
import service.ServiceUser;
import utils.DataSource;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
public class afficherReclamationController implements Initializable {


    @FXML
    private TableView<Reclamation> TableViewReclamation;
    @FXML
    private Button supprimerBTN;
    @FXML
    private Button modifierBTN;
    @FXML
    private Button  TraiterBTN;
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
    private Reclamation localreclamation;
    @FXML
    private Button PDFbtn;
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

        typeRecherche.setOnAction((event) -> {
            String selectedValue = typeRecherche.getValue();
            if (selectedValue.equals("etat") && !selectedValue.isEmpty()) {
                String requete = "SELECT COUNT(id) as nbr1 FROM Reclamation WHERE etat LIKE 'on hold'";
                int nombreReclamationsEnAttente = 0;

                try {
                    // Établir la connexion à la base de données
                    Statement st = DataSource.getInstance().getCnx().createStatement();
                    ResultSet rs = st.executeQuery(requete);

                    // Récupérer le nombre de réclamations en attente à partir de l'objet ResultSet
                    if (rs.next()) {
                        nombreReclamationsEnAttente = rs.getInt("nbr1");

                }// Fermer la connexion à la base de données
                    rs.close();
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String message = "Vous avez " + nombreReclamationsEnAttente + " réclamations en attente!";
                displayPopup(message);
            }});

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
        TraiterBTN.setOnAction(e-> {
            try {
                traiterReclamationGUI();
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
            new ServiceReclamation().delete(localreclamation);
            //System.out.println(allreclamation.get(0).getId());
            System.out.println(localreclamation);
            reclamationSelected.forEach(allreclamation::remove);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
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

    public void list() {
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
            arrayList = (ArrayList) sr.RechercheReclamations(typeRecherche.getValue().toString(), typeRechercheStatus.getValue().toString());
    }
        else if ((RechercheTextField.getText().equals("")) ) {
            arrayList = (ArrayList) sr.readAll();}
        else {
            arrayList = (ArrayList) sr.RechercheReclamations(typeRecherche.getValue().toString(), RechercheTextField.getText());
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TableViewReclamation.setItems(observableList);}

    public void getselectedreclamation(MouseEvent mouseEvent) {
         localreclamation=TableViewReclamation.getSelectionModel().getSelectedItem();

    }
        private void displayPopup(String message) {
            // Créer une nouvelle fenêtre pop-up
            Stage popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setTitle("Fenêtre pop-up");

            // Créer un label pour afficher le message
            Label label = new Label(message);
            label.setAlignment(Pos.CENTER);
            label.setPadding(new Insets(10));

            // Ajouter le label à la scène
            Scene scene = new Scene(label);
            popupWindow.setScene(scene);

            // Afficher la fenêtre pop-up
            popupWindow.showAndWait();

        }

    public void traiterReclamationGUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("traiterReclamation.fxml"));
        Parent root = loader.load();
        TraiterReclamationController controller=loader.getController();
        controller.SetReclamation(localreclamation);
        System.out.println("reclamation ::::::::::::::::::"+localreclamation);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void convertTopdf(ActionEvent event)throws FileNotFoundException, IOException {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("C:\\Users\\ferie\\OneDrive\\Bureau\\Reclamation.pdf"));
            Document doc = new Document(pdfDoc, PageSize.A4);

            try {
                // Get all events from the database
                String query = "SELECT * FROM Reclamation";
                PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                // Add event information to the PDF
                doc.add(new Paragraph("Liste des reclamations:"));
                doc.add(new Paragraph("------------------------\n"));

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    Date dateCreation = rs.getDate("date_creation");
                    String etat = rs.getString("etat");
                    Date dateTreatment = rs.getDate("date_treatment");
                    int note = rs.getInt("note");

                    doc.add(new Paragraph("id de reclam  : " + id));
                    doc.add(new Paragraph("titre de reclamation  : " + title));
                    doc.add(new Paragraph("La date de creation : " + dateCreation));
                    doc.add(new Paragraph("l'etat de la reclamation  : " + etat));
                    doc.add(new Paragraph("La date de traitement: " + dateTreatment));
                    doc.add(new Paragraph("La note : " + note));

                    doc.add(new Paragraph("\n"));
                }

                doc.close();
                Desktop.getDesktop().open(new File("C:\\Users\\ferie\\OneDrive\\Bureau\\Reclamation.pdf"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }






