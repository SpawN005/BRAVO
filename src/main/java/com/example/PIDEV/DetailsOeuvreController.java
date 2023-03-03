package com.example.PIDEV;
import entity.NoteOeuvre;
import org.controlsfx.control.Rating;
import entity.CommentaireOeuvre;
import entity.Oeuvre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import service.ServiceCommentaireOeuvre;
import service.ServiceNoteOeuvre;
import service.ServiceOeuvre;


public class DetailsOeuvreController implements Initializable {

    @FXML
    private Label back;


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Text descriptionDetail;
    @FXML
    private TextArea ta;

    @FXML
    private AnchorPane leftAnchor;

    @FXML
    private ImageView mainImage;

    @FXML
    private Text owner;

    @FXML
    private AnchorPane rightAnchor;

    @FXML
    private Text title;
    @FXML
    private AnchorPane commentAnchor;
    private ServiceOeuvre so;
    private Oeuvre oeuvre;

    private ServiceCommentaireOeuvre sco;
    private ServiceNoteOeuvre sno;
    Rating rating = new Rating();
    NoteOeuvre no;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnMouseClicked(e->backwardButton());
        back.setStyle("-fx-cursor: hand;");

        rating.setMax(5);







    }

    public void SetOeuvre(Oeuvre o) {
        ServiceNoteOeuvre sno = new ServiceNoteOeuvre();
        rating.setRating(sno.readAvg(o));
        double y=0;
        descriptionDetail.setText(o.getDescription());
        descriptionDetail.setWrappingWidth(400);
        title.setText(o.getTitle());
        owner.setText(o.getOwner());
        Image image = new Image("file:C:/xampp/htdocs/img/"+o.getUrl());
        mainImage.setImage(image);
        oeuvre = new Oeuvre(o.getId(),o.getTitle(),o.getDescription(),o.getOwner(),o.getCategory(),o.getUrl());
        sco = new ServiceCommentaireOeuvre();
        List<CommentaireOeuvre> l = sco.readByOeuvre(oeuvre);
        rightAnchor.getChildren().add(rating);
    rating.setLayoutX(245);
        rating.setLayoutY(125);
        for (CommentaireOeuvre co:l){

            Label comment = new Label(co.getComment());

            comment.setLayoutY(y);

            y+=20;

            commentAnchor.getChildren().addAll(comment);



        }

        ta.setWrapText(true);
        ta.setOnKeyPressed(e-> {
            if (e.getCode() == KeyCode.ENTER){


                sco.insert(new CommentaireOeuvre(oeuvre,    1,ta.getText(),new Timestamp(System.currentTimeMillis())));
                ta.setText("");
                FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailsOeuvre.fxml"));
                Parent root= null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                DetailsOeuvreController dc=loader.getController();
                dc.SetOeuvre(oeuvre);
                title.getScene().setRoot(root);

            }
        });
        rating.setOnMouseClicked(e-> {
            no = new NoteOeuvre(oeuvre,1,rating.getRating());
            sno.insert(no);
        });
    }
    protected void backwardButton() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        leftAnchor.getScene().setRoot(root);

    }


    public void modify(ActionEvent actionEvent) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("modifyOeuvre.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ModifyOeuvreController moc = loader.getController();
        moc.SetOeuvre(oeuvre);
        title.getScene().setRoot(root);

    }
    @FXML
    public void delete(ActionEvent actionEvent) {
        so = new ServiceOeuvre();
        sco.deletebyOeuvre(oeuvre);
        so.delete(oeuvre);


        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        title.getScene().setRoot(root);


    }
}
