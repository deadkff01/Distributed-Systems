/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhometop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author hp
 */
public class Login extends Application{
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Style.fxml"));
        stage.setTitle("MHT - Servidor");
        stage.setScene(new Scene(root, 506, 400));
        stage.show();

    }
      public static void main(String[] args) {
        launch(args);
    }
}
