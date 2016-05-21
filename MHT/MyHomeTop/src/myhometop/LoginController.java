/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhometop;

import java.net.URL;
import java.rmi.AlreadyBoundException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;



/**
 *
 * @author hp
 */
public class LoginController implements Initializable {
@FXML
private Label loginMessage;
@FXML
private TextField nome;
@FXML
private TextField senha;

private  ServerActions sa;

@FXML
private void btnLoginAction(ActionEvent event) throws Exception{
                                            //AES ENCRYPT
    if("usuario".equals(nome.getText()) && "PSynyeI1h3jmnJnFIHGJlg==".equals(Encrypt.encrypt(senha.getText()))) {     
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
   
        Stage stage = new Stage();
        stage.setTitle("MHT - Servidor");
        stage.setScene(new Scene(root, 1136, 661));
        stage.show();

        startRMIServer();
         
        MainController.cliente1();
  
    }else {
        loginMessage.setText("Usuario ou senha inválidos!");
    }
 
}
    
    public void startRMIServer() throws RemoteException, AlreadyBoundException{
        RemoteImpl impl = new RemoteImpl();
        Registry r = LocateRegistry.createRegistry(222);
        r.bind("CASA", impl);
        System.out.println("Server Started!");    
         
    }
    

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }
    
}
