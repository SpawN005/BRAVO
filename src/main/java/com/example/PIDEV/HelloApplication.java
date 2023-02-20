package com.example.PIDEV;
import entity.CommentaireBlog;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceUser;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("Feed Page!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        User u = new User(28,"Yousra","Lakhneche",28282828,"youou","role28");
        ServiceUser su = new ServiceUser();

        //su.insert(u);
        //Blog b = new Blog("test","test123","abcdefgh",u);
        //ServiceBlog sb= new ServiceBlog();

        //CommentaireBlog c = new CommentaireBlog("asslema",61,u);





        launch();
    };


}