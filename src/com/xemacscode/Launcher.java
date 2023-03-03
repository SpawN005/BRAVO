
package com.xemacscode;

import entite.Role;
import entite.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.MailService;
import service.ServicePersonne;
import service.Servicerole;
import service.UserManagement;
import utils.DataSource;




public class Launcher extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
                //Parent root = FXMLLoader.load(getClass().getResource("AdminInterface.fxml"));        


        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        //User newUser2 = new User("Omarss", "testedir", 1234546789, "omadr@example.com", "admin", "password123");

        
         //User newUser = new User("Omar", "testeir", 123456789, "omar@example.com", "admin", "password123");
        ServicePersonne service = new ServicePersonne() {};
        Servicerole servicer = new Servicerole() {};
                //System.out.println(newUser2.getId());


       // System.out.println(newUser);
        //service.insertPst(newUser2);

                Connection conn = DataSource.getInstance().getCnx();

                UserManagement userManager = new UserManagement(conn);

                
        //service.deleteUserByEmail("johndoe@example.com");
        //System.out.println(newUser);
    MailService mailService = new MailService();
    //mailService.envoyer("a.rebhy10@gmail.com", "test");
        launch(args);
       
        //service.delete(newUser);

    }
    
}
