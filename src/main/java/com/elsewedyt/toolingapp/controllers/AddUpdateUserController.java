package com.elsewedyt.toolingapp.controllers;
import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
public class AddUpdateUserController implements Initializable {
    @FXML
    private ImageView logo_image_view;
    @FXML
    private TextField emp_id_txtF;

    @FXML
    private TextField user_name_txtF;

    @FXML
    private TextField user_pass_txtF;

    @FXML
    private TextField full_name_txtF;

    @FXML
    private TextField phone_txtF;
    @FXML
    private Label done_lbl;
    @FXML
    private ComboBox<Integer> userRole_ComBox;
    @FXML
    private ComboBox<Integer> userActive_ComBox;
    ObservableList listComboRole ;
    ObservableList listComboActive ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_image_view.setImage(img);
        listComboRole = FXCollections.observableArrayList("Admin", "User");
        listComboActive = FXCollections.observableArrayList("Active", "Not Active");
        userRole_ComBox.setItems(listComboRole);
        userActive_ComBox.setItems(listComboActive);
    }
    public void setUserData(User us) {
    }
    public void setUpdate(boolean update) {

    }
    public void setSaveButton(){
        //save_btn.setText("تعديل");  Move to setUserData
    }

    @FXML
    void saveUser(ActionEvent event) {
        int roleInt = 0;
        int activeInt = 0;
       int emp_id = Integer.parseInt(emp_id_txtF.getText());
       String user_name = user_name_txtF.getText();
       String password = user_pass_txtF.getText();
       String fullname = full_name_txtF.getText();
       String phone = phone_txtF.getText();
       String roleStr = userRole_ComBox.getSelectionModel().getSelectedItem().toString();
       if(roleStr.equals("Admin")){
           activeInt = 1 ;
       }else if (roleStr.equals("User")){
           activeInt = 0 ;
       }
       String activeStr = userActive_ComBox.getSelectionModel().getSelectedItem().toString();
       if(roleStr.equals("Active")){
           roleInt = 1 ;
       }else if (roleStr.equals("Not Active")){
           roleInt = 0 ;
       }
        User us = new User();
        us.setEmp_id(emp_id);
        us.setUsername(user_name);
        us.setPassword(password);
        us.setFullname(fullname);
        us.setPhone(phone);
        us.setRole(roleInt);
        us.setActive(activeInt);
        if(!UserDao.insertUser(us)){
         done_lbl.setText("تم اضافة المستخدم بنجاح");
        }
    }

}
