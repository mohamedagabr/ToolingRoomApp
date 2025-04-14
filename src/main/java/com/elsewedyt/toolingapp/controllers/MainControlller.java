package com.elsewedyt.toolingapp.controllers;

import com.elsewedyt.toolingapp.models.User;
import com.elsewedyt.toolingapp.models.UserContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainControlller implements Initializable {
    @FXML
    private ImageView logo_ImageView;
    @FXML
    private Label welcome_lbl;

    @FXML
    private Button control_btn;

    @FXML
    private Label date_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_ImageView.setImage(img);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  hh:mm a");
        date_lbl.setText(dateFormat.format(date) +" ");
         //String date = LocalDate.now() + "";
        //date_lbl.setText("تاريخ اليوم : " + date);
        //String msg = "مرحبا : " + UserContext.getCurrentUser().getFullname();
        //welcome_lbl.setText(msg);


    }
   public  void setLoginUser(User user){
    welcome_lbl.setText("مرحبا : "+user.getFullname());
    }

    @FXML
    void Open_Control_Page(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/screens/ControlPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Control Page");
            Rectangle2D rd = Screen.getPrimary().getVisualBounds();
            stage.setX((rd.getWidth() - stage.getWidth()) / 2);
            stage.setY((rd.getHeight() - stage.getHeight()) / 2);
            stage.setResizable(true);
        } catch (Exception ex) {
        }
    }

}
