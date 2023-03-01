package com.example.PIDEV;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import service.ServiceEvent;
import entity.Event;
import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {

    ServiceEvent SE = new ServiceEvent();
    Event e = new Event();
    @FXML
    void search(ActionEvent event) {
        SE.filterByType(e.getType_event());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
