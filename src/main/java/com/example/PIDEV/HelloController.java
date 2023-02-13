package com.example.PIDEV;

import entity.Oeuvre;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.ServiceOeuvre;
import utils.DataSource;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Oeuvre o = new Oeuvre("korfi","alaman","ahmed","mohamed");
        ServiceOeuvre so=new ServiceOeuvre();
        List<Oeuvre> l=so.readAll();



        welcomeText.setText(l.get(1).getOwner());
    }
}