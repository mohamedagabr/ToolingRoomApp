package com.elsewedyt.toolingapp.controllers;
import com.elsewedyt.toolingapp.services.ApiCaller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try{
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI("https://jsonplaceholder.typicode.com/todos/1"))
//                    .GET()
//                    .build();
//
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            System.out.println("Response:");
//            System.out.println(response.body());
            String url = "https://jsonplaceholder.typicode.com/todos/1";
            String response = ApiCaller.callApi(url,"GET",null);


            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/screens/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("تسجيل الدخول");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
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
