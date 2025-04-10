package com.elsewedyt.toolingapp.controllers;

import com.elsewedyt.toolingapp.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainControlller implements Initializable {
    @FXML
    private ImageView logo_ImageView;

    @FXML
    private TextField welcome_txtF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_ImageView.setImage(img);
    }
    public  void setLoginUser(User user){
        welcome_txtF.setText(user.getFullname());
    }
}
