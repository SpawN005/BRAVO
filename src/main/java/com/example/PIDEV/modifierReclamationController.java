package com.example.PIDEV;

import entity.Reclamation;
import entity.TypeReclamation;
import entity.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.ServiceReclamation;
import service.ServiceUser;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class modifierReclamationController implements Initializable {
    @FXML
    private ComboBox<String> typeReclamationCBX;

    @FXML
    private TextField title;
    @FXML
    private TextArea description;

    @FXML
    private Button btn;
    @FXML
    private ImageView titlecheck;
    @FXML
    private ImageView textecheckvalue;
    private Reclamation reclamation;
    private ServiceReclamation sr = new ServiceReclamation();
    private Reclamation localreclamation;
    public   void SetReclamation(Reclamation r){
        ServiceReclamation sr = new ServiceReclamation();
        List<TypeReclamation> l= sr.afficherTypes();
        List<String> l1= new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {

            l1.add(l.get(i).getTypeReclamation());

        }


        typeReclamationCBX.setItems(FXCollections.observableArrayList(l1));
        typeReclamationCBX.setValue(r.getTypereclamation().getTypeReclamation());
        localreclamation=r;
        System.out.println("reclamtion---------------------"+localreclamation);

//        User u3 = new User("malik","rmedi",22442166,"malik.rmadi@gmil.com","simple user");
//        ServiceUser s =new ServiceUser();
      title.setText(r.getTitle());
      description.setText(r.getDescription());
      Date date_sql = new Date(Calendar.getInstance().getTime().getTime());

      reclamation = new Reclamation(localreclamation.getId(), r.getTitle(), r.getDescription(), date_sql, r.getEtat(), r.getOwnerID(), date_sql, r.getNote());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeReclamationCBX.setPromptText("Reclamation à propos de : ");
    }

    private void showAlert(String message ,boolean b) {
        Alert alert;
        if (b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
    public void modifierReclamationGUI(ActionEvent event) {
        Date date_sql = new Date(Calendar.getInstance().getTime().getTime());

        String text = title.getText();
        if (text.isEmpty()) {
            showAlert("Veuillez ajouter un titre",false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Le titre ne peut contenir ques des lettres et des espaces",false);
        }
        if (description.getText().isEmpty()) {
            showAlert("Veuillez ajouter une description",false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("La description ne peut contenir que des lettres et des espaces",false);
        }else {
            //titlecheck.setImage(new Image("@assets/checkMark.png"));
            //textecheckvalue.setImage(new Image("@assets/checkMark.png"));

            System.out.println("merci d'avoir bien redigé la reclamation ");
            User u2 = new User(7,"mariem","hammi",22442145,"mariem.hammi@gmil.com","simple user");
            User u3 = new User("malik","rmedi",22442166,"malik.rmadi@gmil.com","simple user");
            ServiceUser s =new ServiceUser();

            Reclamation r = new Reclamation();
            localreclamation.setTitle(title.getText());
            localreclamation.setDescription(description.getText());
           // r.setDate_creation(date_sql);
            localreclamation.setEtat("on hold");
            //r.setOwnerID(u3);
            localreclamation.setDate_treatment(date_sql);
            localreclamation.setNote(1);
            //.setId(55);
            sr.update(localreclamation);



            Stage stage = (Stage) title.getScene().getWindow();
            stage.close();

        }

    }







}









