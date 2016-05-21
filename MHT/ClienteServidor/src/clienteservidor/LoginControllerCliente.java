/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidor;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import myhometop.Atuador;


import myhometop.Sensor;

import myhometop.ServerActions;

import myhometop.Encrypt;

/**
 *
 * @author hp
 */
public class LoginControllerCliente implements Initializable {
@FXML
private Label loginMessage;
@FXML
private TextField nome;
@FXML
private TextField senha;

private  ArrayList<Sensor> listaSensor = new ArrayList<>();
private  ArrayList<Atuador> listaAtuador = new ArrayList<>(); 

private ServerActions sa;

@FXML
private void btnLoginAction(ActionEvent event) throws Exception{

    if("usuario".equals(nome.getText()) && "PSynyeI1h3jmnJnFIHGJlg==".equals(Encrypt.encrypt(senha.getText()))) {     
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("MainCliente.fxml"));
   
        Stage stage = new Stage();
        stage.setTitle("MHT - Cliente");
        stage.setScene(new Scene(root, 1136, 661));
        stage.show();
        
        MainControllerCliente.cliente1();
        

    }else {
        loginMessage.setText("Usuario ou senha inválidos!");
    }
    
}
    

@Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }
    
}
