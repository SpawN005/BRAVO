package com.example.PIDEV;
import entity.Blog;
import entity.CommentaireBlog;
import entity.NoteBlog;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceBlog;
import service.ServiceCommentaireBlog;
import service.ServiceNoteBlog;
import service.ServiceUser;
import utils.DataSource;

import java.io.IOException;

public class HelloApplication extends Application {
   @Override

    /*public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DetailsBlog1.0.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
       stage.setTitle("Feed Page!");
        stage.setScene(scene);
        stage.show();
    }*/

    /*public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ModifierBlog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("Feed Page!");
        stage.setScene(scene);
        stage.show();
    }*/


   /*public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AffichageBlog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("Feed Page!");
        stage.setScene(scene);
        stage.show();
    }*/



   public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddBlog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 770);
        stage.setTitle("Feed Page!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        DataSource ds1 =DataSource.getInstance();
        System.out.println(ds1);

        ServiceUser u= new ServiceUser();
        ServiceBlog b = new ServiceBlog();
        ServiceCommentaireBlog cb= new ServiceCommentaireBlog();
        ServiceNoteBlog snb = new ServiceNoteBlog();

        User us1 = new User("myriam","hammi",24242424,"myriam.hammi@gmail.com","role1");
        User us2 = new User("feriel","sahbi",28282828, "feriel.sahbi@gmail.com","role2");

        /*u.insert(us1);*/
        /*u.insert(us2);*/
//        System.out.println(u.readAll());
//        System.out.println(u.readById(26));

         Blog b1=new Blog("test1","testtest","test123","C:\\Users\\radhy\\OneDrive\\Bureau\\PiDev\\BRAVO\\src\\main\\resources\\com\\example\\PIDEV\\assets\\cropped-creativespirit-lr-copy.jpg",u.readById(30));
         Blog b2 = new Blog("test2","testtesttest","test1234","C:\\Users\\radhy\\OneDrive\\Bureau\\PiDev\\BRAVO\\src\\main\\resources\\com\\example\\PIDEV\\assets\\index.png",u.readById(31));

        //b.insert(b1); //c'est bon
        //b.insert(b2); //c'est bon
        //System.out.println(b.readAll()); //c'est bon
      // System.out.println(b.readById(82)); //c'est bon
        // b.delete(b.readById(83)); // c'est bon
 //         b.update(b1); //c'est bon*
       // System.out.println(b.recherchePartitre("dianes painting blog"));


         CommentaireBlog cb1= new CommentaireBlog("woow",b.readById(93),u.readById(30));
         CommentaireBlog cb2= new CommentaireBlog("merci",b.readById(93),u.readById(30));

//           cb.insert(cb1); //c'est bon
//           cb.insert(cb2); //c'est bon
//           System.out.println(cb.readAll()); // c'est bon
//           System.out.println(cb.readById(7)); // c'est bon
//           cb.update(cb1); / c'est bon
//           cb.delete(cb.readById(7)); //c'est bon


         NoteBlog nb1 = new NoteBlog(6,4,b.readById(93));
        //NoteBlog nb2 = new NoteBlog(3,b.readById(79));

//            snb.insert(nb1); // c'est bon
//            snb.insert(nb2); // c'est bon
//            System.out.println(snb.readAll()); //c'est bon
//            System.out.println(snb.readById(2)); c'est bon

//            snb.update(nb1); // c'est bon
//            System.out.println("nouvelle note  "+nb1.getNote());
//            snb.delete(snb.readById(4)); //c'est bon


        launch();
    }


}