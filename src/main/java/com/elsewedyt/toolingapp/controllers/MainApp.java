package com.elsewedyt.toolingapp.controllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/screens/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("تسجيل الدخول");
            stage.setScene(scene);
            stage.show();
    }catch (Exception e){
            System.out.println("Start Method : " + e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }



    //try {
    //            //    createLog();
    //            // load Setting xml data
    //            logMessage(ERROR, this.getClass().getName(), "start", "Test Start");
    //            SettingService.getXmlFile();
    //            checkLicense();
    //            ALERT("","تم التشغيل",ALERT_INFORMATION);
    //            if (DbConnect.getConnect() == null) {
    //                ALERT("", APP_BUNDLE().getString("CONNECTION_ERROR"), ALERT_ERROR);
    //                logMessage(ERROR, this.getClass().getName(), "start", "Connection error getConnect(): %s", null);
    //            } else {
    //                OPEN_LOGIN_PAGE();
    //            }
    //        } catch (Exception ex) {
    //            logException(ERROR, this.getClass().getName(), "start", ex);
    //        }
    //    }
    //
    //    public static void main(String[] args) {
    //        launch(args);
    //    }

    // try {
    //             FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/screens/LogIn1.fxml"));
    //                Scene scene = new Scene(fxmlLoader.load());
    ////                stage.setTitle("Login");
    ////                stage.setScene(scene);
    ////                stage.show();
    //                Parent root= FXMLLoader.load(MainApp.class.getResource("/screens/LogIn1.fxml"));
    //                Scene scene = new Scene(root);
    //                Stage stage = new Stage();
    //                stage.setScene(scene);
    //                stage.show();
    //
    //            }catch (Exception e){
    //                System.out.println("start meth :" + e);
    //            }
}
