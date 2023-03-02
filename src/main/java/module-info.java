module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    requires kernel;
    requires layout;
    requires io;

    opens com.example.PIDEV to javafx.fxml;
    exports com.example.PIDEV;
    exports utils;
    opens utils to javafx.fxml;
    exports service;
    opens service to javafx.fxml;
    exports entity;
    opens entity to javafx.fxml;
}