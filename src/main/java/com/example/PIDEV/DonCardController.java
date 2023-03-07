package com.example.PIDEV;

import entity.Dons;
import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import service.EmailService;
import service.LoggedInUser;
import service.ServiceDons;

import java.time.LocalDate;

public class DonCardController {

    @FXML
    private Label DonAmount;

    @FXML
    private Label DonDesc;

    @FXML
    private Label DonExp;

    @FXML
    private Label DonTitle;
    @FXML
    private VBox box;
    private User user;
    private String[] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};


    public void setData(Dons dons) {
        DonTitle.setText(dons.getTitle());
        DonDesc.setText(dons.getDescription());
        DonAmount.setText(String.valueOf(dons.getAmount()));
        DonExp.setText(String.valueOf(dons.getDate_expiration().toString()));




   /* public void TestEmail(Dons dons){
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = dons.getDate_expiration();
        long daysUntilExpiration = currentDate.until(expirationDate).getDays();
        if (daysUntilExpiration > 2) {
            System.out.println("il y a");
            EmailService emailService = new EmailService();
            emailService.envoyer("aimen.majoul@gmail.com");
        }
    } */


  /*  LocalDate currentDate = LocalDate.now();&

    LocalDate expirationDate = Dons.getDate_expiration();
    long daysUntilExpiration = currentDate.until(expirationDate).getDays(); */
        box.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";" +
                " -fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10, 0 , 0 ,10);");
    }}

