package com.elsewedyt.toolingapp.controllers;

import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


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

    @FXML
    void Login(MouseEvent event) {
    User user = UserDao.checkLogin(user_name_txtF.getText(),password_passF.getText());
    try {
        if(user!=null){
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/screens/Main.fxml"));
            Parent parent = fxmlLoader.load(); // Load the FXML and get the root
            MainControlller maincontroller = fxmlLoader.getController();
            maincontroller.setLoginUser(user);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }catch (Exception ex){
        logging.logException("ERROR",this.getClass().getName(),"Login",ex);
    }
    }

    @FXML
    void enterLogin(ActionEvent event) {

    }

}
