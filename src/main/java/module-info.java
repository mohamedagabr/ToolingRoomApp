module com.elsewedyt.toolingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;
    requires itextpdf;
    requires java.naming;
   requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires pdfbox.app;
    requires controlsfx;
    requires mail;
    requires activation;
    requires jbcrypt;
    requires org.apache.logging.log4j;


    opens com.elsewedyt.toolingapp.controllers to javafx.fxml;
    opens screens to javafx.fxml;
    exports com.elsewedyt.toolingapp.controllers;
    exports com.elsewedyt.toolingapp.models;

}