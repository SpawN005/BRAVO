package com.example.PIDEV;

import entity.User;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.UserManagement;
import utils.DataSource;

public class AdminInterfaceController implements Initializable {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, Integer> phoneNumberColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private Button blockUserBtn;
        private UserManagement userManagement;



    private ObservableList<User> userList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                Connection conn = DataSource.getInstance().getCnx();
        userManagement = new UserManagement(conn);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        userList.addAll(userManagement.getAllUsers()); // Add all users to the observable list
        System.out.println("userList contents: " + userList);

        userTable.setItems(userList);

        if (userList.isEmpty()) {
            System.out.println("User list is empty.");
        }
    }
}
