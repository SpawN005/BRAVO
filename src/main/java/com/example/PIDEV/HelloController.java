package com.example.PIDEV;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
          String c1 ="";
        DataSource ds=new DataSource();
        try{
            Statement statement = ds.getCnx().createStatement();
            ResultSet resultSet =statement.executeQuery("Select * from personne");
            while(resultSet.next()){
                c1= resultSet.getString("nom");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        welcomeText.setText(c1);
    }
}