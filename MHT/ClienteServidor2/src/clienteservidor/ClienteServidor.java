/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidor;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class ClienteServidor extends Application {
    
    @Override
    public void start(Stage stage)  throws RemoteException, NotBoundException, IOException {
     
        Parent root = FXMLLoader.load(getClass().getResource("StyleCliente.fxml"));
        stage.setTitle("MHT - Cliente");
        stage.setScene(new Scene(root, 506, 400));
        stage.show();
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
  

        launch(args);
    }
    
}
