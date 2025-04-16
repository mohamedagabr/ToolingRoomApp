package com.elsewedyt.toolingapp.controllers;
import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
public class AddUpdateUserController implements Initializable {
    @FXML
    private ImageView logo_image_view;
    @FXML
    private TextField emp_id_txtF;
    @FXML
    private TextField user_name_txtF;
    @FXML
    private PasswordField password_passF;
    @FXML
    private TextField full_name_txtF;
    @FXML
    private TextField phone_txtF;
    @FXML
    private Label done_lbl;
    @FXML
    private ComboBox<String> userRole_ComBox;
    @FXML
    private ComboBox<String> userActive_ComBox;
    @FXML
    private Button saveUser_btn;
    int updatedUserId = 0;
    ObservableList listComboRole ;
    ObservableList listComboActive ;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_image_view.setImage(img);
        listComboRole = FXCollections.observableArrayList("Admin","User");
        listComboActive = FXCollections.observableArrayList("Active","Not Active");
        userRole_ComBox.setItems(listComboRole);
        userActive_ComBox.setItems(listComboActive);
    }
    public void setUserData(User us) {
        updatedUserId = us.getId();
        emp_id_txtF.setText(us.getEmp_id()+ "");
        user_name_txtF.setText(us.getUsername());
        user_name_txtF.setEditable(false);
        password_passF.setText(us.getPassword());
        full_name_txtF.setText(us.getFullname());
        phone_txtF.setText(us.getPhone());
      //  userRole_ComBox.getSelectionModel().getSelectedItem().toString();
       // userActive_ComBox.getSelectionModel().getSelectedItem().toString();

    }
    public void setUpdate(boolean update) {

    }
    public void setSaveButton(){
        saveUser_btn.setText("تعديل");
    }
    void saveUserHelp(){
        int roleInt = 0;
        int activeInt = 0;
        Date currentDate = new Date();
        int emp_id = Integer.parseInt(emp_id_txtF.getText());
        String user_name = user_name_txtF.getText();
        String password = password_passF.getText();
        String fullname = full_name_txtF.getText();
        String phone = phone_txtF.getText();
        String creationDate = dateFormat.format(currentDate);

        String roleStr = userRole_ComBox.getSelectionModel().getSelectedItem();
        if(roleStr.equals("Admin")){
            roleInt = 1 ;
        }else if (roleStr.equals("User")){
            roleInt = 0 ;
        }
       String activeStr = userActive_ComBox.getSelectionModel().getSelectedItem();
      if(activeStr.equals("Active")){
          activeInt = 1 ;
        }else if (activeStr.equals("Not Active")){
           activeInt = 0 ;
        }
        User us = new User();
        us.setEmp_id(emp_id);
        us.setUsername(user_name);
        us.setPassword(password);
        us.setFullname(fullname);
        us.setPhone(phone);
        us.setRole(roleInt);
        us.setActive(activeInt);
        us.setCreation_date(creationDate);
        if(!UserDao.insertUser(us)){
         done_lbl.setText("تم اضافة المستخدم بنجاح");
        }

    }
    void clearUserPage(){
        emp_id_txtF.clear();
        user_name_txtF.clear();
        password_passF.clear();
        full_name_txtF.clear();
        phone_txtF.clear();
        userRole_ComBox.getSelectionModel().select(-1);
        userActive_ComBox.getSelectionModel().select(-1);
    }
    @FXML
    void saveUser(ActionEvent event) {
        saveUserHelp();
        clearUserPage();
    }
    void updateUserHelp(){
     //   int id =

    }



}
