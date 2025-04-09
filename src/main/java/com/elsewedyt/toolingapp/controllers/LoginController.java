package com.elsewedyt.toolingapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ImageView logo_image_view;

    @FXML
    private TextField user_name_txtF;

    @FXML
    private PasswordField password_passF;

    @FXML
    private Button login_btn;



public void initialize(URL url, ResourceBundle rb) {
    Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
    logo_image_view.setImage(img);

    }
//    @FXML
//    void Login(MouseEvent event) {
//
//    }
//
//    @FXML
//    void enterLogin(ActionEvent event) {
//
//    }


    @FXML
    void Login(MouseEvent event) {

    }

    @FXML
    void enterLogin(ActionEvent event) {

    }

}
