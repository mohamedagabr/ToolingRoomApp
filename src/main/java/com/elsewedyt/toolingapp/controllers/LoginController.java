package com.elsewedyt.toolingapp.controllers;
import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;
//import com.elsewedyt.toolingapp.Logging.logging.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

import static com.elsewedyt.toolingapp.Logging.logging.*;
import static com.elsewedyt.toolingapp.services.WindowUtils.ALERT;
import static com.elsewedyt.toolingapp.services.SettingService.APP_BUNDLE;
import static com.elsewedyt.toolingapp.services.WindowUtils.ALERT_ERROR;


import java.net.URL;
import java.util.ResourceBundle;

//import static com.elsewedyt.toolingapp.Logging.logging.ERROR;

public class LoginController implements Initializable {
    @FXML
    private ImageView logo_image_view;
    @FXML
    private ImageView join_ImageView;
    @FXML
    private ImageView tools_img_view;
    @FXML
    private TextField user_name_txtF;

    @FXML
    private PasswordField password_passF;

    public void initialize(URL url, ResourceBundle rb) {
        Image logoImg = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_image_view.setImage(logoImg);
        Image joinImg = new Image(LoginController.class.getResourceAsStream("/images/join.png"));
        join_ImageView.setImage(joinImg);
        Image toolsImg = new Image(LoginController.class.getResourceAsStream("/images/tools03.png"));
        tools_img_view.setImage(toolsImg);


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
                stage.setResizable(false);

            }else{
                ALERT("",APP_BUNDLE().getString("Login Error"),ALERT_ERROR);
                logMessage(ERROR,this.getClass().getName(),"loginHelp","Connection Error");
            }
        } catch (Exception ex) {
            //System.out.println("loginHelp :" + ex);
            logging.logException("ERROR", this.getClass().getName(), "loginHelp", ex);
        }
    }

    @FXML
    void login(ActionEvent event) {
        try {
            loginHelp();
        }catch (Exception ex){
        logging.logException("ERROR", this.getClass().getName(), "login", ex);
        JOptionPane.showMessageDialog(null,"خطأ فى اسم المستخدم او كلمة المرور");
        }
    }
    @FXML
    void enterLogin(ActionEvent event) {
        try {
            loginHelp();
        }catch (Exception ex){
            logging.logException("ERROR", this.getClass().getName(), "enterLogin", ex);
            JOptionPane.showMessageDialog(null,"خطأ فى اسم المستخدم او كلمة المرور");
        }
    }

}

