package com.example.PIDEV;

import entity.Dons;
import entity.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import service.EmailService;
import service.LoggedInUser;
import service.ServiceDons;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MenuDonsController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button ADDBTN;

    @FXML
    private Button Home;

    @FXML
    private Button STATSBTN;
    @FXML
    private GridPane container;


    @FXML
    private Button MYDON;
    @FXML
    private ImageView searchButton;

    @FXML
    private VBox cardlayout;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlMyDon;


    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;
    @FXML
    private TextField Searchfield;
    @FXML
    private Label name;
    @FXML
    private ImageView img;

    private List<Dons> recentlyAdded;
    private User user;

    ServiceDons sd = new ServiceDons();

    public void initialize(URL location, ResourceBundle resources) {
        LoggedInUser loggedInUser = new LoggedInUser();
        img.setImage(new Image("file:C:/xampp/htdocs/img/"+loggedInUser.getUser().getimage()));
        name.setText(loggedInUser.getUser().getFirstName()+" "+loggedInUser.getUser().getLastName());

        Image image = new Image(getClass().getResourceAsStream("assets/search.png"));
        searchButton.setImage(image);
        searchButton.setCursor(Cursor.HAND);

        // Add event handler for the search button
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleSearch(new ActionEvent());
            }
        });

        loadDonations("");
    }
    public static int i =0;
    void SetUser(User user){
        this.user = user;

    }

    public void handleSearch(ActionEvent event) {
        String searchQuery = Searchfield.getText().trim();
        loadDonations(searchQuery);
    }

    private void loadDonations(String searchQuery) {
        ServiceDons serviceDons = new ServiceDons();
        List<Dons> searchResults = serviceDons.searchByTitle(searchQuery);

        container.getChildren().clear(); // clear previous cards

        int column = 0;
        int row = 1;
        for (Dons donation : searchResults) {
            if (i==0){
                LocalDate currentDate = LocalDate.now();
                LocalDate expirationDate = donation.getDate_expiration();
                long daysUntilExpiration = currentDate.until(expirationDate).getDays();
                LoggedInUser loggedInUser = new LoggedInUser();
                System.out.println(daysUntilExpiration);
                EmailService emailService = new EmailService();
                if (daysUntilExpiration < 2 ) {
                    emailService.envoyer(loggedInUser.getUser().getEmail(),false);
                }
                if (daysUntilExpiration <= 0 ) {
                    serviceDons.delete(donation);
                    emailService.envoyer(loggedInUser.getUser().getEmail(),true);
                }
            }


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DonCard.fxml"));
                VBox card = loader.load();
                DonCardController controller = loader.getController();
                controller.setData(donation);
                if(column ==3){
                    column = 0;
                    ++row;
                }
                container.add(card,column++, row);
                GridPane.setMargin(card, new Insets(10));
                card.setCursor(Cursor.HAND);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        i++;
    }

 /*
    // METHODE JDIDA
    private List<Dons> recentlyAdded(){
        List<Dons> ld = new ArrayList<>();
        Dons dons = new Dons();
        dons.setTitle("abdesslam");
        dons.setDescription("Bahri");
        dons.setAmount(2000);
        ld.add(dons);
return ld;
    } */


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ADDBTN) {
            try {
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDons.fxml"));
                Parent root = loader.load();

                // Create a new scene with the loaded FXML file
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == STATSBTN) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
            ServiceDons serviceDons = new ServiceDons();
            List<Dons> donations = serviceDons.readAll();

            Chart chart = new Chart(donations);
            chart.displayChart();
        }
        if (actionEvent.getSource() == btnOverview) {
            loadDonations("");

            pnlOverview.setStyle("-fx-background-color : #f7f7f7");
            pnlOverview.toFront();
        }
        if (actionEvent.getSource() == MYDON) {
            try {
                container.getChildren().clear();
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MyDons.fxml"));
                Parent root = loader.load();

                // Add the ModifyDons view to the pnlUser pane
                pnlMyDon.getChildren().setAll(root);
                pnlMyDon.toFront();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource()== Home)
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Feed.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            pnlMyDon.getScene().setRoot(root);
        }
        i=0;
    }

}
