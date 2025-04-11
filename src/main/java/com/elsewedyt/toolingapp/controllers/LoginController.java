package com.elsewedyt.toolingapp.controllers;
import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;
import com.elsewedyt.toolingapp.services.WindowUtils;
//import com.elsewedyt.toolingapp.Logging.logging.*;
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


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static com.elsewedyt.toolingapp.Logging.logging.ERROR;

//import static com.elsewedyt.toolingapp.Logging.logging.ERROR;

public class LoginController implements Initializable {
    @FXML
    private ImageView logo_image_view;
    @FXML
    private ImageView join_ImageView;
    @FXML
    private TextField user_name_txtF;

    @FXML
    private PasswordField password_passF;

    public void initialize(URL url, ResourceBundle rb) {
        Image logoImg = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_image_view.setImage(logoImg);
        Image joinImg = new Image(LoginController.class.getResourceAsStream("/images/join.png"));
        join_ImageView.setImage(joinImg);
    }
    private void loginHelp() {
        User user = UserDao.checkLogin(user_name_txtF.getText(), password_passF.getText());
        try {
            if (user != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/screens/Main.fxml"));
                Parent parent = fxmlLoader.load(); // Load the FXML and get the root
                MainControlller maincontroller = fxmlLoader.getController();
                maincontroller.setLoginUser(user);
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
                //JOptionPane.showMessageDialog(null,"Hellow You Are Connected");
            }else{
                System.out.println("Error : " + -1);
            }
        } catch (Exception ex) {
            //System.out.println("loginHelp :" + ex);
            logging.logException("ERROR", this.getClass().getName(), "Login", ex);
        }
    }

    @FXML
    void login(ActionEvent event) {
        loginHelp();
    }
    @FXML
    void enterLogin(ActionEvent event) {
        loginHelp();
    }


}

