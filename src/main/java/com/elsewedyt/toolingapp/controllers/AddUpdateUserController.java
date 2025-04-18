package com.elsewedyt.toolingapp.controllers;

import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;
import com.elsewedyt.toolingapp.services.WindowUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
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
    ObservableList listComboUserRole;
    ObservableList listComboUserActive;
    int indexUsers = -1;
    boolean update = false;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
        logo_image_view.setImage(img);
        listComboUserRole = FXCollections.observableArrayList("Admin", "User");
        listComboUserActive = FXCollections.observableArrayList("Active", "Not Active");
        userRole_ComBox.setItems(listComboUserRole);
        userActive_ComBox.setItems(listComboUserActive);
    }

    public void setUserData(int emp_id, boolean update) {
        try {
            this.update = update;
            User us = UserDao.getUserByEmpId(emp_id);
            emp_id_txtF.setText(us.getEmp_id() + "");
            emp_id_txtF.setEditable(false);
            user_name_txtF.setText(us.getUsername());
            user_name_txtF.setEditable(false);
            password_passF.setText(us.getPassword());
            full_name_txtF.setText(us.getFullname());
            phone_txtF.setText(us.getPhone());
            int roleId = us.getRole();
            String roleStr = null;
            if (roleId == 1) {
                roleStr = "Admin";
            } else if (roleId == 0) {
                roleStr = "User";
            }
            int userRoleIndex = listComboUserRole.indexOf(roleStr);// roleStr = us.getRole() // اول ما اضغط على رو بيخزن رقم الصف فى متغير userRoleIndex
            userRole_ComBox.getSelectionModel().select(userRoleIndex);
            int activeId = us.getActive();
            String activeStr = null;
            if (activeId == 1) {
                activeStr = "Active";
            } else if (activeId == 0) {
                activeStr = "Not Active";
            }
            int userActiveIndex = listComboUserActive.indexOf(activeStr);
            userActive_ComBox.getSelectionModel().select(userActiveIndex);


        } catch (Exception ex) {
            logging.logException("ERROR", this.getClass().getName(), "setUserData  :", ex);
        }

    }

    public void setSaveButton() {

        saveUser_btn.setText("تعديل");
    }

    void addUserHelp() {
        try {
            Date currentDate = new Date();
            int emp_id = Integer.parseInt(emp_id_txtF.getText());
            String user_name = user_name_txtF.getText();
            String password = password_passF.getText();
            String fullname = full_name_txtF.getText();
            String phone = phone_txtF.getText();
            String creationDate = dateFormat.format(currentDate);
            String roleStr = userRole_ComBox.getSelectionModel().getSelectedItem();
            int roleInt = 0;
            if (roleStr.equals("Admin")) {
                roleInt = 1;
            } else if (roleStr.equals("User")) {
                roleInt = 0;
            }
            String activeStr = userActive_ComBox.getSelectionModel().getSelectedItem();
            int activeInt = WindowUtils.getUserActive(activeStr);

            User us = new User();
            us.setEmp_id(emp_id);
            us.setUsername(user_name);
            us.setPassword(password);
            us.setFullname(fullname);
            us.setPhone(phone);
            us.setRole(roleInt);
            us.setActive(activeInt);
            us.setCreation_date(creationDate);
            if (!UserDao.insertUser(us)) {
                done_lbl.setText("تم اضافة المستخدم بنجاح");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"خطا فى قواعد ملئ البيانات");
            logging.logException("ERROR", this.getClass().getName(), "addUserHelp", ex);
        }

    }

    void clearUserPage() {
        emp_id_txtF.clear();
        user_name_txtF.clear();
        password_passF.clear();
        full_name_txtF.clear();
        phone_txtF.clear();
        userRole_ComBox.getSelectionModel().select(-1);
        userActive_ComBox.getSelectionModel().select(-1);
    }

    public boolean UpdateUserHelp() {
        User us = null;
        try {
            int emp_id = Integer.parseInt(emp_id_txtF.getText());
            String password = password_passF.getText();
            String username = user_name_txtF.getText();
            String fullname = full_name_txtF.getText();
            String phone = phone_txtF.getText();
            String role = userRole_ComBox.getSelectionModel().getSelectedItem();
            int roleInt = WindowUtils.getUserRole(role);
            String active = userActive_ComBox.getSelectionModel().getSelectedItem();
            int activeInt = WindowUtils.getUserActive(active);

            us = new User();
            us.setEmp_id(emp_id);
            us.setUsername(username);
            us.setPassword(password);
            us.setFullname(fullname);
            us.setPhone(phone);
            us.setRole(roleInt);
            us.setActive(activeInt);

        } catch (Exception ex) {
            logging.logException("ERROR", this.getClass().getName(), "UpdateUserHelp", ex);
        }
        return !UserDao.updateUser(us);
    }


    @FXML
    void saveUserButton(ActionEvent event) {
        try {
            if (saveUser_btn.getText().contains("حفظ")) {
                addUserHelp();
                clearUserPage();
            } else if (saveUser_btn.getText().contains("تعديل")) {
                UpdateUserHelp();
                clearUserPage();
                done_lbl.setText("تم تعديل بيانات المستخدم");
            }

            if (!update) {
                addUserHelp();
                clearUserPage();
            } else if (update) {
                UpdateUserHelp();
                clearUserPage();
                done_lbl.setText("تم تعديل بيانات المستخدم");
            }
        } catch (Exception ex) {
            logging.logException("ERROR", this.getClass().getName(), "saveUserButton", ex);
        }
    }


}
