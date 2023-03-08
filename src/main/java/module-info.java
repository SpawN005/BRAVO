module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.media;
    requires javafx.swing;
    requires org.jfree.jfreechart;




    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;





    requires kaptcha;


    requires org.apache.commons.lang3;
    requires java.net.http;

    requires kernel;
    requires layout;
    requires com.fasterxml.jackson.databind;


    requires java.desktop;
    requires java.activation;
    requires com.google.zxing;
    requires javax.mail.api;

    opens com.example.PIDEV to javafx.fxml;
    exports com.example.PIDEV;
    exports utils;
    opens utils to javafx.fxml;
    exports service;
    opens service to javafx.fxml;
    exports entity;
    opens entity to javafx.fxml;



}