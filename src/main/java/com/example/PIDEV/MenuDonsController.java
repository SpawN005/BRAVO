package com.example.PIDEV;

import entity.Dons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader.*;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
import service.ServiceDons;
import service.ServiceDons;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuDonsController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button ADDBTN;

    @FXML
    private Button SETTINGS;

    @FXML
    private Button STATSBTN;
    @FXML
    private GridPane container;


    @FXML
    private Button USERID;


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
    private List<Dons> recentlyAdded;

    ServiceDons sd = new ServiceDons();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Dons> donations = sd.readAll();
        int column = 0;
        int row = 1;
        for (Dons donation : donations) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DonCard.fxml"));
                VBox card = loader.load();
                DonationInfoController controller = loader.getController();
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
        } /*


        List<Dons>l =sd.readAll();
        double  y = 60;
        double x =100;
        for (Dons d : l ){
            AnchorPane anchorPane = new AnchorPane();
            Label title=new Label(d.getTitle());
            Label description=new Label(d.getDescription());
            Label amount=new Label(String.valueOf(d.getAmount()));
            Label owner=new Label(d.getOwner());
            description.setWrapText(true);
            description.setMaxWidth(200);
            anchorPane.setLayoutX(x);
            title.setLayoutY(y);
            description.setLayoutY(y+10);
            amount.setLayoutY(y+20);
            owner.setLayoutY(y+36);;
            anchorPane.getChildren().addAll(title,description,amount,owner);
            anchorPane.setStyle("-fx-cursor: hand;");
            cardlayout.getChildren().addAll(anchorPane);
            x+=500; */


    } /*
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
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #f7f7f7");
            pnlOverview.toFront();
        }
        if (actionEvent.getSource() == USERID) {
            try {
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyDons.fxml"));
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
    }
}
