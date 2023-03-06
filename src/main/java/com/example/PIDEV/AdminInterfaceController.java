package com.example.PIDEV;

import entity.User;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceUser;
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
    private UserManagement userManagement ;
    private User user;



    private ObservableList<User> userList = FXCollections.observableArrayList();
    @FXML
    private Button blockButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userManagement= new UserManagement();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        System.out.println(userManagement.getAllUsers());
        userList.addAll(userManagement.getAllUsers()); // Add all users to the observable list
        System.out.println("userList contents: " + userList);

        userTable.setItems(userList);

        if (userList.isEmpty()) {
            System.out.println("User list is empty.");
        }
    }

    void setUser(User user) {
        this.user = user;
    }


    @FXML
    public void blockSelectedUser(ActionEvent event) {
        // Get the selected user from the table view
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        ServiceUser service = new ServiceUser();

        if (selectedUser != null) {service.blockUser(selectedUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("The account with this Email : " +selectedUser.getEmail() +  "\nhas been blocked!");
            alert.showAndWait();
        }

        else {

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No User Selected");
            alert.setHeaderText("Please select a user to delete");
            alert.showAndWait();


        }

    }


    @FXML
    void deleteAcc(ActionEvent event) {

        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        ServiceUser service = new ServiceUser();


        if (selectedUser != null) {
            userTable.getItems().remove(selectedUser);
            service.delete(selectedUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("The account with this Email : " +selectedUser.getEmail() +  "\nhas been deleted!");
            alert.showAndWait();
        } else {
            // No user selected, show an alert
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No User Selected");
            alert.setHeaderText("Please select a user to delete");
            alert.showAndWait();
        }

    }







}
