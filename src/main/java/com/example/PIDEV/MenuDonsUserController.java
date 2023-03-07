package com.example.PIDEV;

import entity.Dons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import service.ServiceDons;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuDonsUserController implements Initializable {

    @FXML
    private Button SETTINGS;

    @FXML
    private TextField Searchfield;

    @FXML
    private Button btnOverview;

    @FXML
    private VBox cardlayout;

    @FXML
    private GridPane container;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlMyDon;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private ImageView searchButton;


    ServiceDons sd = new ServiceDons();

    public void initialize(URL location, ResourceBundle resources) {
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
        VBox card;
        FXMLLoader loader=null;
        for (Dons donation : searchResults) {
            try {
                if ( donation.getAmount() ==0 ){
                    loader = new FXMLLoader(getClass().getResource("DonCard.fxml"));
                    card = loader.load();
                    DonCardController controller = loader.getController();
                    controller.setData(donation);

                }else {
                     loader = new FXMLLoader(getClass().getResource("DonCardUser.fxml"));
                     card = loader.load();
                    DonCardUserController controller = loader.getController();
                    controller.setData(donation);
                }



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
        if (actionEvent.getSource() == btnOverview) {
            loadDonations("");

            pnlOverview.setStyle("-fx-background-color : #f7f7f7");
            pnlOverview.toFront();
        }

    }
}
