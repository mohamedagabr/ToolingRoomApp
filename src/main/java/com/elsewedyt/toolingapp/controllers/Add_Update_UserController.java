package com.elsewedyt.toolingapp.controllers;
import com.elsewedyt.toolingapp.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
public class Add_Update_UserController implements Initializable {
    @FXML
    private ImageView logo_image_view;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_image_view.setImage(img);
    }
    public void setUserData(User us) {

    }
    public void setSaveButton(){
        //save_btn.setText("تعديل");  Move to setUserData
    }
}
