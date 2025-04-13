package com.elsewedyt.toolingapp.controllers;

import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;
import com.elsewedyt.toolingapp.models.UserContext;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControlPageController implements Initializable {
    @FXML
    private ImageView logo_ImageView;
    @FXML
    private Label welcome_lbl;
    @FXML
    private Label date_lbl;
    @FXML
    private TableView<User> users_tbl_view;

    @FXML
    private TableColumn<User, Integer> empId_colm;

    @FXML
    private TableColumn<User, String> username_colm;

    @FXML
    private TableColumn<User, String> fullname_colm;

    @FXML
    private TableColumn<User, Integer> phone_colm;

    @FXML
    private TableColumn<User, Integer> roleGroup_colm;

    @FXML
    private TableColumn<User, Integer> active_colm;

    @FXML
    private TableColumn<User, String> creationDate_colm;

    @FXML
    private TableColumn<User, String> edit_colm;
    @FXML
    private TextField searchUsers_txtF;

    ObservableList<User> listUsers ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // fontawTools_lbl.setGraphic(new FontAwesomeIconView("fas-screwdriver-wrench"));

       Image img = new Image(LoginController.class.getResourceAsStream("/images/company_logo.png"));
       logo_ImageView.setImage(img);
       String date = LocalDate.now() + "";
       date_lbl.setText("تاريخ اليوم : " + date);
       try {
           if (UserContext.getCurrentUser() != null) {
           String msg = ("مرحبا : " + UserContext.getCurrentUser().getFullname());
               welcome_lbl.setText(msg);
           }
       }catch (Exception ex){
           logging.logException("ERROR", this.getClass().getName(), "initialize", ex);
           System.out.println(ex);
       }
    loadData();
    listUsers = UserDao.getUsers();
    users_tbl_view.setItems(listUsers);

    }

    private void loadData() {
        empId_colm.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        username_colm.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullname_colm.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        phone_colm.setCellValueFactory(new PropertyValueFactory<>("phone"));
        roleGroup_colm.setCellValueFactory(new PropertyValueFactory<>("role"));
        active_colm.setCellValueFactory(new PropertyValueFactory<>("active"));
        creationDate_colm.setCellValueFactory(new PropertyValueFactory<>("creation_date"));

        empId_colm.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;-fx-font-weight:bold;");
        username_colm.setStyle("-fx-alignment: CENTER;-fx-font-size:12px;-fx-font-weight:bold;");
        fullname_colm.setStyle("-fx-alignment: CENTER;");
        phone_colm.setStyle("-fx-alignment: CENTER;");
        roleGroup_colm.setStyle("-fx-alignment: CENTER;");
        active_colm.setStyle("-fx-alignment: CENTER;");
        creationDate_colm.setStyle("-fx-alignment: CENTER;");

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String user, boolean empty) {
                    super.updateItem(user, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:24px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:24px;"
                                        + "-fx-fill:#00E676;"
                        );


                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            Stage stagemain = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stagemain.close();

                            User us = users_tbl_view.getSelectionModel().getSelectedItem();
                            openEditUserPage(us);
                        });
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            // code
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new javafx.geometry.Insets(2, 2, 0, 3));
                        HBox.setMargin(deleteIcon, new javafx.geometry.Insets(2, 2, 0, 3));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        edit_colm.setCellFactory(cellFoctory);
        users_tbl_view.setItems(listUsers);
    }

    private void openEditUserPage(User us){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/Screens/NewUser.fxml"));
            Parent parent = fxmlLoader.load(); // Load the FXML and get the root
            Add_Update_UserController add_update_user_controller = fxmlLoader.getController();
            add_update_user_controller.setUserData(us);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("تعديل مستخدم");
            stage.show();
            add_update_user_controller.setSaveButton();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    @FXML
    void search_Users(KeyEvent event) {
        FilteredList<User> filteredData = new FilteredList<>(listUsers, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        // id of search TextField
        searchUsers_txtF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {  // object from Meals
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;    }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                String empId = user.getId() + "";
                String role = user.getRole() + "";
                String active = user.getActive() + "";
                //String username = user.getUsername() + "";
                if (user.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (user.getFullname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getCreation_date().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (empId.contains(lowerCaseFilter)) {
                    return true;
                }else if (role.contains(lowerCaseFilter)) {
                    return true ;
                }else if (active.contains(lowerCaseFilter)) {
                    return true ;
                }
                return false;      }); // Does not match.
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<User> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(users_tbl_view.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        users_tbl_view.setItems(sortedData);
    }
    @FXML
    void addUser(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/screens/Add_Update_User.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Control Page");
            Rectangle2D rd = Screen.getPrimary().getVisualBounds();
            stage.setX((rd.getWidth() - stage.getWidth()) / 2);
            stage.setY((rd.getHeight() - stage.getHeight()) / 2);
            stage.setResizable(false);
        } catch (Exception ex) {
        }
    }

    }

