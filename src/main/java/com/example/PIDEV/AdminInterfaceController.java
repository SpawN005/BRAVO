package com.example.PIDEV;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceUser;
import service.UserManagement;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

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
    @FXML
    private Button PDFBtn;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> filterComboBox;


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
        ObservableList<String> items = FXCollections.observableArrayList(
                "All Users",
                "Guest",
                "Artist"
        );
        filterComboBox.setValue("All Users");

        filterComboBox.setItems(items);
        filterComboBox.valueProperty().addListener((observable, oldValue, selectedRole) -> {
            if (selectedRole.equals("All Users")) {
                userList.setAll(userManagement.getAllUsers());
            } else {
                userList.setAll(userManagement.getUsersByRole(selectedRole));
            }
        });
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


    @FXML
    private void handleSearch() {

        String searchText = searchField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            // If search bar is empty, show all users
            userTable.setItems(FXCollections.observableArrayList(userList));
        } else {
            // Filter user list based on search text
            ObservableList<User> filteredList = FXCollections.observableArrayList();
            for (User user : userList) {
                if (user.getFirstName().toLowerCase().contains(searchText)||
                user.getLastName().toLowerCase().contains(searchText)||
                user.getEmail().toLowerCase().contains(searchText) ||
                        user.getRole().toString().toLowerCase().contains(searchText)) {
                    filteredList.add(user);
                }
            }
            userTable.setItems(filteredList);
        }
}
    private void generatePdf(ObservableList<User> userList) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\PDF\\users.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph title = new Paragraph("User List", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add headers to the table
        Stream.of("ID", "First Name", "Last Name", "Email", "Phone Number", "Role")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    table.addCell(header);
                });

        // Add rows to the table
        for (User user : userList) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getId()))));
            table.addCell(new PdfPCell(new Phrase(user.getFirstName())));
            table.addCell(new PdfPCell(new Phrase(user.getLastName())));
            table.addCell(new PdfPCell(new Phrase(user.getEmail())));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getPhoneNumber()))));
            table.addCell(new PdfPCell(new Phrase(user.getRole())));
        }

        document.add(table);


        Image image = null;
        Image logo = null;

        try {
            image = Image.getInstance("C:\\xampp\\htdocs\\img\\esprit.jpg");
            logo = Image.getInstance("C:\\xampp\\htdocs\\img\\logo.png");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image.setAbsolutePosition(5f, 50f);
        logo.setAbsolutePosition(480f, 50f);
        // set the size of the image
        image.scaleAbsolute(200f, 80f); // width and height in points
        logo.scaleAbsolute(100f, 90f); // width and height in points

        document.add(image);
        document.add(logo);
        document.close();
    }


    @FXML
    private void handlePDFBtnClick() {
        try {
            generatePdf(userList);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("PDF generated");
            alert.setHeaderText(null);
            alert.setContentText("PDF file has been generated successfully!");
            alert.showAndWait();
        } catch (FileNotFoundException | DocumentException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to generate PDF file.");
            alert.showAndWait();
        }
    }}
